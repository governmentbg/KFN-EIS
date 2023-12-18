import Box from '@mui/material/Box';
import ReadMoreIcon from '@mui/icons-material/ReadMore';
import Button from '@mui/material/Button';
import { DataGrid, GridColDef, GridEventListener } from '@mui/x-data-grid';
import { useTranslation } from 'react-i18next';
import { useFetchRequestedServicesQuery } from '../../../../../store/api/requestedServicesSlice';
import RenderCellExpand from '../../../../public/PublicRegisterView/PublicRegisterDataGrid/RenderCellExpand';
import { useEffect, useState } from 'react';
import { useAppSelector } from '../../../../../app/hooks/redux';
import { useRouter } from 'next/router';
import { IPersonStoreState, personSelector } from '../../../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../../../store/pnl';
import { IUser, userSelector } from '../../../../../store/user';
import { IRequestedServicesFilter } from '../../../../../contracts/interfaces/dossier/requestedServicesFilter';
import { IUserContext } from '../../../../../contracts/interfaces/userContext';
import { UserContext_Types } from '../../../../../contracts/enums/userContext';
import { ROUTES } from '../../../../../constants';
import Typography from '@mui/material/Typography';
import { SERVICE_STATUSES } from '../../../../../contracts/enums/dossier/services';
import { RequestedServicesResponseResultType } from '../../../../../contracts/types/requestedServices';

interface IRequestDynamicProps {
  personId: number | null;
  pnlId: number | null;
  page: number;
  filterParams: IRequestedServicesFilter;
}

