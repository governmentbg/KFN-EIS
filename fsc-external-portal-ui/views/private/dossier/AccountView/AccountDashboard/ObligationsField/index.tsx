import { useTranslation } from 'react-i18next';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';

const ObligationsField = ({
  currencyCode,
  balance,
}: {
  currencyCode: string;
  balance: number;
}) => {
  const { t } = useTranslation(['dossier']);
  return (
    <TextField
      fullWidth
      label={t('account.dashboard.obligations.label')}
      margin="normal"
      name="obligations"
      type="text"
      variant="outlined"
      value={balance.toFixed(2) + ' ' + currencyCode}
      aria-readonly={true}
      inputProps={{
        readOnly: true,
      }}
      InputProps={{
        sx: {
          '.MuiOutlinedInput-notchedOutline': {
            borderColor: 'primary.main',
          },
        },
      }}
      sx={(t) => ({
        '.MuiInputBase-input': {
          fontSize: t.typography.h2,
          fontWeight: t.typography.fontWeightMedium,
        },
      })}
    ></TextField>
  );
};

export default ObligationsField;
