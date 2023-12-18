import Button from '@mui/material/Button';
import { useTranslation } from 'react-i18next';
import { useAppDispatch } from '../../../../app/hooks/redux';
import { logout } from '../../../../store/user';
import useIsAuthenticated from '../../../../app/hooks/auth/useIsAuthenticated';
import { useRouter } from 'next/router';
import { ROUTES } from '../../../../constants';

const NavAuthButton = ({ color }: { color?: 'primary' | 'secondary' }) => {
  const { t } = useTranslation(['navigation']);
  const dispatch = useAppDispatch();
  const router = useRouter();
  const isAuthenticated = useIsAuthenticated();

  return (
    <Button
      onClick={async () =>
        isAuthenticated
          ? await dispatch(logout())
          : router.push(ROUTES.AUTH.LOGIN.OPTIONS)
      }
      variant="contained"
      {...(color && { color })}
      LinkComponent={'a'}
      aria-label={
        isAuthenticated ? t('navigation.logout') : t('navigation.login')
      }
      sx={{
        width: { xs: '50%', md: 'auto' },
        textAlign: { xs: 'center' },
      }}
    >
      {isAuthenticated ? t('navigation.logout') : t('navigation.login')}
    </Button>
  );
};

export default NavAuthButton;
