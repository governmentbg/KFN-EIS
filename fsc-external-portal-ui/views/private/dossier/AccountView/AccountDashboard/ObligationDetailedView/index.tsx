import { useCallback, useEffect, useState } from 'react';
import {
  useAppDispatch,
  useAppSelector,
} from '../../../../../../app/hooks/redux';
import { IUser, userSelector } from '../../../../../../store/user';
import { useFetchObligationDetailedInfoQuery } from '../../../../../../store/api/obligationsSlice';
import { useRouter } from 'next/router';
import Box from '@mui/material/Box';
import PageContentHeader from '../../../../shared/PageContentHeader';
import { useTranslation } from 'react-i18next';
import Paper from '@mui/material/Paper';
import DataItem from '../../../../../../components/DataItem';
import Typography from '@mui/material/Typography';
import { OBLIGATION_STATUSES } from '../../../../../../contracts/enums/dossier/account/obligations';
import ObligationPayments from './ObligationPayments';
import {
  IPersonStoreState,
  personSelector,
} from '../../../../../../store/person';
import { ROUTES } from '../../../../../../constants/routes';
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import { handleError } from '../../../../../../utils/handlers/errorHandlers';
import { setLoader } from '../../../../../../store/loader';
import useInitObligationsPayment from '../../../../../../utils/serviceHelpers/dossier/account/obligations/useInitObligationsPayment';
import isObligationsPayButtonDisabled from '../../../../../../utils/serviceHelpers/dossier/account/obligations/isObligationsPayButtonDisabled';
import { IPnlStoreState, pnlSelector } from '../../../../../../store/pnl';

