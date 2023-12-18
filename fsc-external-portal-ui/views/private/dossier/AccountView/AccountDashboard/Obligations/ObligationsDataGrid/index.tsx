import * as React from 'react';
import Box from '@mui/material/Box';
import {
  DataGrid,
  GridActionsCellItem,
  GridColDef,
  GridColumns,
  GridRowParams,
} from '@mui/x-data-grid';
import PaymentOutlinedIcon from '@mui/icons-material/PaymentOutlined';
import ReadMoreIcon from '@mui/icons-material/ReadMore';
import { useCallback, useContext, useEffect, useState } from 'react';
import { useAppDispatch, useAppSelector } from '../../../../../../../app/hooks';
import { useTranslation } from 'react-i18next';
import AccountContext, { AccountContextProps } from '../../../AccountContext';
import Typography from '@mui/material/Typography';
import { ObligationsResponseResultType } from '../../../../../../../contracts/types/obligations';
import { useFetchObligationsQuery } from '../../../../../../../store/api/obligationsSlice';
import { IUser, userSelector } from '../../../../../../../store/user';
import { IPnlStoreState, pnlSelector } from '../../../../../../../store/pnl';
import {
  IPersonStoreState,
  personSelector,
} from '../../../../../../../store/person';
import { IObligationsFilter } from '../../../../../../../contracts/interfaces/dossier/account/obligationsFilter';
import { OBLIGATION_STATUSES } from '../../../../../../../contracts/enums/dossier/account/obligations';
import { ROUTES } from '../../../../../../../constants/routes';
import { handleError } from '../../../../../../../utils/handlers/errorHandlers';
import RenderCellExpand from '../../../../../../public/PublicRegisterView/PublicRegisterDataGrid/RenderCellExpand';
import { useRouter } from 'next/router';
import useInitObligationsPayment from '../../../../../../../utils/serviceHelpers/dossier/account/obligations/useInitObligationsPayment';
import { setLoader } from '../../../../../../../store/loader';
import isObligationsPayButtonDisabled from '../../../../../../../utils/serviceHelpers/dossier/account/obligations/isObligationsPayButtonDisabled';
import CellAmount from './CellAmount';

