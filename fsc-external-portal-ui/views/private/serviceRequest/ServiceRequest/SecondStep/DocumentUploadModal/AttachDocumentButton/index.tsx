import Button from '@mui/material/Button';
import { useTranslation } from 'react-i18next';

const AttachDocumentButton = ({
  handleClick,
  hasOnlyOptionalDocs,
}: {
  handleClick: (event: React.MouseEvent<HTMLElement>) => void;
  hasOnlyOptionalDocs?: boolean;
}) => {
  const { t } = useTranslation(['services']);
  let attachDocButtonText: string;
  if (hasOnlyOptionalDocs === false) {
    attachDocButtonText = t(
      'services.serviceRequest.documents.uploadModal.uploadMoreDocument.label'
    );
  } else if (hasOnlyOptionalDocs === true) {
    attachDocButtonText = t(
      'services.serviceRequest.documents.uploadModal.uploadDocuments.label'
    );
  } else {
    attachDocButtonText = t(
      'services.serviceRequest.documents.uploadModal.uploadDocument.label'
    );
  }

  return (
    <Button
      variant="contained"
      component="button"
      onClick={handleClick}
      sx={{ m: '1rem 0rem', width: 'fit-content', minWidth: '12.8rem' }}
    >
      {attachDocButtonText}
    </Button>
  );
};

export default AttachDocumentButton;
