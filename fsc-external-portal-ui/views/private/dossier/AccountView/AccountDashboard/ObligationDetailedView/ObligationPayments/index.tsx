import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import DataItem from '../../../../../../../components/DataItem';
import { ChargesPaymentsType } from '../../../../../../../contracts/types/obligations';
import { useTranslation } from 'react-i18next';
import ObligationPayment from '../ObligationPayment';

const ObligationPayments = ({
  chargesPayments,
}: {
  chargesPayments: ChargesPaymentsType[];
}) => {
  const { t } = useTranslation(['dossier']);

  return (
    <Box
      sx={{
        width: '100%',
        pr: { xs: '0', sm: '1rem' },
        mb: '1rem',
      }}
    >
      <DataItem title={t('obligation.chargesPayments')}>
        <>
          {chargesPayments.map((payment: ChargesPaymentsType) => (
            <ObligationPayment key={payment.id} payment={payment} />
          ))}
        </>
      </DataItem>
    </Box>
  );
};

export default ObligationPayments;
