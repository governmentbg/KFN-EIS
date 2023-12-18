import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { ChargesPaymentsType } from '../../../../../../../contracts/types/obligations';
import { useTranslation } from 'react-i18next';

const ObligationPayment = ({ payment }: { payment: ChargesPaymentsType }) => {
  const { t } = useTranslation(['dossier']);

  return (
    <Box
      sx={{
        display: 'inline-flex',
        flexWrap: 'wrap',
        width: '100%',
        mb: '0.5rem',
        p: '0 0.5rem',
        border: '1px solid #4c6280',
        borderLeftWidth: '1rem',
        borderRadius: '5px',
      }}
    >
      <Box
        sx={{
          width: '50%',
          pr: '1rem',
        }}
      >
        <Typography sx={{ color: '#4c6280', fontStyle: 'italic' }}>
          {t('obligation.paymentDate') + ':'}
        </Typography>
      </Box>
      <Box sx={{ width: '50%', textAlign: 'right' }}>
        <Typography sx={{ fontWeight: '600' }}>{payment.createdOn}</Typography>
      </Box>
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
          {payment.principalValueMain} {payment.currencyMainCode}
        </Typography>
        <Typography color="textSecondary" sx={{ fontSize: '0.875rem' }}>
          {payment.principalValueSecond} {payment.currencySecondCode}
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
          {payment.interestValueMain} {payment.currencyMainCode}
        </Typography>
        <Typography color="textSecondary" sx={{ fontSize: '0.875rem' }}>
          {payment.interestValueSecond} {payment.currencySecondCode}
        </Typography>
      </Box>
    </Box>
  );
};

export default ObligationPayment;
