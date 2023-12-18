import { useCallback, useContext, useState } from 'react';
import { useAppSelector } from '../../../../../app/hooks';
import { ACCOUNT_PROCESSES_TYPES } from '../../../../../contracts/enums/dossier/account';
import { currencyCodeSelector } from '../../../../../store/application';
import Box from '@mui/material/Box';
import Stack from '@mui/material/Stack';
import PageContentHeader from '../../../shared/PageContentHeader';
import AccountContext, { AccountContextProps } from '../AccountContext';
import ObligationsField from './ObligationsField';
import PayButton from './PayButton';
import Obligations from './Obligations';
import { useFetchTotalNotPaidObligationsQuery } from '../../../../../store/api/obligationsSlice';
import { IUser, userSelector } from '../../../../../store/user';
import { IPnlStoreState, pnlSelector } from '../../../../../store/pnl';
import { IPersonStoreState, personSelector } from '../../../../../store/person';

const AccountDashboard = ({ title }: { title: string }) => {
  const { selectedObligations, setActiveProcessStep } =
    useContext<AccountContextProps>(AccountContext);
  const currencyCode = useAppSelector<string>(currencyCodeSelector);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);
  const { data } = useFetchTotalNotPaidObligationsQuery({
    accessToken,
    personId: person.id,
    pnlId: pnl.id ? pnl.id : null,
  });

  const { totalDueValue, totalDueCurrency } = data || {
    totalDueValue: 0.0,
    totalDueCurrency: currencyCode,
  };

  return (
    <Box sx={{ height: '100%' }}>
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
        {title}
      </PageContentHeader>

      <Stack sx={{ p: '1rem', height: '100%' }}>
        <Box
          sx={{
            display: 'flex',
            flexDirection: { xs: 'column', sm: 'column', md: 'row' },
          }}
        >
          <ObligationsField
            currencyCode={totalDueCurrency}
            balance={totalDueValue}
          />
        </Box>

        <Stack sx={{ mt: '1rem', height: '100%' }}>
          <Obligations />
        </Stack>

        {selectedObligations.length > 0 && (
          <PayButton
            buttonProps={{
              onClick: () =>
                setActiveProcessStep({
                  process: ACCOUNT_PROCESSES_TYPES.PAYMENT,
                  step: 1,
                }),
              sx: {
                width: 'fit-content',
                p: '0.25rem 2rem',
                marginTop: '1rem',
                textTransform: 'none',
                alignSelf: 'flex-end',
              },
            }}
          />
        )}
      </Stack>
    </Box>
  );
};

export default AccountDashboard;
