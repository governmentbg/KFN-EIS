import Box from '@mui/material/Box';
import Head from 'next/head';
import Grid from '@mui/material/Grid';
import Paper from '@mui/material/Paper';
import { useTranslation } from 'react-i18next';
import loginImage from '../../public/homeImages/homeContentBackground.jpg';
import Stack from '@mui/material/Stack';

const AuthLayout = ({ children }: { children: JSX.Element }) => {
  const { t } = useTranslation(['messages']);
  return (
    <>
      <Head>{t('kfnWelcomeMessage')}</Head>

      <Grid container component="div" sx={{ height: '100%', maxWidth: '100%' }}>
        <Grid
          item
          xs={12}
          sm={8}
          md={6}
          component={Paper}
          elevation={6}
          square
          sx={{
            display: 'flex',
            justifyContent: 'center',
            backgroundColor: '#ffffff',
            maxWidth: '100%',
          }}
        >
          <Stack maxWidth="100%">{children}</Stack>
        </Grid>

        <Grid
          item
          xs={false}
          sm={4}
          md={6}
          sx={{
            backgroundImage: `url(${loginImage.src})`,
            backgroundRepeat: 'no-repeat',
            backgroundColor: (t) =>
              t.palette.mode === 'light'
                ? t.palette.grey[50]
                : t.palette.grey[900],
            backgroundSize: 'cover',
            backgroundPosition: 'center',
          }}
        />
      </Grid>
    </>
  );
};
export default AuthLayout;
