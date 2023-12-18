import * as React from 'react';
import Box from '@mui/material/Box';
import { v4 as uuidv4 } from 'uuid';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { SectionDataGridViewPropsType } from '../../../../../contracts/types/publicRegisterPnl/details/dataGrid';
import { useTranslation } from 'react-i18next';
import getRenderCellBySectionId from '../../../../../utils/serviceHelpers/publicRegister/detailedView/getRenderCellBySectionId';
import filterAndSortColumns from '../../../../../utils/serviceHelpers/publicRegister/detailedView/filterAndSortColumns';
import { useAppDispatch } from '../../../../../app/hooks';

const SectionDataGridView = ({
  sectionId,
  rows,
  columnDefinitions,
  tableName,
}: SectionDataGridViewPropsType) => {
  const { t } = useTranslation('publicRegisterDetailedView');
  const dispatch = useAppDispatch();

  const rowsWithSortedColumns = filterAndSortColumns(
    rows,
    columnDefinitions,
    tableName
  );

  if (Object.keys(rowsWithSortedColumns).length === 0) return null;

  const columns: GridColDef[] = Object.keys(
    rowsWithSortedColumns?.[0] ?? []
  ).map((key: string) => ({
    field: key,
    headerName: t(
      `publicRegister.detailedView.section.${sectionId}.${key}.label`
    ),
    flex: 1,
    renderCell: (params) =>
      getRenderCellBySectionId(params, key, sectionId, dispatch),
  }));

  return (
    <Box sx={{ height: 'auto', width: '100%', mt: 1, mb: 1 }}>
      <DataGrid
        rows={rows?.map((row: any) => ({ uid: uuidv4(), ...row }))}
        columns={columns}
        getRowId={(row) => row.uid}
        autoHeight
        pageSize={rows?.length <= 25 ? 5 : 10}
        rowsPerPageOptions={[rows?.length <= 25 ? 5 : 10]}
        disableColumnMenu={true}
        disableColumnSelector={true}
        localeText={{
          MuiTablePagination: {
            labelDisplayedRows: ({ from, to, count }) =>
              `${from} - ${to} ${t('from', {
                ns: 'common',
              }).toLowerCase()} ${count}`,
            labelRowsPerPage: t('rowsPerPage.label', { ns: 'common' }),
          },
        }}
      />
    </Box>
  );
};

export default SectionDataGridView;
