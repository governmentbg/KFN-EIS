import React, { useCallback, useContext, useEffect } from 'react';
import {
  DataGrid,
  GridActionsCellItem,
  GridColumns,
  DataGridProps,
  GridRowParams,
} from '@mui/x-data-grid';
import DeleteIcon from '@mui/icons-material/Delete';
import fileDownload from 'js-file-download';
import FileDownloadOutlinedIcon from '@mui/icons-material/FileDownloadOutlined';
import { handleError } from '../../../../../../utils/handlers/errorHandlers';
import {
  useAppDispatch,
  useAppSelector,
} from '../../../../../../app/hooks/redux';
import {
  IDocument,
  IDocumentType,
} from '../../../../../../contracts/interfaces/document';
import { useDateFnsLocale } from '../../../../../../app/hooks/date';
import { documentTypesSelector } from '../../../../../../store/documentTypes';
import ServiceRequestContext, {
  IServiceRequestContext,
} from '../../ServiceRequestContext';
import { setLoader } from '../../../../../../store/loader';
import useFetchDocumentFile from '../../../../../../store/hooks/files/useFetchDocumentFIle';
import useDeleteDocumentFromServiceRequest from '../../../../../../store/hooks/files/useDeleteDocumentFromServiceRequest';
import { ERRORS } from '../../../../../../constants/errors';

export interface IRow {
  id: number;
  documentId: number;
  createdDateTime: string;
  documentTypeName: string;
  documentDescription: string;
  documentName: string;
  fileRef: {
    path: string;
    fileName: string;
    storageName?: string;
    parameters?: { size?: string };
    contentType?: string;
  };
}
interface IDataGridParams extends GridRowParams {
  row: IRow;
}

const DocumentsDataGrid = (props: Partial<DataGridProps>) => {
  const dispatch = useAppDispatch();
  const [rows, setRows] = React.useState<IRow[]>([]);
  const documentTypes = useAppSelector<IDocumentType[]>(documentTypesSelector);
  const { serviceRequestId, documents, setDocuments } =
    useContext<IServiceRequestContext>(ServiceRequestContext);
  const [dateFnsLocale] = useDateFnsLocale();
  const { fetchDocumentFile } = useFetchDocumentFile();
  const { deleteDocumentFromServiceRequestAbstract } =
    useDeleteDocumentFromServiceRequest();

  const handleDownload = useCallback(
    async ({
      row: {
        documentId,
        fileRef: { fileName },
      },
    }: IDataGridParams) => {
      try {
        const document = documents.find(
          (document: IDocument) => document.id === documentId
        );

        if (!document)
          throw new Error(ERRORS.DOCUMENTS.THERE_IS_NO_SUCH_DOCUMENT);

        let file = await fetchDocumentFile({
          document,
        });

        fileDownload(file, `${fileName}`);
      } catch (error: any) {
        handleError(error);
      }
    },
    [documents, fetchDocumentFile]
  );

  const handleDelete = useCallback(
    async ({ row: { documentId } }: IDataGridParams) => {
      try {
        dispatch(setLoader({ active: true }));

        if (!serviceRequestId)
          throw new Error(ERRORS.SERVICES.SERVICE_REQUEST_ID_IS_NOT_PROVIDED);

        await deleteDocumentFromServiceRequestAbstract({
          documentId,
          serviceRequestId,
        });

        setDocuments(
          documents.filter(
            (document: IDocument) =>
              document?.id?.toString() !== documentId?.toString()
          )
        );
      } catch (error: any) {
        handleError(error);
      } finally {
        dispatch(setLoader({ active: false }));
      }
    },
    [
      deleteDocumentFromServiceRequestAbstract,
      dispatch,
      documents,
      serviceRequestId,
      setDocuments,
    ]
  );

  //TODO: Add translations to the headerName prop of each column
  const columns = React.useMemo<GridColumns<IRow>>(
    () => [
      {
        field: 'documentTypeName',
        type: 'string',
        headerName: `Документ`,
        flex: 1,
        minWidth: 150,
      },
      {
        field: 'documentName',
        type: 'string',
        headerName: `Име на файл`,
        flex: 1,
        minWidth: 150,
      },
      {
        field: 'documentDescription',
        type: 'string',
        headerName: `Описание`,
        flex: 1,
        minWidth: 150,
      },
      {
        field: 'createdDateTime',
        type: 'string',
        headerName: `Дата и час`,
        flex: 1,
        minWidth: 150,
      },
      {
        field: 'actions',
        type: 'actions',
        headerName: `Действия`,
        flex: 0.5,
        minWidth: 150,
        getActions: (params: GridRowParams<IRow>) => [
          <GridActionsCellItem
            icon={<FileDownloadOutlinedIcon />}
            label="Preview icon"
            onClick={() => handleDownload(params)}
            key={params.id}
          />,
          <GridActionsCellItem
            icon={<DeleteIcon />}
            label="Delete icon"
            onClick={() => handleDelete(params)}
            key={params.id}
          />,
        ],
      },
    ],
    [handleDelete, handleDownload]
  );

  useEffect(() => {
    if (documents.length > 0) {
      setRows(
        documents.map((document: IDocument): IRow => {
          const {
            id,
            sceDocumentTypeId,
            createdDate,
            documentDescription,
            fileRef: { fileName, storageName, path },
          } = document;
          return {
            id: id,
            documentId: id,
            createdDateTime: createdDate,
            documentTypeName:
              documentTypes?.find(
                (documentType: IDocumentType) =>
                  documentType.id?.toString() === sceDocumentTypeId.toString()
              )?.shortName ?? '',
            documentDescription: documentDescription,
            documentName: fileName,
            fileRef: document.fileRef,
          };
        })
      );
    } else {
      setRows([]);
    }
  }, [documents, dateFnsLocale, documentTypes]);

  return (
    <DataGrid
      rows={rows}
      columns={columns}
      pageSize={5}
      rowsPerPageOptions={[5, 10, 15, 25, 50, 100]}
      components={{
        NoRowsOverlay: function () {
          return <></>;
        },
      }}
      disableSelectionOnClick={true}
      {...props}
    />
  );
};

export default DocumentsDataGrid;
