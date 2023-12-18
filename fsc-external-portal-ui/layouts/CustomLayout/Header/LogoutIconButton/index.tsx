import IconButton from '@mui/material/IconButton';
import LogoutIcon from '@mui/icons-material/Logout';
import { useAppDispatch } from '../../../../app/hooks/redux';
import { useTranslation } from 'react-i18next';
import { logout } from '../../../../store/user';
import Typography from '@mui/material/Typography';

interface ILogoutIconButtonProps {
  label?: string;
}
const LogoutIconButton = ({ label }: ILogoutIconButtonProps) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation(['navigation']);

  return (
    <IconButton
      size="large"
      aria-label={t('navigation.logout')}
      onClick={() => dispatch(logout())}
    >
      <LogoutIcon />
      <Typography component='span' variant="h2">{label}</Typography>
    </IconButton>
  );
};

export default LogoutIconButton;
