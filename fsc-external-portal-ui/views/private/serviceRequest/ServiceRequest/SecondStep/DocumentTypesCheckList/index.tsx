import Box from '@mui/material/Box';
import Checkbox from '@mui/material/Checkbox';
import FormControlLabel from '@mui/material/FormControlLabel';
import FormGroup from '@mui/material/FormGroup';
import Typography from '@mui/material/Typography';
import { Fragment, useContext } from 'react';
import { useTranslation } from 'react-i18next';
import { useAppSelector } from '../../../../../../app/hooks/redux';
import { DOCUMENT_TYPE_NECESSITY_OPTIONS } from '../../../../../../contracts/enums/documentTypes';
import {
  IDocument,
  IDocumentFileReference,
  IDocumentType,
} from '../../../../../../contracts/interfaces/document';
import { documentTypesSelector } from '../../../../../../store/documentTypes';
import ServiceRequestContext, {
  IServiceRequestContext,
} from '../../ServiceRequestContext';
import DocumentUploadModal from '../DocumentUploadModal';
import ErrorOutlineOutlinedIcon from '@mui/icons-material/ErrorOutlineOutlined';
import Tooltip from '@mui/material/Tooltip';
import IconButton from '@mui/material/IconButton';
import FileDownloadOutlinedIcon from '@mui/icons-material/FileDownloadOutlined';
import { useDownloadPublicFileMutation } from '../../../../../../store/api/filesSlice';
import fileDownload from 'js-file-download';
import { handleError } from '../../../../../../utils/handlers/errorHandlers';
import { IUser, userSelector } from '../../../../../../store/user';
import { ERRORS } from '../../../../../../constants/errors';

const DocumentTypesCheckList = ({
  showDocumentTypes,
}: {
  showDocumentTypes: 'all' | 'mandatory' | 'recommended';
}) => {
  const { t } = useTranslation(['services']);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const [downloadSpecialDocTemplate] = useDownloadPublicFileMutation();
  const { documents } = useContext<IServiceRequestContext>(
    ServiceRequestContext
  );
  const documentTypes = useAppSelector<IDocumentType[]>(documentTypesSelector);

  if (documentTypes.length === 0) return null;

  const handleDownload = async ({
    fileRef,
    fileReferenceStr,
  }: {
    fileRef: IDocumentFileReference | null;
    fileReferenceStr: string | null;
  }) => {
    if (!fileRef || !fileReferenceStr) {
      throw new Error(
        ERRORS.DOCUMENTS.DOCUMENT_FILE_REFERENCE_PROP_HAS_MISSING_PROPERTY
      );
    }

    try {
      const file = await downloadSpecialDocTemplate({
        fileReferenceStr,
      }).unwrap();

      fileDownload(file, fileRef.fileName, fileRef.contentType);
    } catch (e: any) {
      handleError(e);
    }
  };

  const filteredCheckboxList = documentTypes.filter(
    (documentType: IDocumentType) => {
      let showDocumentType = false;
      switch (showDocumentTypes) {
        case 'mandatory':
          showDocumentType =
            documentType.mandatory ===
            DOCUMENT_TYPE_NECESSITY_OPTIONS.MANDATORY;
          break;
        case 'recommended':
          showDocumentType =
            documentType.mandatory ===
            DOCUMENT_TYPE_NECESSITY_OPTIONS.RECOMMENDED;
          break;
        default:
          break;
      }
      return showDocumentType;
    }
  );

  const checkboxList = filteredCheckboxList?.map(
    (documentType: IDocumentType) => (
      <Fragment key={documentType.id}>
        <Box
          sx={{
            width: '100%',
            display: 'flex',
            flexDirection: { xs: 'column', sm: 'row' },
            justifyContent: 'space-between',
            borderBottom: '1px solid #bdbdbd',
          }}
        >
          <FormControlLabel
            control={
              <Checkbox
                checked={
                  documents.length > 0 &&
                  !!documents.find(
                    (document: IDocument) =>
                      document.sceDocumentTypeId?.toString() ===
                      documentType.id?.toString()
                  )
                }
                inputProps={{
                  readOnly: true,
                  'aria-readonly': true,
                }}
              />
            }
            label={
              <Typography variant="body1">
                {documentType.shortName}

                {documentType.mandatory ===
                DOCUMENT_TYPE_NECESSITY_OPTIONS.MANDATORY ? (
                  <ErrorOutlineOutlinedIcon
                    sx={{
                      color: '#ffa5a5',
                      margin: '0 .2rem',
                      fontSize: '1.2rem',
                    }}
                  />
                ) : null}
              </Typography>
            }
            sx={{ wordBreak: 'break-all' }}
          />
          <Box sx={{ display: 'flex' }}>
            {documentType.templateFileReference &&
              documentType.templateFileReferenceStr && (
                <Tooltip
                  title={
                    t(
                      'services.secondStep.documentTypes.downloadSpecialDocumentsTemplate'
                    ) ?? ''
                  }
                >
                  <IconButton
                    aria-label={t(
                      'services.secondStep.documentTypes.downloadSpecialDocumentsTemplate'
                    )}
                    sx={{ mr: '1rem' }}
                    onClick={() =>
                      handleDownload({
                        fileRef: documentType.templateFileReference,
                        fileReferenceStr: documentType.templateFileReferenceStr,
                      })
                    }
                  >
                    <FileDownloadOutlinedIcon />
                  </IconButton>
                </Tooltip>
              )}
            <DocumentUploadModal
              keepMounted={true}
              selectedDocumentTypeId={documentType.id}
            />
          </Box>
        </Box>
      </Fragment>
    )
  );

  return checkboxList?.length > 0 ? (
    <Box
      sx={{
        width: '100%',
        padding: '.25rem .5rem',
      }}
    >
      <FormGroup>{checkboxList}</FormGroup>
    </Box>
  ) : null;
};

export default DocumentTypesCheckList;
