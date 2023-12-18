import React, {
  Dispatch,
  SetStateAction,
  useCallback,
  useContext,
  useState,
} from 'react';
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
import FileCreationForm from './FileCreationForm';
import { IAttachment } from '../../../../../../../contracts/interfaces/attachment';
import generateXMLFileFromJSObject from '../../../../../../../utils/serviceHelpers/blank/generateXMLFileFromJSObject';
import useServiceRequestFormDataByDocumentTypeId from '../../../../../../../app/hooks/serviceRequest/useServiceRequestFormDataByDocumentTypeId';
import DocumentUploadModalContext, {
  IDocumentUploadModalContext,
} from '../DocumentUploadModalContext';
import { DocumentUploadModalFormikContextType } from '..';

const CreateAttachmentButton = () => {
  const { t } = useTranslation(['services']);
  const [open, setOpen] = useState<boolean>(false);

  const { setFileFormikValues } = useContext<IDocumentUploadModalContext>(
    DocumentUploadModalContext
  );
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
    async (values: any, { setSubmitting }: FormikHelpers<any>) => {
      setSubmitting(false);

      try {
        const file = generateXMLFileFromJSObject(values);

        const { name: fileName } = file;

        try {
          setFileFormikValues(values);
          setFieldValue('attachment', {
            file,
            fileRef: '',
            fileName,
          } as IAttachment);
        } catch (error: any) {
          handleError(error);
        }

        closeModal();
      } catch (e: any) {
        handleError(e);
      }
    },
    [closeModal, setFieldValue, setFileFormikValues]
  );
  return (
    <Stack alignItems="flex-start" sx={{ width: { xs: '100%', md: '50%' } }}>
      <Button
        onClick={() => {
          setOpen(!open);
        }}
      >
        {t(
          'services.serviceRequest.documents.uploadModal.fillFormForFileGenerating.label'
        )}
      </Button>
      <Dialog
        onClose={() => {
          closeModal();
        }}
        open={open}
        aria-labelledby="file-generation-modal"
        aria-describedby="file-generation-modal"
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
          {() => (
            <Box display="flex" flexDirection="column">
              <Stack flexDirection={'row'}>
                <DialogTitle id="file-generation-modal" sx={{ flexGrow: 1 }}>
                  <Typography variant="h2" component="span" color="text.main">
                    {t(
                      'services.serviceRequest.documents.uploadModal.fileGeneration.label'
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
                <DialogContent
                  sx={{
                    bgColor: 'common.white',
                    maxHeight: '70vh',
                    overflow: 'hidden',
                  }}
                >
                  <Paper
                    sx={{
                      maxHeight: '65vh',
                      elevation: 2,
                      overflow: 'auto',
                      p: '1rem',
                    }}
                  >
                    <ConnectedFocusError />
                    <FileCreationForm
                      componentsData={componentsData}
                      htmlForm={htmlForm}
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
            </Box>
          )}
        </Formik>
      </Dialog>
    </Stack>
  );
};
export default CreateAttachmentButton;
