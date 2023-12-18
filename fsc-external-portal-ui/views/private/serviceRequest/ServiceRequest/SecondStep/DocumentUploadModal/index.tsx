import React, { useCallback, useContext, useState } from 'react';
import Dialog, { DialogProps } from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import DocumentTypeSelect from './DocumentTypeSelect';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import CloseIcon from '@mui/icons-material/Close';
import IconButton from '@mui/material/IconButton';
import Divider from '@mui/material/Divider';
import {
  useAppDispatch,
  useAppSelector,
} from '../../../../../../app/hooks/redux';
import { userSelector } from '../../../../../../store/user';
import { handleError } from '../../../../../../utils/handlers/errorHandlers';
import {
  IDocument,
  IDocumentType,
  IFileValidationResponseObject,
} from '../../../../../../contracts/interfaces/document';
import {
  deleteDocumentFromServiceRequest,
  deleteDocumentFromServiceRequestPublic,
  submitDocumentToServiceRequest,
  submitDocumentToServiceRequestPublic,
} from '../../../../../../store/serviceRequest';
import { IAttachment } from '../../../../../../contracts/interfaces/attachment';
import showDocumentTypeInvalidMessage from '../../../../../../utils/serviceHelpers/documentUploadModal/showDocumentTypeInvalidMessage';
import showDocumentDescriptionInvalidLengthMessage from '../../../../../../utils/serviceHelpers/documentUploadModal/showDocumentDescriptionInvalidLengthMessage';
import AttachDocumentButton from './AttachDocumentButton';
import { Form, Formik, FormikHelpers, FormikProps } from 'formik';
import showMissingAttachmentMessage from '../../../../../../utils/serviceHelpers/documentUploadModal/showMissingAttachmentMessage';
import { documentUploadValidationSchema } from '../../../../../../validation/serviceRequest/documentUploadValidation';
import DeleteAttachmentButton from './DeleteAttachmentButton';
import ServiceRequestContext, {
  IServiceRequestContext,
} from '../../ServiceRequestContext';
import CreateAttachmentButton from './CreateAttachmentButton';
import EditAttachmentButton from './EditAttachmentButton';
import {
  IPersonStoreState,
  personSelector,
} from '../../../../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../../../../store/pnl';
import {
  useUploadFileMutation,
  useUploadFilePublicMutation,
  useValidateFileByFileTypeMutation,
} from '../../../../../../store/api/filesSlice';
import handleFileValidationSchemaResponse from '../../../../../../utils/serviceHelpers/formComponent/handleFileValidationSchemaResponse';
import DownloadAttachmentButton from './DownloadAttachmentButton';
import { useTranslation } from 'react-i18next';
import { ConnectedFocusError } from 'focus-formik-error';
import AttachFileButton from './AttachFileButton';
import { Provider as DocumentUploadModalContextProvider } from './DocumentUploadModalContext';
import { setLoader } from '../../../../../../store/loader';
import SubmitButton from './SubmitButton';
import ConfirmationDialog from './commonComponents/ConfirmationDialog';
import { documentTypesSelector } from '../../../../../../store/documentTypes';
import checkForUniqueServiceRequestDocumentBySceDocumentTypeId from '../../../../../../utils/serviceHelpers/documentUploadModal/checkForUniquerServiceRequestDocumentBySceDocumentTypeId';
import { useFetchServiceQuery } from '../../../../../../store/api/servicesSlice';
import {
  SERVICE_AUTHENTICATION_LEVELS,
  SERVICE_TYPES,
} from '../../../../../../contracts/enums/services';
import { useRouter } from 'next/router';
import useServiceAuthenticationLevel from '../../../../../../app/hooks/service/useServiceAuthenticationLevel';
import isNullOrUndefined from '../../../../../../utils/isNullOrUndefined';
import { ERRORS } from '../../../../../../constants/errors';
import { DOCUMENT_TYPE_NECESSITY_OPTIONS } from '../../../../../../contracts/enums/documentTypes';
import showDocumentDescriptionRequiredMessage from '../../../../../../utils/serviceHelpers/documentUploadModal/showDocumentDescriptionRequiredMessage';

export type DocumentUploadModalFormikContextType = {
  documentTypeId: string;
  sceDocumentTypeId: string;
  documentDescription: string;
  attachment?: IAttachment;
};