const ObligationsDataGrid = ({
  filterParams,
}: {
  filterParams: IObligationsFilter;
}) => {
  const router = useRouter();
  const dispatch = useAppDispatch();
  const { t } = useTranslation(['dossier']);
  const { setSelectedObligations } =
    useContext<AccountContextProps>(AccountContext);
  const [skip, setSkip] = useState(true);
  const [page, setPage] = useState(1);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);
  const { id: personId } = useAppSelector<IPersonStoreState>(personSelector);
  const [initObligationsPayment] = useInitObligationsPayment();
  const { data } = useFetchObligationsQuery(
    {
      accessToken,
      personId: personId as number,
      pnlId: pnlId ?? null,
      page: page,
      filterParams: filterParams,
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

  const handlePay = useCallback(
    async (params: GridRowParams<ObligationsResponseResultType>) => {
      await dispatch(setLoader({ active: true }));

      const obligationValue = Number(params.row.chargeValueMain);

      try {
        if (isNaN(obligationValue))
          throw new Error('The obligation charge value is not a number');

        await initObligationsPayment({
          obligations: [
            {
              chargeId: params.row.chargeId,
              value: obligationValue,
            },
          ],
          alreadyCreatedAccessCode: params.row.paymentAccessCode,
        });
      } catch (error) {
        handleError(error);
      }
    },
    [dispatch, initObligationsPayment]
  );

  const columns: GridColDef[] = React.useMemo<
    GridColumns<ObligationsResponseResultType>
  >(
    () => [
      {
        field: 'chargeId',
        headerName: `${t('account.obligationsDataGrid.obligationId')}`,
        editable: false,
        sortable: false,
        minWidth: 100,
        flex: 1.5,
      },
      {
        field: 'chargeType',
        headerName: `${t('account.obligationsDataGrid.obligationType')}`,
        editable: false,
        sortable: false,
        minWidth: 300,
        flex: 4,
        renderCell: RenderCellExpand,
      },
      {
        field: 'chargeDate',
        headerName: `${t('account.obligationsDataGrid.obligationCreateDate')}`,
        editable: false,
        sortable: false,
        minWidth: 175,
        flex: 3,
      },
      {
        field: 'chargeValue',
        headerName: `${t('account.obligationsDataGrid.obligationValue')}`,
        editable: false,
        sortable: false,
        minWidth: 170,
        flex: 1.5,
        renderCell: (params) => (
          <Box sx={{ width: '100%', textAlign: 'right' }}>
            <CellAmount
              valueMain={params.row.chargeValueMain}
              valueSecond={params.row.chargeValueSecond}
              currencyMain={params.row.chargeValueMainCurrency}
              currencySecond={params.row.chargeValueSecondCurrency}
            />
          </Box>
        ),
      },
      {
        field: 'chargeDue',
        headerName: `${t(
          'account.obligationsDataGrid.obligationNotPaidValue'
        )}`,
        editable: false,
        sortable: false,
        minWidth: 170,
        flex: 1.5,
        renderCell: (params) => (
          <Box sx={{ width: '100%', textAlign: 'right' }}>
            <CellAmount
              title={t('obligation.chargePrincipal') + ':'}
              valueMain={params.row.currentPrincipalValueMain}
              valueSecond={params.row.currentPrincipalValueSecond}
              currencyMain={params.row.chargeValueMainCurrency}
              currencySecond={params.row.chargeValueSecondCurrency}
            />
            <CellAmount
              title={t('obligation.chargeInterest') + ':'}
              valueMain={params.row.currentInterestValueMain}
              valueSecond={params.row.currentInterestValueSecond}
              currencyMain={params.row.chargeValueMainCurrency}
              currencySecond={params.row.chargeValueSecondCurrency}
            />
          </Box>
        ),
      },
      {
        field: 'paymentDueDate',
        headerName: `${t('account.obligationsDataGrid.obligationDueDate')}`,
        editable: false,
        sortable: false,
        minWidth: 156,
        flex: 2,
      },
      {
        field: 'status',
        headerName: `${t('account.obligationsDataGrid.obligationStatus')}`,
        editable: false,
        sortable: false,
        minWidth: 150,
        flex: 2,
        renderCell: (params) => (
          <div>
            <Typography
              sx={{
                fontWeight: '600',
                color:
                  params.row.status === 'NOT_PAID'
                    ? '#ffaa00'
                    : params.row.status === 'OVERDUE'
                    ? '#b10538'
                    : '#059326',
              }}
            >
              {params.row.status && OBLIGATION_STATUSES[params.row.status]
                ? t(
                    `account.obligations.status.${
                      OBLIGATION_STATUSES[params.row.status]
                    }.label`,
                    {
                      ns: 'dossier',
                    }
                  )
                : params.row.status}
            </Typography>
          </div>
        ),
      },
      {
        field: 'actions',
        headerName: `${t('account.obligationsDataGrid.actions')}`,
        type: 'actions',
        minWidth: 110,
        flex: 1,
        getActions: (params) => [
          <GridActionsCellItem
            key={params.row.chargeId}
            icon={<ReadMoreIcon />}
            label={t(
              'account.obligationsDataGrid.actions.details.button.label'
            )}
            onClick={() => {
              router.push(
                ROUTES.USER.ACCOUNT_DETAILED_INFO + params.row.chargeId
              );
            }}
          />,
          <GridActionsCellItem
            key={`${params.row.chargeId}${params.row.chargeDate}`}
            icon={<PaymentOutlinedIcon />}
            disabled={isObligationsPayButtonDisabled(
              params.row.status as OBLIGATION_STATUSES
            )}
            label={t('account.obligationsDataGrid.actions.pay.button.label')}
            onClick={() => handlePay(params)}
          />,
        ],
      },
    ],
    [handlePay, router, t]
  );

  useEffect(() => {
    if (typeof personId !== 'undefined' && typeof pnlId !== 'undefined') {
      setSkip(false);
    }
  }, [personId, pnlId]);

  useEffect(() => {
    setPage(1);
  }, [filterParams]);

  return (
    <DataGrid
      getRowId={(row) => row.chargeId}
      rows={result}
      rowCount={totalNumberOfElements}
      rowsPerPageOptions={[pageSize]}
      getRowHeight={() => 'auto'}
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
      // TODO remove comment when Payment functionality is ready
      // checkboxSelection
      localeText={{
        MuiTablePagination: {
          labelDisplayedRows: ({ from, to, count }) =>
            `${from} - ${to} ${t('from', {
              ns: 'common',
            }).toLowerCase()} ${count}`,
          labelRowsPerPage: t('rowsPerPage.label', { ns: 'common' }),
        },
        footerRowSelected: (count) =>
          count !== 1
            ? `${count.toLocaleString()} ${t('selectedRows.label', {
                ns: 'common',
              })}`
            : `${count.toLocaleString()} ${t('selectedRow.label', {
                ns: 'common',
              })}`,
      }}
      components={{
        NoRowsOverlay: () => <></>,
      }}
      sx={{
        '.MuiDataGrid-iconButtonContainer': {
          visibility: 'visible',
        },
        '& .MuiDataGrid-sortIcon': {
          opacity: '1 !important',
        },
      }}
      onSelectionModelChange={(selectionModel) =>
        setSelectedObligations(selectionModel as number[])
      }
    />
  );
};

export default ObligationsDataGrid;
