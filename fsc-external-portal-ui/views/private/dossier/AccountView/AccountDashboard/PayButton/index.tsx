import Button, { ButtonProps } from '@mui/material/Button';
import { useTranslation } from 'react-i18next';

const PayButton = ({ buttonProps }: { buttonProps?: ButtonProps }) => {
  const { t } = useTranslation(['dossier']);
  return (
    <Button variant="contained" {...buttonProps}>
      {t('account.dashboard.payButton.label')}
    </Button>
  );
};

export default PayButton;
