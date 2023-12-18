import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useTranslation } from 'react-i18next';
import { IUser, userSelector } from '../../../../store/user';
import { useAppDispatch, useAppSelector } from '../../../../app/hooks';
import { useFetchRequestedServiceInfoQuery } from '../../../../store/api/requestedServiceInfoSlice';
import { IPersonStoreState, personSelector } from '../../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../../store/pnl';
import { IUserContext } from '../../../../contracts/interfaces/userContext';
import { UserContext_Types } from '../../../../contracts/enums/userContext';
import { ROUTES } from '../../../../constants';
import DataItem from '../../../../components/DataItem';
import FileComponent from './FileComponent';
import PageContentHeader from '../../shared/PageContentHeader';
import Button from '@mui/material/Button';
import {
  SERVICE_STATUSES,
  SERVICE_ALLOWED_OPERATION,
} from '../../../../contracts/enums/dossier/services';
import { IDocument } from '../../../../contracts/interfaces/document';
import { getKeyDataForCorrectionServiceRequest } from '../../../../store/serviceRequest';
import { handleError } from '../../../../utils/handlers/errorHandlers';

const RequestedServicesDetailedView = ({ isReport }: { isReport: boolean }) => {
  const dispatch = useAppDispatch();
  const [skip, setSkip] = useState(true);
  const [selectedContextName, setSelectedContextName] = useState('');
  const [isProfileUserContext, setIsProfileUserContext] = useState(true);
  const { t } = useTranslation(['dossier']);
  const { userContexts } = useAppSelector<IUser>(userSelector);
  const router = useRouter();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);
  const { data } = useFetchRequestedServiceInfoQuery(
    {
      accessToken,
      serviceRequestId: router?.query?.serviceRequestId?.toString() ?? '',
      isReport,
    },
    { skip }
  );

  const {
    serviceRequestId,
    allowedOperation,
    personId,
    serviceName,
    userWhoSubmitted,
    incomingNumber,
    submissionDate,
    submissionMethod,
    reportDateFrom,
    reportDateTo,
    status,
    statusDate,
    versionId,
    mainDocument,
    attachedDocuments,
  } = data || {
    serviceRequestId: '',
    allowedOperation: null,
    personId: '',
    serviceName: '',
    userWhoSubmitted: '',
    incomingNumber: '',
    submissionDate: '',
    submissionMethod: '',
    reportDateFrom: '',
    reportDateTo: '',
    status: '',
    statusDate: '',
    versionId: '',
    mainDocument: [],
    attachedDocuments: [],
  };

  const openServiceForm = async () => {
    if (!allowedOperation) return;
    try {
      const formData = await dispatch(
        getKeyDataForCorrectionServiceRequest({
          accessToken,
          serviceRequestId,
          allowedOperation,
          pnlId: pnl.id?.toString(),
          documentTypeId: mainDocument[0].documentTypeId,
        })
      ).unwrap();

      router.push(
        ROUTES.SERVICES.FORM +
          `/${formData.catalogElementId}/?keyData=${formData.keyData}`
      );
    } catch (error: any) {
      handleError(error);
    }
  };

  useEffect(() => {
    if (
      typeof router.query.serviceRequestId !== 'undefined' &&
      !serviceRequestId
    ) {
      setSkip(false);
    }
  }, [router.query.serviceRequestId, serviceRequestId]);

  useEffect(() => {
    if (serviceRequestId) {
      setSkip(true);
    }
  }, [serviceRequestId]);

  useEffect(() => {
    const selectedContext = userContexts.find(
      (userContext: IUserContext) =>
        userContext.personId === person.id && userContext.pnlId === pnl.id
    );

    setSelectedContextName(selectedContext?.name ?? '');
    setIsProfileUserContext(
      selectedContext?.userContextType === UserContext_Types.ME
    );

    if (personId && personId !== person.id) {
      if (!isReport) {
        router.replace(ROUTES.USER.SERVICES);
      } else if (isReport && isProfileUserContext) {
        router.replace(ROUTES.USER.PROFILE);
      } else {
        router.replace(ROUTES.USER.REPORTS);
      }
    }
  }, [
    person.id,
    pnl.id,
    userContexts,
    router,
    personId,
    isReport,
    isProfileUserContext,
  ]);

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        position: 'relative',
        height: { xs: 'auto', sm: '100%' },
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
        {isReport
          ? t('requestedServiceInfo.report.title.label')
          : t('requestedServiceInfo.service.title.label')}
      </PageContentHeader>
      <Paper
        elevation={2}
        sx={{
          display: 'flex',
          flexDirection: 'column',
          height: { xs: 'auto', lg: '100%' },
          p: '1rem',
          m: '1rem',
        }}
      >
        <Stack
          flexDirection="row"
          sx={{
            p: '0.5rem 0rem',
            flexWrap: 'wrap',
            alignItems: 'center',
            justifyContent: 'space-between',
            marginBottom: '1rem',
          }}
        >
          <Typography
            component="h2"
            variant="h3"
            color="primary.main"
            tabIndex={0}
          >
            {serviceName}
          </Typography>
          {isReport &&
            allowedOperation &&
            SERVICE_ALLOWED_OPERATION[allowedOperation] && (
              <Button
                aria-label={t(
                  `requestedServices.correctionButton.${SERVICE_ALLOWED_OPERATION[allowedOperation]}.label`
                )}
                variant="contained"
                color="primary"
                size="small"
                onClick={openServiceForm}
              >
                {t(
                  `requestedServices.correctionButton.${SERVICE_ALLOWED_OPERATION[allowedOperation]}.label`
                )}
              </Button>
            )}
        </Stack>
        <Box>
          <Box
            sx={{
              display: 'inline-flex',
              flexWrap: 'wrap',
              width: { xs: '100%', sm: '50%' },
            }}
          >
            <Box
              sx={{
                width: { xs: '100%', lg: '50%' },
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem
                title={t(
                  'requestedServices.dataGrid.column.incomingNumber.label'
                )}
                value={incomingNumber}
              />
            </Box>
            <Box
              sx={{
                width: { xs: '100%', lg: '50%' },
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem
                title={t(
                  'requestedServices.dataGrid.column.submissionDate.label'
                )}
                value={submissionDate}
              />
            </Box>
            <Box
              sx={{
                display: 'inline-flex',
                flexWrap: 'wrap',
                ...(isReport
                  ? { width: '100%' }
                  : { width: { xs: '100%', lg: '50%' } }),
              }}
            >
              <Box
                sx={{
                  ...(isReport
                    ? { width: { xs: '100%', lg: '50%' } }
                    : { width: '100%' }),
                  pr: { xs: '0', sm: '1rem' },
                  mb: '1rem',
                }}
              >
                <DataItem
                  title={t('requestedServices.dataGrid.column.status.label')}
                >
                  <Typography
                    sx={{
                      fontWeight: '600',
                      color: status === 'CORRECTION' ? '#b10538' : '',
                    }}
                  >
                    {status && SERVICE_STATUSES[status]
                      ? t(`services.status.${SERVICE_STATUSES[status]}.label`)
                      : status}
                  </Typography>
                </DataItem>
              </Box>
              <Box
                sx={{
                  width: { xs: '100%', lg: '50%' },
                  pr: { xs: '0', sm: '1rem' },
                  mb: '1rem',
                }}
              >
                {isReport && (
                  <DataItem
                    title={t(
                      'requestedServices.dataGrid.column.statusDate.label'
                    )}
                    value={statusDate}
                  />
                )}
              </Box>
            </Box>
            {isReport && (
              <Box
                sx={{
                  display: 'inline-flex',
                  flexWrap: 'wrap',
                  width: '100%',
                }}
              >
                <Box
                  sx={{
                    width: { xs: '100%', lg: '50%' },
                    pr: { xs: '0', sm: '1rem' },
                    mb: '1rem',
                  }}
                >
                  <DataItem
                    title={t(
                      'requestedServices.dataGrid.column.reportDateFrom.label'
                    )}
                    value={reportDateFrom}
                  />
                </Box>
                <Box
                  sx={{
                    width: { xs: '100%', lg: '50%' },
                    pr: { xs: '0', sm: '1rem' },
                    mb: '1rem',
                  }}
                >
                  <DataItem
                    title={t(
                      'requestedServices.dataGrid.column.reportDateTo.label'
                    )}
                    value={reportDateTo}
                  />
                </Box>
              </Box>
            )}
            <Box
              sx={{
                width: { xs: '100%', lg: '50%' },
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem
                title={t(
                  'requestedServices.dataGrid.column.submissionMethod.label'
                )}
                value={submissionMethod}
              />
            </Box>
            {isReport && (
              <Box
                sx={{
                  width: { xs: '100%', lg: '50%' },
                  pr: { xs: '0', sm: '1rem' },
                  mb: '1rem',
                }}
              >
                <DataItem
                  title={t('requestedServices.reports.version.label')}
                  value={versionId}
                />
              </Box>
            )}
            {!isProfileUserContext && (
              <Box
                sx={{
                  width: '100%',
                  pr: { xs: '0', sm: '1rem' },
                  mb: '1rem',
                }}
              >
                <DataItem
                  title={t('requestedServiceInfo.pnl')}
                  value={selectedContextName}
                />
              </Box>
            )}
            <Box
              sx={{
                width: '100%',
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem
                title={t(
                  'requestedServices.dataGrid.column.complainantName.label'
                )}
                value={userWhoSubmitted}
              />
            </Box>
          </Box>
          <Box
            sx={{
              display: 'inline-flex',
              flexWrap: 'wrap',
              width: { xs: '100%', sm: '50%' },
            }}
          >
            <Box
              sx={{
                width: { xs: '100%', lg: '50%' },
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <Typography
                component="h3"
                color="primary.main"
                tabIndex={0}
                sx={{
                  mb: '0.5rem',
                  borderBottom: '1px solid #4c6280',
                  backgroundColor: '#eff6fe',
                }}
              >
                {t('requestedServiceInfo.mainDocument')}
              </Typography>
              {mainDocument.length > 0 && mainDocument[0].fileRef && (
                <FileComponent attachedItem={mainDocument[0]} />
              )}
            </Box>
            <Box
              sx={{
                width: { xs: '100%', lg: '50%' },
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <Typography
                component="h3"
                color="primary.main"
                tabIndex={0}
                sx={{
                  mb: '0.5rem',
                  borderBottom: '1px solid #4c6280',
                  backgroundColor: '#eff6fe',
                }}
              >
                {t('requestedServiceInfo.attachedDocuments')}
              </Typography>
              {attachedDocuments.length > 0 &&
                attachedDocuments.map(
                  (attachedItem: IDocument) =>
                    attachedItem.fileRef && (
                      <FileComponent
                        key={attachedItem.id}
                        attachedItem={attachedItem}
                      />
                    )
                )}
            </Box>
          </Box>
        </Box>
      </Paper>
    </Box>
  );
};
export default RequestedServicesDetailedView;
