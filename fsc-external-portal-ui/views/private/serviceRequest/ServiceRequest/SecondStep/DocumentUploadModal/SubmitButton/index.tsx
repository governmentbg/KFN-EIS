import Button from '@mui/material/Button';
import { useTranslation } from 'react-i18next';

const SubmitButton = () => {
  const { t } = useTranslation();
  return (
    <Button
      type="submit"
      variant="contained"
      sx={(theme) => ({
        '&.Mui-disabled': {
          color: theme.button.colorDisabled,
          backgroundColor: theme.button.backgroundColorDisabled,
        },
      })}
    >
      {t('confirm', { ns: 'common' })}
    </Button>
  );
};

export default SubmitButton;
