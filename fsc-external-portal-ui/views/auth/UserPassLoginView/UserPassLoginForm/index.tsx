import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import { Theme } from '@mui/material/styles';
import makeStyles from '@mui/styles/makeStyles';
import clsx from 'clsx';
import { useFormik } from 'formik';
import { loginValidationSchema } from '../../../../validation/validationSchemas';
import { useAppDispatch } from '../../../../app/hooks/redux';
import { loginWithUsernameAndPassword } from '../../../../store/user';
import { IUserPassLoginFormProps } from '../../../../contracts/interfaces/login';
import { useTranslation } from 'react-i18next';
import useIsAuthenticated from '../../../../app/hooks/auth/useIsAuthenticated';
import { handleError } from '../../../../utils/handlers/errorHandlers';
import { CALLBACK_URL, ROUTES } from '../../../../constants';

const useStyles = makeStyles((theme: Theme) => ({
  root: {
    display: 'flex',
    flexDirection: 'column',
    padding: '1rem',
    [theme.breakpoints.up('xs')]: {
      width: '100%',
    },
    [theme.breakpoints.up('sm')]: {
      width: '25,3125rem',
    },
    '& form': {
      padding: '0rem',
      [theme.breakpoints.up('xs')]: {
        marginTop: '1.5rem',
      },
      [theme.breakpoints.up('sm')]: {
        marginTop: '3.75rem',
      },
    },
    '& .MuiTextField-root': {
      margin: '0 0 1.875rem 0',
    },
    '& .MuiFormHelperText-root': {
      position: 'absolute',
      bottom: '-1.375rem',
    },
  },
}));

const UserPassLoginForm = ({
  className,
  header,
  ...rest
}: IUserPassLoginFormProps) => {
  const styles = useStyles();
  const dispatch = useAppDispatch();
  const isAuthenticated = useIsAuthenticated();
  const { t } = useTranslation(['formFields', 'common']);
  const router = useRouter();
  const [authRequestError, setAuthRequestError] = useState<boolean>(false);
  const formik = useFormik({
    initialValues: {
      username: '',
      password: '',
    },

    validationSchema: loginValidationSchema,

    onSubmit: async (values, { setSubmitting }) => {
      setSubmitting(false);
      try {
        await dispatch(loginWithUsernameAndPassword(values)).unwrap();
      } catch (e: any) {
        if (e?.response?.status === 400) {
          setAuthRequestError(true);
        } else {
          handleError(e);
        }
      }
    },
  });

  const {
    values,
    touched,
    errors,
    isSubmitting,
    handleSubmit,
    handleBlur,
    handleChange,
  } = formik;

  useEffect(() => {
    const back = () => {
      const callbackUrl = window.localStorage.getItem(CALLBACK_URL);

      window.localStorage.removeItem(CALLBACK_URL);

      router.replace(callbackUrl ? callbackUrl : ROUTES.USER.PROFILE);
    };

    if (isAuthenticated) {
      back();
    }
  }, [isAuthenticated, router]);

  return (
    <Box className={clsx(styles.root, className)}>
      <Typography
        variant="h1"
        sx={{
          mb: '1.25rem',
          fontSize: '1.875rem',
          textTransform: 'uppercase',
        }}
      >
        {t('login', { ns: 'common' })}
      </Typography>

      <Typography
        variant="subtitle1"
        align="center"
        sx={{
          textAlign: 'left',
        }}
      >
        {header}
      </Typography>

      <form noValidate onSubmit={handleSubmit} {...rest}>
        <TextField
          error={Boolean(touched.username && errors.username)}
          helperText={touched.username && errors.username}
          fullWidth
          placeholder={t('username')}
          name="username"
          onBlur={(e) => {
            handleBlur(e);
            setAuthRequestError(false);
          }}
          onChange={handleChange}
          type="username"
          value={values.username}
          variant="outlined"
        />

        <TextField
          error={Boolean(touched.password && errors.password)}
          helperText={touched.password && errors.password}
          fullWidth
          placeholder={t('password')}
          name="password"
          onBlur={(e) => {
            handleBlur(e);
            setAuthRequestError(false);
          }}
          onChange={handleChange}
          type="password"
          value={values.password}
          variant="outlined"
        />

        {authRequestError && (
          <Typography color="error">
            Грешно потребителско име или парола
          </Typography>
        )}

        <Button
          color="primary"
          type="submit"
          disabled={isSubmitting}
          fullWidth
          variant="contained"
          sx={{ mt: '3.125rem', mb: '1rem' }}
        >
          {t('login', { ns: 'common' })}
        </Button>
      </form>
    </Box>
  );
};

export default UserPassLoginForm;
