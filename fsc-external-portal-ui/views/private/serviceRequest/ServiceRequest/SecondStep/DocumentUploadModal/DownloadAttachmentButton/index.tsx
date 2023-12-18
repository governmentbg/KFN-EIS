import IconButton from '@mui/material/IconButton';
import { useFormikContext } from 'formik';
import { DocumentUploadModalFormikContextType } from '..';
import { handleError } from '../../../../../../../utils/handlers/errorHandlers';
import FileDownloadOutlinedIcon from '@mui/icons-material/FileDownloadOutlined';
import fileDownload from 'js-file-download';
import { useTranslation } from 'react-i18next';

const DownloadAttachmentButton = () => {
  const { t } = useTranslation(['services']);
  const {
    values: { attachment },
  } = useFormikContext<DocumentUploadModalFormikContextType>();

  return (
    <IconButton
      aria-label={t(
        'services.serviceRequest.documents.uploadModal.downloadAttachment.label'
      )}
      onClick={async () => {
        if (!attachment) return;

        const { file } = attachment || {};
        const { name, type } = file || {};

        try {
          fileDownload(file, name, type);
        } catch (e: any) {
          handleError(e);
        }
      }}
    >
      <FileDownloadOutlinedIcon />
    </IconButton>
  );
};

export default DownloadAttachmentButton;
