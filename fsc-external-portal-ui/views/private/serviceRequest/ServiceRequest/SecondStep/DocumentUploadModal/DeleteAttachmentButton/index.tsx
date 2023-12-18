import IconButton from '@mui/material/IconButton';
import { useFormikContext } from 'formik';
import { DocumentUploadModalFormikContextType } from '..';
import { handleError } from '../../../../../../../utils/handlers/errorHandlers';
import CloseIcon from '@mui/icons-material/Close';
import { useTranslation } from 'react-i18next';
import { useContext } from 'react';
import DocumentUploadModalContext, {
  IDocumentUploadModalContext,
} from '../DocumentUploadModalContext';

const DeleteAttachmentButton = () => {
  const { t } = useTranslation(['services']);
  const { fileFormikValues, setFileFormikValues } =
    useContext<IDocumentUploadModalContext>(DocumentUploadModalContext);
  const {
    values: { attachment },
    setFieldValue,
  } = useFormikContext<DocumentUploadModalFormikContextType>();

  return (
    <IconButton
      aria-label={t(
        'services.serviceRequest.documents.uploadModal.deleteAttachment.label'
      )}
      color="primary"
      onClick={async () => {
        if (!attachment) return;

        try {
          fileFormikValues && setFileFormikValues(undefined);
          setFieldValue('attachment', undefined);
        } catch (error: any) {
          handleError(error);
        }
      }}
    >
      <CloseIcon />
    </IconButton>
  );
};

export default DeleteAttachmentButton;
