import Box from '@mui/material/Box';
import { useMemo, useState } from 'react';
import Payment from './Payment';
import AccountDashboard from './AccountDashboard';
import { useTranslation } from 'react-i18next';
import AccountContext from './AccountContext';
import { ACCOUNT_PROCESSES_TYPES } from '../../../../contracts/enums/dossier/account';
import { IActiveAccountProcessStep } from '../../../../contracts/interfaces/dossier/account';

const AccountView = () => {
  const { t } = useTranslation(['dossier']);
  const [activeProcessStep, setActiveProcessStep] = useState<
    IActiveAccountProcessStep | undefined
  >(undefined);
  const [selectedObligations, setSelectedObligations] = useState<number[]>([]);

  const processesStepLength = useMemo(
    () =>
      new Map([
        [ACCOUNT_PROCESSES_TYPES.PAYMENT, 3],
        [ACCOUNT_PROCESSES_TYPES.FUNDING, 3],
      ]),
    []
  );

  const handleNext = () => {};

  const handleBack = () => {};

  const renderStep = (activeStep: IActiveAccountProcessStep | undefined) => {
    if (!activeStep) return <AccountDashboard title={t('obligations')} />;

    if (activeStep.process === ACCOUNT_PROCESSES_TYPES.PAYMENT) {
      switch (activeStep.step) {
        case 1:
          return <Payment title={t('account.payment.title.label')} />;
        default:
          return null;
      }
    }

    if (activeStep.process === ACCOUNT_PROCESSES_TYPES.FUNDING) {
      switch (activeStep.step) {
        case 1:
          return <>Funding process</>;
        default:
          return null;
      }
    }
  };
  return (
    <AccountContext.Provider
      value={{
        activeProcessStep,
        setActiveProcessStep,
        selectedObligations,
        setSelectedObligations,
        handleNext,
        handleBack,
      }}
    >
      <Box sx={{ height: '90%' }}>{renderStep(activeProcessStep)}</Box>
    </AccountContext.Provider>
  );
};
export default AccountView;