const ObligationDetailedView = () => {
  const dispatch = useAppDispatch();
  const [skip, setSkip] = useState(true);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);
  const router = useRouter();
  const { t } = useTranslation(['dossier']);
  const [initObligationsPayment] = useInitObligationsPayment();
  const { data } = useFetchObligationDetailedInfoQuery(
    {
      accessToken,
      chargeId: router?.query?.chargeId?.toString() ?? '',
      personId: person.id as number,
      pnlId: pnl.id ?? null,
    },
    { skip }
  );

  const {
    personId,
    chargeId,
    createdOn,
    chargeTypeName,
    serviceName,
    dueDate,
    periodFrom,
    periodTo,
    status,
    principalValueMain,
    interestValueMain,
    currencyMainCode,
    principalValueSecond,
    interestValueSecond,
    currencySecondCode,
    notPaidMainPrincipal,
    notPaidMainInterest,
    notPaidMainTotal,
    notPaidSecondPrincipal,
    notPaidSecondInterest,
    notPaidSecondTotal,
    chargesPayments,
    paymentAccessCode,
  } = data || {
    personId: '',
    chargeId: '',
    createdOn: '',
    chargeTypeName: '',
    serviceName: '',
    dueDate: '',
    periodFrom: '',
    periodTo: '',
    status: '',
    principalValueMain: null,
    interestValueMain: null,
    currencyMainCode: '',
    principalValueSecond: null,
    interestValueSecond: null,
    currencySecondCode: '',
    notPaidMainPrincipal: null,
    notPaidMainInterest: null,
    notPaidMainTotal: null,
    notPaidSecondPrincipal: null,
    notPaidSecondInterest: null,
    notPaidSecondTotal: null,
    chargesPayments: [],
    paymentAccessCode: '',
  };

  const handlePay = useCallback(async () => {
    dispatch(setLoader({ active: true }));

    try {
      await initObligationsPayment({
        obligations: [
          {
            chargeId: Number(chargeId),
            value: Number(principalValueMain) + Number(interestValueMain),
          },
        ],
        alreadyCreatedAccessCode: paymentAccessCode,
      });
    } catch (error) {
      handleError(error);
    }
  }, [
    chargeId,
    dispatch,
    initObligationsPayment,
    interestValueMain,
    paymentAccessCode,
    principalValueMain,
  ]);

  useEffect(() => {
    if (
      typeof router.query.chargeId !== 'undefined' &&
      typeof person.id !== 'undefined' &&
      typeof pnl.id !== 'undefined' &&
      !chargeId
    ) {
      setSkip(false);
    }
  }, [router.query.chargeId, person.id, pnl.id, chargeId]);

  useEffect(() => {
    if (personId && personId !== person.id) {
      router.replace(ROUTES.USER.ACCOUNT);
    }
  }, [personId, person.id, router]);

  useEffect(() => {
    if (chargeId) {
      setSkip(true);
    }
  }, [chargeId]);

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
        {t('obligation.title.label')}
      </PageContentHeader>
      <Paper
        elevation={2}
        sx={{
          display: 'flex',
          flexDirection: 'column',
          p: '1rem',
          m: '1rem',
        }}
      >
        <Box
          sx={{
            display: 'inline-flex',
            flexWrap: 'wrap',
            width: '100%',
          }}
        >
          <Box
            sx={{
              display: 'inline-flex',
              flexWrap: 'wrap',
              width: { xs: '100%', sm: '50%' },
            }}
          >
            <Box
              sx={{
                width: '100%',
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem
                title={t('obligation.chargeId')}
                value={chargeId.toString()}
              />
            </Box>
            <Box
              sx={{
                width: '100%',
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem
                title={t('obligation.chargeType')}
                value={chargeTypeName}
              />
            </Box>
            <Box
              sx={{
                width: '100%',
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem
                title={t('obligation.serviceName')}
                value={serviceName}
              />
            </Box>
            <Box
              sx={{
                width: { xs: '100%', lg: '50%' },
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem title={t('obligation.chargeDate')} value={createdOn} />
            </Box>
            <Box
              sx={{
                width: { xs: '100%', lg: '50%' },
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem
                title={t('obligation.paymentDueDate')}
                value={dueDate}
              />
            </Box>
            <Box
              sx={{
                width: '100%',
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem title={t('obligation.period')}>
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
                      pr: '1rem',
                    }}
                  >
                    <Typography
                      component="span"
                      sx={{
                        color: '#4c6280',
                        fontStyle: 'italic',
                      }}
                    >
                      {t('obligation.periodFrom') + ': '}
                    </Typography>
                    <Typography component="span">{periodFrom}</Typography>
                  </Box>
                  <Box
                    sx={{
                      width: { xs: '100%', lg: '50%' },
                      pr: '1rem',
                      pl: { xs: '0', lg: '0.5rem' },
                    }}
                  >
                    <Typography
                      component="span"
                      sx={{
                        color: '#4c6280',
                        fontStyle: 'italic',
                      }}
                    >
                      {t('obligation.periodTo') + ': '}
                    </Typography>
                    <Typography component="span">{periodTo}</Typography>
                  </Box>
                </Box>
              </DataItem>
            </Box>
            <Box
              sx={{
                width: { xs: '100%', lg: '50%' },
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem title={t('obligation.status')}>
                <Typography
                  sx={{
                    fontWeight: '600',
                    color:
                      status === 'NOT_PAID'
                        ? '#ffaa00'
                        : status === 'OVERDUE'
                        ? '#b10538'
                        : '#059326',
                  }}
                >
                  {status && OBLIGATION_STATUSES[status]
                    ? t(
                        `account.obligations.status.${OBLIGATION_STATUSES[status]}.label`
                      )
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
              <DataItem title={t('obligation.chargeValueMain')}>
                <Box
                  sx={{
                    display: 'inline-flex',
                    flexWrap: 'wrap',
                    width: '100%',
                  }}
                >
                  <Box
                    sx={{
                      width: '50%',
                      pr: '1rem',
                    }}
                  >
                    <Typography sx={{ color: '#4c6280', fontStyle: 'italic' }}>
                      {t('obligation.chargePrincipal') + ':'}
                    </Typography>
                  </Box>
                  <Box sx={{ width: '50%', textAlign: 'right' }}>
                    <Typography sx={{ fontWeight: '600' }}>
                      {principalValueMain} {currencyMainCode}
                    </Typography>
                    <Typography
                      color="textSecondary"
                      sx={{ fontSize: '0.875rem' }}
                    >
                      {principalValueSecond} {currencySecondCode}
                    </Typography>
                  </Box>
                  <Box
                    sx={{
                      width: '50%',
                      pr: '1rem',
                    }}
                  >
                    <Typography sx={{ color: '#4c6280', fontStyle: 'italic' }}>
                      {t('obligation.chargeInterest') + ':'}
                    </Typography>
                  </Box>
                  <Box sx={{ width: '50%', textAlign: 'right' }}>
                    <Typography sx={{ fontWeight: '600' }}>
                      {interestValueMain} {currencyMainCode}
                    </Typography>
                    <Typography
                      color="textSecondary"
                      sx={{ fontSize: '0.875rem' }}
                    >
                      {interestValueSecond} {currencySecondCode}
                    </Typography>
                  </Box>
                </Box>
              </DataItem>
            </Box>
            <Box
              sx={{
                width: '100%',
                pr: { xs: '0', sm: '1rem' },
                mb: '1rem',
              }}
            >
              <DataItem title={t('obligation.chargeNotPaid')}>
                <Box
                  sx={{
                    display: 'inline-flex',
                    flexWrap: 'wrap',
                    width: '100%',
                  }}
                >
                  <Box
                    sx={{
                      width: { xs: '50%', lg: '30%' },
                      pr: '1rem',
                    }}
                  >
                    <Typography sx={{ color: '#4c6280', fontStyle: 'italic' }}>
                      {t('obligation.chargePrincipal') + ':'}
                    </Typography>
                  </Box>
                  <Box
                    sx={{ width: { xs: '50%', lg: '70%' }, textAlign: 'right' }}
                  >
                    <Typography sx={{ fontWeight: '600' }}>
                      {notPaidMainPrincipal} {currencyMainCode}
                    </Typography>
                    <Typography
                      color="textSecondary"
                      sx={{ fontSize: '0.875rem' }}
                    >
                      {notPaidSecondPrincipal} {currencySecondCode}
                    </Typography>
                  </Box>
                  <Box
                    sx={{
                      width: { xs: '50%', lg: '30%' },
                      pr: '1rem',
                    }}
                  >
                    <Typography sx={{ color: '#4c6280', fontStyle: 'italic' }}>
                      {t('obligation.chargeInterest') + ':'}
                    </Typography>
                  </Box>
                  <Box
                    sx={{ width: { xs: '50%', lg: '70%' }, textAlign: 'right' }}
                  >
                    <Typography sx={{ fontWeight: '600' }}>
                      {notPaidMainInterest} {currencyMainCode}
                    </Typography>
                    <Typography
                      color="textSecondary"
                      sx={{ fontSize: '0.875rem' }}
                    >
                      {notPaidSecondInterest} {currencySecondCode}
                    </Typography>
                  </Box>
                  <Box
                    sx={{
                      width: { xs: '50%', lg: '30%' },
                      pr: '1rem',
                      borderTop: '1px solid #4c6280',
                    }}
                  >
                    <Typography
                      sx={{
                        fontWeight: '600',
                        color: '#4c6280',
                        fontStyle: 'italic',
                      }}
                    >
                      {t('obligation.chargeTotal') + ':'}
                    </Typography>
                  </Box>
                  <Box
                    sx={{
                      width: { xs: '50%', lg: '70%', textAlign: 'right' },
                      borderTop: '1px solid #4c6280',
                    }}
                  >
                    <Typography sx={{ fontWeight: '600', color: '#b10538' }}>
                      {notPaidMainTotal} {currencyMainCode}
                    </Typography>
                    <Typography sx={{ fontSize: '0.875rem', color: '#c85073' }}>
                      {notPaidSecondTotal} {currencySecondCode}
                    </Typography>
                  </Box>
                </Box>
              </DataItem>
            </Box>
          </Box>

          <Stack
            sx={{
              display: 'inline-flex',
              flexWrap: 'wrap',
              justifyContent: 'space-between',
              width: { xs: '100%', sm: '50%' },
            }}
          >
            <ObligationPayments chargesPayments={chargesPayments} />
            <Button
              variant="contained"
              disabled={isObligationsPayButtonDisabled(
                status as OBLIGATION_STATUSES
              )}
              onClick={handlePay}
            >
              {t('account.obligationsDataGrid.actions.pay.button.label')}
            </Button>
          </Stack>
        </Box>
      </Paper>
    </Box>
  );
};

export default ObligationDetailedView;
