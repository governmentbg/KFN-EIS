import Box from '@mui/material/Box';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { useTranslation } from 'react-i18next';
import RenderCellExpand from './RenderCellExpand';
import NoRowsOverlay from './NoRowsOverlay';

type DataGridRowType = {
  personName: string;
  dpf: number;
  ppf: number;
  upf: number;
  dpfps: number;
};

type PensionShareDataGridPropsType = {
  pensionShare: DataGridRowType[];
};

const defaultColumnsKeys: Array<keyof DataGridRowType> = [
  'personName',
  'dpf',
  'ppf',
  'upf',
  'dpfps',
];

const PensionShareDataGrid = ({
  pensionShare,
}: PensionShareDataGridPropsType) => {
  const { t } = useTranslation();
  const columnKeys = pensionShare?.[0]
    ? Object.keys(pensionShare?.[0])
    : defaultColumnsKeys;
  const columns: GridColDef[] = columnKeys.map(
    (key: string, index: number) => ({
      field: key,
      headerName: `${t(`services.pensionShare.dataGrid.column.${key}.label`, {
        ns: 'services',
      })}`,
      flex: 1,
      renderCell: RenderCellExpand,
      sortable: false,
      align: index > 0 ? 'right' : 'left',
    })
  );

  return (
    <Box
      sx={{
        position: 'relative',
        height: { xs: '28rem', md: '100%' },
        width: '100%',
        maxHeight: '90%',
      }}
    >
      <DataGrid
        getRowId={({ personName, dpf, ppf, upf, dpfps }: DataGridRowType) =>
          '' + personName + dpf + ppf + upf + dpfps
        }
        rows={pensionShare ?? []}
        rowCount={pensionShare?.length ?? 0}
        pagination
        columns={columns}
        disableColumnMenu
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
          NoRowsOverlay,
        }}
        sx={{
          '& .MuiDataGrid-row--lastVisible': {
            color: 'red',
          },
          '.MuiDataGrid-cell--textRight > *': {
            justifyContent: 'end',
          },
        }}
      />
    </Box>
  );
};

export default PensionShareDataGrid;
