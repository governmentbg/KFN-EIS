import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useTranslation } from 'react-i18next';
import PageContentHeader from '../../shared/PageContentHeader';
import RequestedServicesFilter from './RequestedServicesFilter';
import RequestedServicesDataGrid from './RequestedServicesDataGrid';
import { IRequestedServicesFilter } from '../../../../contracts/interfaces/dossier/requestedServicesFilter';
import { IUser, userSelector } from '../../../../store/user';
import { useAppSelector } from '../../../../app/hooks';
import { IPersonStoreState, personSelector } from '../../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../../store/pnl';
import { IUserContext } from '../../../../contracts/interfaces/userContext';
import { UserContext_Types } from '../../../../contracts/enums/userContext';
import { ROUTES } from '../../../../constants';

const RequestedServicesView = ({ isReports }: { isReports: boolean }) => {
  const [filterParams, setFilterParams] = useState<IRequestedServicesFilter>({
    serviceName: null,
    incomingNumber: null,
    submissionDate: null,
    ...(isReports && {
      reportDateFrom: null,
      reportDateTo: null,
    }),
    status: [],
  });
  const [selectedContextName, setSelectedContextName] = useState('');
  const { t } = useTranslation(['dossier']);
  const { userContexts } = useAppSelector<IUser>(userSelector);
  const router = useRouter();
  const person = useAppSelector<IPersonStoreState>(personSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);

  const updateFilterParams = (newParams: IRequestedServicesFilter) => {
    setFilterParams((prevState) => {
      return { ...prevState, ...newParams };
    });
  };

  useEffect(() => {
    const selectedContext = userContexts.find(
      (userContext: IUserContext) =>
        userContext.personId === person.id && userContext.pnlId === pnl.id
    );

    if (!isReports || (isReports && selectedContext?.pnlId !== null)) {
      setSelectedContextName(selectedContext?.name ?? '');
    } else {
      router.replace(ROUTES.USER.PROFILE);
    }
  }, [person.id, pnl.id, userContexts, isReports, router]);

  useEffect(() => {
    updateFilterParams({
      serviceName: null,
      incomingNumber: null,
      submissionDate: null,
      ...(isReports && {
        reportDateFrom: null,
        reportDateTo: null,
      }),
      status: [],
    });
  }, [isReports, person.id]);

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        position: 'relative',
        height: '100%',
        width: '100%',
      }}
    >
      <PageContentHeader
        rootProps={{
          sx: {
            backgroundColor: (t) => t.palette.background.paper,
            p: '1rem',
          },
        }}
        typographyProps={{
          component: 'h1',
          variant: 'h2',
          tabIndex: 0,
        }}
      >
        {isReports
          ? t('requestedServices.reports.title.label')
          : t('requestedServices.services.title.label')}
      </PageContentHeader>

      <Paper
        elevation={2}
        sx={{
          display: 'flex',
          flexDirection: 'column',
          height: '100%',
          p: '1rem',
          m: '1rem',
        }}
      >
        <Stack flexDirection="row" sx={{ p: '0.5rem 0rem', flexWrap: 'wrap' }}>
          <Typography
            component="h2"
            variant="h3"
            color="primary.main"
            tabIndex={0}
            sx={{
              alignSelf: 'center',
              paddingBottom: '0.5rem',
              paddingRight: '0.5rem',
            }}
          >
            {isReports
              ? t('requestedServices.reports.subTitle.label') +
                ' - ' +
                selectedContextName
              : t('requestedServices.services.subTitle.label') +
                ' - ' +
                selectedContextName}
          </Typography>

          <RequestedServicesFilter
            isReports={isReports}
            filterParams={filterParams}
            updateFilterParams={updateFilterParams}
          />
        </Stack>

        <RequestedServicesDataGrid
          isReports={isReports}
          filterParams={filterParams}
        />
      </Paper>
    </Box>
  );
};
export default RequestedServicesView;