const DocumentUploadModal = ({
  hasOnlyOptionalDocs,
  selectedDocumentTypeId,
  ...rest
}: Partial<DialogProps> & {
  hasOnlyOptionalDocs?: boolean;
  selectedDocumentTypeId?: number | null;
}) => {
  const { t } = useTranslation(['services']);
  const router = useRouter();
  const dispatch = useAppDispatch();
  const { serviceAuthenticationLevel } = useServiceAuthenticationLevel();
  const { accessToken } = useAppSelector(userSelector);
  const { id: personId } = useAppSelector<IPersonStoreState>(personSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);
  const documentTypes = useAppSelector<IDocumentType[]>(documentTypesSelector);
  const [confirmationDialogOpen, setConfirmationDialogOpen] =
    useState<boolean>(false);
  const { data: service } = useFetchServiceQuery(
    { serviceId: router?.query?.id?.toString() ?? '' },
    { skip: !router?.query?.id }
  );

  const [fileFormikValues, setFileFormikValues] = useState<
    { [key: string]: any } | undefined
  >(undefined);

  const { serviceRequestId, documents, setDocuments } =
    useContext<IServiceRequestContext>(ServiceRequestContext);
  const [uploadFile] = useUploadFileMutation();
  const [uploadFilePublic] = useUploadFilePublicMutation();
  const [validateFileByFileType] = useValidateFileByFileTypeMutation();

  const [open, setOpen] = useState<boolean>(false);

  const closeModal = useCallback(() => setOpen(false), []);

  const isServiceOfTypeReport =
    service?.catalogElement?.serviceType === SERVICE_TYPES.REPORT;

  const selectedDocument = documentTypes?.find(
    (documentType: IDocumentType) => documentType.id === selectedDocumentTypeId
  );

  const isSelectedDocumentMandatory =
    selectedDocument?.mandatory === DOCUMENT_TYPE_NECESSITY_OPTIONS.MANDATORY;

  const isReadOnlyInput =
    selectedDocument?.mandatory !== DOCUMENT_TYPE_NECESSITY_OPTIONS.OPTIONAL;

  const handleSubmit = useCallback(
    async (
      {
        attachment,
        documentTypeId,
        sceDocumentTypeId,
        documentDescription,
      }: DocumentUploadModalFormikContextType,
      { resetForm }: FormikHelpers<DocumentUploadModalFormikContextType>,
      excludeDocumentsById?: string[]
    ) => {
      try {
        dispatch(setLoader({ active: true }));

        if (!documentTypeId) return showDocumentTypeInvalidMessage();

        if (documentDescription.length === 0)
          return showDocumentDescriptionRequiredMessage();

        if (documentDescription.length > 1000)
          return showDocumentDescriptionInvalidLengthMessage();

        if (!attachment) return showMissingAttachmentMessage();

        if (!serviceRequestId)
          throw new Error(t('somethingWentWrong', { ns: 'errors' }));

        const { file, fileName } = attachment;

        if (isNullOrUndefined(serviceAuthenticationLevel))
          throw new Error(
            ERRORS.SERVICES.SERVICE_AUTHENTICATION_LEVEL_IS_NOT_PROVIDED
          );

        if (
          serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PRIVATE
        ) {
          const validationResponse: IFileValidationResponseObject[] =
            await validateFileByFileType({
              accessToken,
              file,
              personId: personId?.toString(),
              pnlId: pnlId?.toString(),
              documentTypeId,
            }).unwrap();

          if (validationResponse && validationResponse.length > 0) {
            handleFileValidationSchemaResponse(validationResponse);
            return;
          }
        }
        const uploadFileActionArgs = {
          file,
          fileName,
        };
        const { fileRef } =
          serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PRIVATE
            ? await uploadFile({
                ...uploadFileActionArgs,
                accessToken,
              }).unwrap()
            : await uploadFilePublic(uploadFileActionArgs).unwrap();

        const submitDocumentToServiceRequestActionArgs = {
          serviceRequestId: serviceRequestId?.toString(),
          documentTypeId,
          sceDocumentTypeId,
          documentDescription,
          fileReferenceStr: fileRef,
        };

        const submittedDocument =
          serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PRIVATE
            ? await dispatch(
                submitDocumentToServiceRequest({
                  accessToken,
                  ...submitDocumentToServiceRequestActionArgs,
                })
              ).unwrap()
            : await dispatch(
                submitDocumentToServiceRequestPublic(
                  submitDocumentToServiceRequestActionArgs
                )
              ).unwrap();

        setDocuments([
          ...documents.filter(
            (document: IDocument) =>
              !excludeDocumentsById?.includes(document?.id?.toString())
          ),
          submittedDocument,
        ]);

        closeModal();
        resetForm();
      } catch (error) {
        handleError(error);
      } finally {
        dispatch(setLoader({ active: false }));
      }
    },
    [
      accessToken,
      closeModal,
      dispatch,
      documents,
      personId,
      pnlId,
      serviceAuthenticationLevel,
      serviceRequestId,
      setDocuments,
      t,
      uploadFile,
      uploadFilePublic,
      validateFileByFileType,
    ]
  );

  const initialValues: DocumentUploadModalFormikContextType = {
    documentTypeId: '',
    sceDocumentTypeId: '',
    documentDescription: selectedDocument
      ? selectedDocument.mandatory !== DOCUMENT_TYPE_NECESSITY_OPTIONS.OPTIONAL
        ? selectedDocument.shortName
        : ''
      : '',
    attachment: undefined,
  };

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={documentUploadValidationSchema}
      onSubmit={async (
        values: DocumentUploadModalFormikContextType,
        formikHelpers: FormikHelpers<DocumentUploadModalFormikContextType>
      ) => {
        const { sceDocumentTypeId } = values;
        if (
          checkForUniqueServiceRequestDocumentBySceDocumentTypeId(
            documents,
            documentTypes,
            sceDocumentTypeId
          )
        ) {
          setConfirmationDialogOpen(true);
        } else {
          await handleSubmit(values, formikHelpers);
        }
      }}
    >
      {(formikProps: FormikProps<DocumentUploadModalFormikContextType>) => {
        const {
          values,
          touched,
          errors,
          handleReset,
          handleChange,
          handleBlur,
          submitCount,
        } = formikProps;

        const {
          documentDescription,
          documentTypeId,
          sceDocumentTypeId,
          attachment,
        } = values;

        return (
          <>
            <DocumentUploadModalContextProvider
              value={{
                fileFormikValues,
                setFileFormikValues,
              }}
            >
              <Stack>
                <AttachDocumentButton
                  handleClick={() => setOpen(!open)}
                  hasOnlyOptionalDocs={hasOnlyOptionalDocs}
                />
                <Dialog
                  onClose={() => {
                    handleReset();
                    closeModal();
                  }}
                  open={open}
                  aria-labelledby="document-creating"
                  aria-describedby="document-creating"
                  maxWidth="md"
                  sx={{
                    '.MuiDialog-paperWidthMd': {
                      minWidth: '50%',
                      backgroundColor: 'common.white',
                    },
                  }}
                  {...rest}
                >
                  <Box display="flex" flexDirection="column" overflow="hidden">
                    <Stack flexDirection="row" justifyContent="space-between">
                      <DialogTitle
                        id="document-creating"
                        variant="h2"
                        component="span"
                        color="text.main"
                        flexGrow={1}
                        maxWidth="80%"
                      >
                        {t(
                          'services.serviceRequest.documents.uploadModal.documentCreating.label'
                        )}
                      </DialogTitle>

                      <IconButton
                        aria-label={t('close', { ns: 'common' })}
                        onClick={() => {
                          handleReset();
                          closeModal();
                        }}
                      >
                        <CloseIcon />
                      </IconButton>
                    </Stack>

                    <Form noValidate>
                      <ConnectedFocusError />
                      <DialogContent
                        sx={{
                          maxHeight: '65vh',
                          overflowY: 'auto',
                        }}
                      >
                        <DialogContentText
                          id="document-type-select-label"
                          variant="h3"
                          component="span"
                          color="text.main"
                        >
                          {t(
                            'services.serviceRequest.documents.uploadModal.documentType.label'
                          )}
                        </DialogContentText>

                        <Box
                          sx={{
                            width: { xs: '100%', md: '50%' },
                            m: '1rem 0rem',
                          }}
                        >
                          <DocumentTypeSelect
                            modalStateFlag={open}
                            selectedDocumentTypeId={selectedDocumentTypeId}
                          />
                        </Box>

                        <DialogContentText
                          variant="h3"
                          component="span"
                          color="text.main"
                        >
                          {t(
                            'services.serviceRequest.documents.uploadModal.description.label'
                          )}
                        </DialogContentText>

                        <Box mt=".5rem" sx={{ width: '100%' }}>
                          <TextField
                            required
                            disabled={isReadOnlyInput}
                            name="documentDescription"
                            fullWidth
                            label={t(
                              'services.serviceRequest.documents.uploadModal.documentDescription.label'
                            )}
                            error={Boolean(
                              touched['documentDescription'] &&
                                errors['documentDescription']
                            )}
                            helperText={errors['documentDescription']}
                            aria-label={t(
                              'services.serviceRequest.documents.uploadModal.documentDescription.label'
                            )}
                            inputProps={{
                              readOnly:
                                selectedDocument?.mandatory !==
                                DOCUMENT_TYPE_NECESSITY_OPTIONS.OPTIONAL,
                            }}
                            onChange={handleChange}
                            onBlur={handleBlur}
                            type="text"
                            value={documentDescription}
                            variant="outlined"
                            sx={
                              isReadOnlyInput
                                ? {
                                    backgroundColor: 'rgba(0,0,0,0.06)',
                                    borderRadius: '4px',
                                  }
                                : {}
                            }
                          ></TextField>
                        </Box>

                        <Stack
                          mt="2rem"
                          sx={{ gap: '1rem' }}
                          flexDirection="row"
                        >
                          <AttachFileButton />
                          {process.env.NEXT_PUBLIC_SHOW_XML_GENERATOR ===
                            'true' && (
                            <>
                              {!attachment &&
                                documentTypeId?.length > 0 &&
                                isServiceOfTypeReport &&
                                isSelectedDocumentMandatory && (
                                  <CreateAttachmentButton />
                                )}

                              {attachment &&
                                documentTypeId?.length > 0 &&
                                fileFormikValues &&
                                isServiceOfTypeReport &&
                                isSelectedDocumentMandatory && (
                                  <EditAttachmentButton />
                                )}
                            </>
                          )}
                        </Stack>

                        {Boolean(
                          errors['attachment'] &&
                            submitCount > 0 &&
                            !attachment &&
                            documentTypeId?.length > 0
                        ) && (
                          <Stack>
                            <Typography
                              component="span"
                              variant="h5"
                              color="#d32f2f"
                              ml="1rem"
                            >
                              {errors['attachment']}
                            </Typography>
                          </Stack>
                        )}

                        {attachment && (
                          <Stack>
                            <Divider sx={{ m: '1rem 0rem' }} />
                            <Stack
                              flexDirection="row"
                              alignContent="center"
                              alignItems="center"
                              flexWrap="wrap"
                            >
                              <Typography
                                variant="body1"
                                component="span"
                                flexGrow={1}
                              >
                                {attachment.file.name}
                              </Typography>

                              <DownloadAttachmentButton />
                              <DeleteAttachmentButton />
                            </Stack>
                          </Stack>
                        )}
                      </DialogContent>

                      <DialogActions>
                        <SubmitButton />
                      </DialogActions>
                    </Form>
                  </Box>
                </Dialog>
              </Stack>
            </DocumentUploadModalContextProvider>

            <ConfirmationDialog
              open={confirmationDialogOpen}
              setOpen={setConfirmationDialogOpen}
              contentText={`${t(
                'services.serviceRequest.documents.uploadModal.documentWithSuchTypeExists'
              )}, ${t(
                'services.serviceRequest.documents.uploadModal.doYouWantToReplaceIt'
              ).toLowerCase()} ?`}
              confirmButtonLabel={t('yes', { ns: 'common' })}
              cancelButtonLabel={t('no', { ns: 'common' })}
              onConfirm={async () => {
                try {
                  dispatch(setLoader({ active: true }));

                  if (!serviceRequestId)
                    throw new Error(t('somethingWentWrong', { ns: 'errors' }));

                  const foundDocument =
                    checkForUniqueServiceRequestDocumentBySceDocumentTypeId(
                      documents,
                      documentTypes,
                      sceDocumentTypeId
                    );

                  const { id: documentId } = foundDocument || {};
                  if (!documentId) return;

                  const deleteDocumentFromServiceRequestActionArgs = {
                    serviceRequestId,
                    documentId,
                  };

                  if (isNullOrUndefined(serviceAuthenticationLevel))
                    throw new Error(
                      ERRORS.SERVICES.SERVICE_AUTHENTICATION_LEVEL_IS_NOT_PROVIDED
                    );

                  serviceAuthenticationLevel ===
                  SERVICE_AUTHENTICATION_LEVELS.PRIVATE
                    ? await dispatch(
                        deleteDocumentFromServiceRequest({
                          accessToken,
                          ...deleteDocumentFromServiceRequestActionArgs,
                        })
                      ).unwrap()
                    : await dispatch(
                        deleteDocumentFromServiceRequestPublic(
                          deleteDocumentFromServiceRequestActionArgs
                        )
                      ).unwrap();

                  await handleSubmit(values, formikProps, [
                    documentId?.toString(),
                  ]);

                  setConfirmationDialogOpen(false);

                  closeModal();
                } catch (e) {
                  handleError(e);
                } finally {
                  dispatch(setLoader({ active: false }));
                }
              }}
            />
          </>
        );
      }}
    </Formik>
  );
};

export default DocumentUploadModal;
