import Button from '@mui/material/Button';
import { useTranslation } from 'react-i18next';
import { ROUTES } from '../../../../constants';

const GoToHomeButton = () => {
  const { t } = useTranslation(['navigation']);

  return (
    <Button
      href={ROUTES.HOME}
      variant="contained"
      color="primary"
      LinkComponent={'a'}
      aria-label={t('navigation.home')}
      sx={{
        width: { xs: '50%', sm: 'auto' },
        textAlign: { xs: 'center' },
        marginLeft: 'auto',
      }}
    >
      {t('navigation.home')}
    </Button>
  );
};

export default GoToHomeButton;
