import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { useCallback, useEffect, useMemo, useState } from 'react';
import { useRouter } from 'next/router';
import { useAppDispatch, useAppSelector } from '../app/hooks';
import { handleError } from '../utils/handlers/errorHandlers';
import { getDocumentTypesByServiceIdPublic } from '../store/serviceRequest';
import { IDocumentType } from '../contracts/interfaces/document';
import { IUser, userSelector } from '../store/user';
import { DataGrid } from '@mui/x-data-grid/DataGrid';
import {
  GridActionsCellItem,
  GridColDef,
  GridRowParams,
} from '@mui/x-data-grid';
import FileDownloadOutlinedIcon from '@mui/icons-material/FileDownloadOutlined';
import { useTranslation } from 'react-i18next';
import { ERRORS } from '../constants/errors';
import { useDownloadPublicFileMutation } from '../store/api/filesSlice';
import fileDownload from 'js-file-download';

const SpecialDocumentsDataGrid = () => {
  const [documentTypes, setDocumentTypes] = useState<IDocumentType[]>([]);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const dispatch = useAppDispatch();
  const router = useRouter();
  const { t } = useTranslation(['services']);
  const [downloadSpecialDocTemplate] = useDownloadPublicFileMutation();
  const specialDocuments = documentTypes.filter(
    (document: IDocumentType) => document.templateFileReferenceStr !== null
  );

  const handleDownload = useCallback(
    async ({ row }: GridRowParams<IDocumentType>) => {
      if (!row.templateFileReference || !row.templateFileReferenceStr) {
        throw new Error(
          ERRORS.DOCUMENTS.DOCUMENT_FILE_REFERENCE_PROP_HAS_MISSING_PROPERTY
        );
      }

      try {
        const file = await downloadSpecialDocTemplate({
          fileReferenceStr: row.templateFileReferenceStr,
        }).unwrap();

        fileDownload(
          file,
          row.templateFileReference.fileName,
          row.templateFileReference.contentType
        );
      } catch (e: any) {
        handleError(e);
      }
      console.log(row);
    },
    [downloadSpecialDocTemplate]
  );

  const columns: GridColDef<IDocumentType>[] = useMemo(
    () => [
      {
        field: 'shortName',
        type: 'string',
        headerName: `Документ`,
        flex: 1,
        minWidth: 150,
        sortable: false,
      },
      {
        field: 'actions',
        type: 'actions',
        headerName: `Изтегли`,
        flex: 0.5,
        minWidth: 150,
        getActions: (params: GridRowParams<IDocumentType>) => [
          <GridActionsCellItem
            icon={<FileDownloadOutlinedIcon />}
            label="Свали бланка"
            onClick={() => handleDownload(params)}
            key={params.id}
          />,
        ],
      },
    ],
    [handleDownload]
  );

  useEffect(() => {
    const getServiceDocumentTypes = async () => {
      const serviceId = router.query.id;
      try {
        if (!serviceId) return;

        const getDocumentTypesActionArgs = {
          serviceId: Number(serviceId),
        };

        const documentTypes = await dispatch(
          getDocumentTypesByServiceIdPublic(getDocumentTypesActionArgs)
        ).unwrap();

        if (documentTypes?.length > 0) {
          setDocumentTypes(documentTypes);
        }
      } catch (e) {
        handleError(e);
      }
    };

    getServiceDocumentTypes();
  }, [dispatch, router.query.id, accessToken]);

  return (
    <Box sx={{ width: '100%', mb: '1rem', pr: '1rem' }}>
      <Typography sx={{ fontStyle: 'italic', fontWeight: 600, mt: '1rem' }}>
        {t('services.specialDocumentsTable.description')}
      </Typography>
      <Box sx={{ display: 'flex', height: '100%' }}>
        <Box sx={{ flexGrow: 1 }}>
          <DataGrid
            autoHeight
            rows={specialDocuments}
            columns={columns}
            pageSize={10}
            disableColumnMenu
            disableSelectionOnClick
            experimentalFeatures={{ newEditingApi: true }}
            disableColumnFilter={true}
            localeText={{
              MuiTablePagination: {
                labelDisplayedRows: ({ from, to, count }) =>
                  `${from} - ${to} ${t('from', {
                    ns: 'common',
                  }).toLowerCase()} ${count}`,
                labelRowsPerPage: t('rowsPerPage.label', { ns: 'common' }),
              },
            }}
            components={{
              NoRowsOverlay: function () {
                return <></>;
              },
            }}
          />
        </Box>
      </Box>
    </Box>
  );
};

export default SpecialDocumentsDataGrid;
