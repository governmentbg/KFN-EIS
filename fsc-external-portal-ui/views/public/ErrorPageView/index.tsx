import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { useRouter } from 'next/router';
import React, { useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { ROUTES } from '../../../constants';

const ErrorPageView = ({
  errorTitle,
  errorDescription,
  showGoBackButton = true,
  showGoHomeButton = true,
  error = null,
  resetErrorBoundary = () => {},
}: {
  errorTitle?: string;
  errorDescription?: string;
  showGoBackButton?: boolean;
  showGoHomeButton?: boolean;
  error?: any;
  resetErrorBoundary?: Function;
}) => {
  const router = useRouter();
  const { t } = useTranslation(['common']);
  const { errorCode } = router.query || {};

  return (
    <Box
      sx={{
        position: 'absolute',
        left: '50%',
        top: '50%',
        transform: 'translate(-50%,-50%)',
        textAlign: 'center',
      }}
    >
      <Typography component="h1" variant="h2" sx={{ m: 1 }}>
        {errorTitle ? errorTitle : t('status.500.title', { ns: 'errors' })}
      </Typography>

      <Typography component="h2" variant="h3" sx={{ m: 2 }}>
        {errorDescription
          ? errorDescription
          : t('status.500.message', { ns: 'errors' })}
      </Typography>

      {errorCode && (
        <Typography>{`${t('errorCode', {
          ns: 'errors',
        })} ${errorCode}`}</Typography>
      )}

      <Box
        sx={{
          display: 'flex',
          flexDirection: {
            xs: 'column',
            sm: 'column',
            md: 'row',
            alignItems: 'center',
            justifyContent: 'center',
          },
          gap: 2,
          m: 4,
        }}
      >
        {showGoBackButton && (
          <Button
            role="link"
            variant="contained"
            sx={{ width: { xs: '100%', sm: '100%', md: 'auto' } }}
            onClick={() => {
              router.back();
              resetErrorBoundary && resetErrorBoundary();
            }}
          >
            {`${t('returnBack')}`}
          </Button>
        )}

        {showGoHomeButton && (
          <Button
            href={ROUTES.HOME}
            LinkComponent="a"
            variant="contained"
            sx={{ width: { xs: '100%', sm: '100%', md: 'auto' } }}
            onClick={() => resetErrorBoundary && resetErrorBoundary()}
          >
            {`${t('goToHomePage')}`}
          </Button>
        )}
      </Box>
    </Box>
  );
};

export default ErrorPageView;