const RequestedServicesDataGrid = ({
  filterParams,
  isReports,
}: {
  filterParams: IRequestedServicesFilter;
  isReports: boolean;
}) => {
  const [skip, setSkip] = useState(true);
  const [requestDynamicProps, setRequestDynamicProps] =
    useState<IRequestDynamicProps>({
      personId: null,
      pnlId: null,
      page: 1,
      filterParams,
    });
  const [selectedUser, setSelectedUser] = useState<IUserContext | undefined>(
    undefined
  );
  const { t } = useTranslation(['dossier']);
  const router = useRouter();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);
  const { userContexts } = useAppSelector<IUser>(userSelector);
  const profileUser = userContexts.find(
    (userContext: IUserContext) =>
      userContext.userContextType === UserContext_Types.ME
  );
  const { data } = useFetchRequestedServicesQuery(
    {
      accessToken,
      personId: requestDynamicProps.personId as number,
      pnlId: requestDynamicProps.pnlId,
      page: requestDynamicProps.page,
      filterParams: requestDynamicProps.filterParams,
      isReports,
    },
    {
      skip:
        skip ||
        (isReports && selectedUser?.pnlId === null) ||
        !requestDynamicProps.personId,
    }
  );

  const { totalNumberOfElements, currentPage, pageSize, result } = data || {
    result: [],
    totalNumberOfPages: 0,
    totalNumberOfElements: 0,
    currentPage: 1,
    pageSize: 30,
  };

  let columns: GridColDef<RequestedServicesResponseResultType>[] = [];

  columns = [
    {
      field: 'submissionDate',
      headerName: t('requestedServices.dataGrid.column.submissionDate.label'),
      flex: 0.2,
      minWidth: 170,
      sortable: false,
      renderCell: RenderCellExpand,
    },
    {
      field: 'serviceName',
      headerName: isReports
        ? t('requestedServices.dataGrid.column.reportType.label')
        : t('requestedServices.dataGrid.column.serviceName.label'),
      flex: 0.6,
      minWidth: isReports ? 200 : 320,
      sortable: false,
      renderCell: RenderCellExpand,
    },
    {
      field: 'incomingNumber',
      headerName: t('requestedServices.dataGrid.column.incomingNumber.label'),
      flex: 0.4,
      minWidth: 200,
      sortable: false,
      renderCell: RenderCellExpand,
    },
    ...(isReports
      ? [
          {
            field: 'reportPeriod',
            headerName: t(
              'requestedServices.dataGrid.column.reportPeriod.label'
            ),
            flex: 0.4,
            minWidth: 200,
            sortable: false,
            renderCell: (params: any) => (
              <div>
                <Typography>
                  <Typography
                    component="span"
                    sx={{
                      display: 'inline-block',
                      width: '2.25rem',
                      mr: '0.8rem',
                      fontWeight: '600',
                      fontStyle: 'italic',
                    }}
                  >
                    {t('requestedServices.dataGrid.column.periodFrom.label') +
                      ': '}
                  </Typography>
                  {params.row.reportDateFrom}
                </Typography>
                <Typography>
                  <Typography
                    component="span"
                    sx={{
                      display: 'inline-block',
                      width: '2.25rem',
                      mr: '0.8rem',
                      fontWeight: '600',
                      fontStyle: 'italic',
                    }}
                  >
                    {t('requestedServices.dataGrid.column.periodTo.label') +
                      ': '}
                  </Typography>
                  {params.row.reportDateTo}
                </Typography>
              </div>
            ),
          },
        ]
      : []),
    {
      field: 'status',
      headerName: t('requestedServices.dataGrid.column.status.label'),
      flex: 0.4,
      minWidth: 164,
      sortable: false,
      renderCell: (params) => (
        <div>
          <Typography
            sx={{
              fontWeight: '600',
              color: params.row.status === 'CORRECTION' ? '#b10538' : '',
            }}
          >
            {params.row.status && SERVICE_STATUSES[params.row.status]
              ? t(
                  `services.status.${SERVICE_STATUSES[params.row.status]}.label`
                )
              : params.row.status}
          </Typography>
        </div>
      ),
    },
  ];

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

  columns.push(buttonCol);

  const handleEvent: GridEventListener<'cellClick'> = (params) => {
    if (params.field !== 'detailedView') {
      return;
    }

    if (isReports) {
      router.push(ROUTES.USER.REPORTS_DETAILED_INFO + params.row.serviceId);
    } else {
      router.push(ROUTES.USER.SERVICES_DETAILED_INFO + params.row.serviceId);
    }
  };

  useEffect(() => {
    if (person.id && userContexts) {
      const selectedContext = userContexts.find(
        (userContext: IUserContext) =>
          userContext.personId === person.id && userContext.pnlId === pnl?.id
      );
      setSelectedUser(selectedContext);
    }
  }, [person.id, userContexts, pnl.id]);

  useEffect(() => {
    if (isReports && selectedUser?.pnlId === null) {
      setSkip(true);
    } else {
      setSkip(false);
    }
  }, [isReports, selectedUser?.pnlId]);

  useEffect(() => {
    if (typeof selectedUser !== 'undefined') {
      setRequestDynamicProps((prevDynamicPropsState: IRequestDynamicProps) => {
        const updatedProps: IRequestDynamicProps = {
          ...prevDynamicPropsState,
          page: 1,
          personId: selectedUser.personId,
          pnlId: selectedUser.pnlId,
          filterParams,
        };

        return updatedProps;
      });
    }
  }, [selectedUser, filterParams]);

  return (
    <Box sx={{ position: 'relative', height: '100%', width: '100%' }}>
      <DataGrid
        getRowId={(row) =>
          row.incomingNumber + row.submissionDate + row.serviceId + row.status
        }
        columnVisibilityModel={{ serviceId: false }}
        rows={result}
        rowCount={totalNumberOfElements}
        rowsPerPageOptions={[pageSize]}
        pagination
        page={currentPage - 1}
        pageSize={pageSize}
        paginationMode="server"
        onPageChange={(newPage) => {
          setRequestDynamicProps(
            (prevDynamicPropsState: IRequestDynamicProps) => {
              const updatedProps = {
                ...prevDynamicPropsState,
                page: newPage + 1,
              };

              return updatedProps;
            }
          );
        }}
        columns={columns}
        disableColumnMenu
        filterMode="server"
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
          NoRowsOverlay: () => <></>,
        }}
        onCellClick={handleEvent}
      />
    </Box>
  );
};

export default RequestedServicesDataGrid;
