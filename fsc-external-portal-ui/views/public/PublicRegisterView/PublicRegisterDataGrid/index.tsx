import Box from '@mui/material/Box';
import { DataGrid, GridColDef, GridEventListener } from '@mui/x-data-grid';
import { useRouter } from 'next/router';
import { useTranslation } from 'react-i18next';
import { useFetchPublicRegisterQuery } from '../../../../store/api/publicRegisterSlice';
import RenderCellExpand from './RenderCellExpand';
import { useEffect, useState } from 'react';
import { IPublicRegisterFilter } from '../../../../contracts/interfaces/publicRegisterFilter';
import ReadMoreIcon from '@mui/icons-material/ReadMore';
import Button from '@mui/material/Button';

const PublicRegisterDataGrid = ({
  filterParams,
}: {
  filterParams: IPublicRegisterFilter;
}) => {
  const [skip, setSkip] = useState(true);
  const [page, setPage] = useState(1);
  const { t } = useTranslation();
  const router = useRouter();
  const { data } = useFetchPublicRegisterQuery(
    {
      serviceId: router?.query?.id?.toString() ?? '',
      page: page,
      filterParams,
    },
    { skip }
  );

  const { totalNumberOfElements, currentPage, pageSize, result } = data || {
    result: [],
    totalNumberOfPages: 0,
    totalNumberOfElements: 0,
    currentPage: 1,
    pageSize: 30,
  };

  const columns: GridColDef[] = Object.keys(result?.[0] ?? []).map(
    (key: string) => ({
      field: key,
      headerName: `${t(`services.publicRegister.dataGrid.column.${key}.label`, {
        ns: 'services',
      })}`,
      flex: key === 'pnlName' ? 1 : 0.3,
      renderCell: RenderCellExpand,
      sortable: false,
    })
  );

  const buttonCol = {
    field: 'detailedView',
    headerName: '',
    width: 60,
    filterable: false,
    sortable: false,
    renderCell: () => (
      <Button variant="outlined" size="small" sx={{ minWidth: '2.5rem' }}>
        <ReadMoreIcon sx={{ cursor: 'pointer' }} />
      </Button>
    ),
  };

  columns.unshift(buttonCol);

  const handleEvent: GridEventListener<'cellClick'> = (params) => {
    if (params.field !== 'detailedView') {
      return;
    }

    router.push(
      `/public-register/${router.query.id}/detailed-view/${params.row.pnlId}`
    );
  };

  useEffect(() => {
    if (typeof router.query.id !== 'undefined') {
      setSkip(false);
    }
  }, [router.query.id]);

  useEffect(() => {
    setPage(1);
  }, [filterParams]);

  return (
    <Box
      sx={{
        position: 'relative',
        height: { xs: '28rem', md: '100%' },
        width: '100%',
        minHeight: '28rem',
      }}
    >
      <DataGrid
        getRowId={(row) => row.pnlName + row.eikEgn + row.pnlId}
        columnVisibilityModel={{ pnlId: false }}
        rows={result}
        rowCount={totalNumberOfElements}
        rowsPerPageOptions={[pageSize]}
        pagination
        page={currentPage - 1}
        pageSize={pageSize}
        paginationMode="server"
        onPageChange={(newPage) => setPage(newPage + 1)}
        columns={columns}
        disableColumnMenu
        filterMode="server"
        disableSelectionOnClick
        experimentalFeatures={{ newEditingApi: true }}
        disableColumnFilter={true}
        localeText={{
          MuiTablePagination: {
            labelDisplayedRows: ({ from, to, count }) =>
              `${from} - ${to} ${t('from').toLowerCase()} ${count}`,
            labelRowsPerPage: t('rowsPerPage.label', { ns: 'common' }),
          },
        }}
        components={{
          NoRowsOverlay: () => <></>,
        }}
        onCellClick={handleEvent}
      />
    </Box>
  );
};

export default PublicRegisterDataGrid;
