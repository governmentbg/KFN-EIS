import React, { useCallback, useContext, useState } from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import CloseIcon from '@mui/icons-material/Close';
import IconButton from '@mui/material/IconButton';
import { useTranslation } from 'react-i18next';
import { Form, Formik, FormikHelpers, useFormikContext } from 'formik';
import getServiceRequestFormikInitialValues from '../../../../../../../utils/serviceHelpers/blank/getServiceRequestFormikInitialValues';
import { ConnectedFocusError } from 'focus-formik-error';
import Paper from '@mui/material/Paper';
import { handleError } from '../../../../../../../utils/handlers/errorHandlers';
import { IAttachment } from '../../../../../../../contracts/interfaces/attachment';
import FileEditingForm from './FileEditingForm';
import generateXMLFileFromJSObject from '../../../../../../../utils/serviceHelpers/blank/generateXMLFileFromJSObject';
import ConfirmationDialog from '../commonComponents/ConfirmationDialog';
import useServiceRequestFormDataByDocumentTypeId from '../../../../../../../app/hooks/serviceRequest/useServiceRequestFormDataByDocumentTypeId';
import DocumentUploadModalContext, {
  IDocumentUploadModalContext,
} from '../DocumentUploadModalContext';
import { DocumentUploadModalFormikContextType } from '..';
import { useAppDispatch } from '../../../../../../../app/hooks';
import { setLoader } from '../../../../../../../store/loader';

const EditAttachmentButton = () => {
  const { t } = useTranslation('services');
  const dispatch = useAppDispatch();
  const [open, setOpen] = useState<boolean>(false);
  const { fileFormikValues, setFileFormikValues } =
    useContext<IDocumentUploadModalContext>(DocumentUploadModalContext);
  const [confirmationDialogOpen, setConfirmationDialogOpen] =
    useState<boolean>(false);

  const { setFieldValue, getFieldMeta } =
    useFormikContext<DocumentUploadModalFormikContextType>();

  const documentTypeId = getFieldMeta('documentTypeId').value as string;
  const [componentsData, htmlForm] = useServiceRequestFormDataByDocumentTypeId(
    {
      documentTypeId,
    },
    { skipFetching: !documentTypeId }
  );

  const closeModal = useCallback(() => setOpen(false), []);

  const handleSubmit = useCallback(
    async (_, { setSubmitting }: FormikHelpers<any>) => {
      setSubmitting(false);

      setConfirmationDialogOpen(true);
    },
    [setConfirmationDialogOpen]
  );

  return (
    <Stack alignItems="flex-start" sx={{ width: { xs: '100%', md: '50%' } }}>
      <Button
        onClick={() => {
          setOpen(!open);
        }}
      >
        {t(
          'services.serviceRequest.documents.uploadModal.editTheAttachedFile.label'
        )}
      </Button>
      <Dialog
        onClose={() => {
          closeModal();
        }}
        open={open}
        aria-labelledby="attached-file-edit-modal"
        aria-describedby="attached-file-edit-modal"
        maxWidth="md"
        sx={{
          '.MuiDialog-paperWidthMd': {
            minWidth: '50%',
            backgroundColor: 'common.white',
          },
        }}
      >
        <Formik
          initialValues={getServiceRequestFormikInitialValues(componentsData)}
          onSubmit={handleSubmit}
        >
          {({ values }) => (
            <Box sx={{ p: '1rem' }}>
              <Stack flexDirection={'row'}>
                <DialogTitle id="attached-file-edit-modal" sx={{ flexGrow: 1 }}>
                  <Typography variant="h2" component="span" color="text.main">
                    {t(
                      'services.serviceRequest.documents.uploadModal.editingTheAttachedFile.label'
                    )}
                  </Typography>
                </DialogTitle>

                <IconButton
                  aria-label={t('close', { ns: 'common' })}
                  color="primary"
                  onClick={() => {
                    closeModal();
                  }}
                >
                  <CloseIcon />
                </IconButton>
              </Stack>

              <Form noValidate>
                <ConnectedFocusError />

                <DialogContent>
                  <Paper
                    elevation={2}
                    sx={{
                      p: '1rem',
                      backgroundColor: 'common.white',
                    }}
                  >
                    <FileEditingForm
                      componentsData={componentsData}
                      htmlForm={htmlForm}
                      fileFormikValues={fileFormikValues}
                    />
                  </Paper>
                </DialogContent>

                <DialogActions>
                  <Button type="submit" color="primary" variant="contained">
                    {t(
                      'services.serviceRequest.documents.uploadModal.generate.label'
                    )}
                  </Button>
                </DialogActions>
              </Form>

              <ConfirmationDialog
                open={confirmationDialogOpen}
                setOpen={setConfirmationDialogOpen}
                contentText={t(
                  'services.serviceRequest.documents.uploadModal.areYouSureYouWantToReplaceTheAttachedFile.label'
                )}
                confirmButtonLabel={t('yes', { ns: 'common' })}
                cancelButtonLabel={t('no', { ns: 'common' })}
                onConfirm={() => {
                  try {
                    dispatch(setLoader({ active: true }));

                    const file = generateXMLFileFromJSObject(values);

                    const { name: fileName } = file;

                    setFileFormikValues(values);
                    setFieldValue('attachment', {
                      file,
                      fileRef: '',
                      fileName,
                    } as IAttachment);

                    setConfirmationDialogOpen(false);
                    closeModal();
                  } catch (e: any) {
                    handleError(e);
                  } finally {
                    dispatch(setLoader({ active: false }));
                  }
                }}
                onCancel={() => {
                  setConfirmationDialogOpen(false);
                  closeModal();
                }}
              />
            </Box>
          )}
        </Formik>
      </Dialog>
    </Stack>
  );
};
export default EditAttachmentButton;
