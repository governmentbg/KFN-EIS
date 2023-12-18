import { useRouter } from 'next/router';
import { useTranslation } from 'react-i18next';
import { ROUTES } from '../../../constants/routes';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import ProfileButton from './ProfileButton';
import NavAuthButton from './NavAuthButton';

const Navigation = () => {
  const { t } = useTranslation(['navigation']);
  const router = useRouter();

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: { xs: 'column', sm: 'row' },
        height: { xs: 'auto', sm: '3.75rem' },
        borderWidth: '.1rem',
        borderStyle: 'solid',
        borderColor: '#707070',
        padding: { xs: '.5rem 1rem', md: '.5rem 2rem' },
        backgroundColor: 'primary.main',
        justifyContent: { xs: 'center', sm: 'initial' },
        alignContent: 'center',
        alignItems: { xs: 'initial', sm: 'center' },
      }}
    >
      {router.pathname.startsWith(ROUTES.AUTH.INDEX) ? (
        <Typography color="white">
          {t('navigation.loginPageMessage')}
        </Typography>
      ) : (
        <>
          <Typography color="white">
            {t('navigation.financialSupervisionCommission')}
          </Typography>
          <Box
            sx={{
              display: { xs: 'none', md: 'initial' },
              marginLeft: { xs: '0', sm: 'auto' },
            }}
          >
            <ProfileButton />

            <NavAuthButton color="secondary" />
          </Box>
        </>
      )}
    </Box>
  );
};

export default Navigation;
