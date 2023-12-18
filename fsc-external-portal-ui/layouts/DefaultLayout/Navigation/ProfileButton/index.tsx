import IconButton from '@mui/material/IconButton';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import Tooltip from '@mui/material/Tooltip';
import { useTranslation } from 'react-i18next';
import useIsAuthenticated from '../../../../app/hooks/auth/useIsAuthenticated';
import { useRouter } from 'next/router';

const ProfileButton = () => {
  const { t } = useTranslation(['navigation']);
  const router = useRouter();
  const isAuthenticated = useIsAuthenticated();

  return (
    <>
      {isAuthenticated && (
        <Tooltip title={t('navigation.profile') ?? ''}>
          <IconButton
            href="/profile"
            size="large"
            aria-label={t('navigation.profile')}
            sx={{ color: 'common.white' }}
          >
            <AccountCircleIcon fontSize="large" />
          </IconButton>
        </Tooltip>
      )}
    </>
  );
};

export default ProfileButton;
