import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import FileDownloadOutlinedIcon from '@mui/icons-material/FileDownloadOutlined';
import { handleError } from '../../../../../utils/handlers/errorHandlers';
import fileDownload from 'js-file-download';
import { useAppSelector } from '../../../../../app/hooks';
import { IUser, userSelector } from '../../../../../store/user';
import { useTranslation } from 'react-i18next';
import {
  IDocument,
  IDocumentFileReference,
} from '../../../../../contracts/interfaces/document';
import Tooltip from '@mui/material/Tooltip';
import { useDownloadFileMutation } from '../../../../../store/api/filesSlice';

const FileComponent = ({ attachedItem }: { attachedItem: IDocument }) => {
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { t } = useTranslation(['dossier']);
  const [downloadFile] = useDownloadFileMutation();

  const getFile = async (fileInfo: {
    fileRef: IDocumentFileReference;
    fileReferenceStr: string | null;
  }) => {
    try {
      if (!fileInfo.fileReferenceStr || !fileInfo.fileReferenceStr) {
        return;
      }
      const file = await downloadFile({
        accessToken,
        fileReferenceStr: fileInfo.fileReferenceStr,
      }).unwrap();

      fileDownload(
        file,
        fileInfo.fileRef.fileName,
        fileInfo.fileRef.contentType
      );
    } catch (e: any) {
      handleError(e);
    }
  };

  return (
    <Stack key={attachedItem.id}>
      <Stack
        flexDirection="row"
        alignContent="center"
        alignItems="center"
        flexWrap="wrap"
      >
        <Typography variant="body1" component="span" flexGrow={1}>
          {attachedItem.fileRef?.fileName}
        </Typography>
        <Tooltip title={t('requestedServiceInfo.download') ?? ''}>
          <IconButton
            aria-label={t('requestedServiceInfo.download')}
            onClick={async () => {
              const fileInfo = {
                fileRef: attachedItem.fileRef,
                fileReferenceStr: attachedItem.fileReferenceStr,
              };
              getFile(fileInfo);
            }}
          >
            <FileDownloadOutlinedIcon />
          </IconButton>
        </Tooltip>
      </Stack>
      <Divider sx={{ m: '1rem 0rem' }} />
    </Stack>
  );
};

export default FileComponent;
