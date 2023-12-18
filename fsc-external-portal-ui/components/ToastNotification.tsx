import Dialog from '@mui/material/Dialog';
import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import { useCallback, useEffect } from 'react';
import { useAppDispatch, useAppSelector } from '../app/hooks/redux';
import { AppError, errorSelector, resetError } from '../store/error';
import {
  notificationSelector,
  resetNotification,
  setNotification,
} from '../store/notification/index';
import Typography from '@mui/material/Typography';
import { ERROR_SEVERITY } from '../contracts/enums/error';
import getAlertColorBySeverityType from '../utils/alert/getAlertColorBySeverityType';
import { useRouter } from 'next/router';

const ToastNotification = () => {
  const dispatch = useAppDispatch();
  const appError = useAppSelector<AppError>(errorSelector);
  const router = useRouter();
  const appNotification = useAppSelector(notificationSelector);
  const { isOpen, severity, title, message } = appNotification || {};

  const handleClose = useCallback(() => {
    dispatch(resetError());
    dispatch(resetNotification());
  }, [dispatch]);

  useEffect(() => {
    if (appError && appError.message)
      dispatch(
        setNotification({
          isOpen: true,
          severity: appError.severity,
          title: appError.title,
          message: appError.message,
        })
      );
  }, [appError, dispatch]);

  return !isOpen ? null : (
    <Dialog keepMounted={true} open={isOpen} role="alertdialog">
      <Alert
        severity={severity ? getAlertColorBySeverityType(severity) : 'success'}
        role="alert"
        variant="filled"
        onClick={handleClose}
      >
        <AlertTitle color="common.white">
          {title && <h3>{title}</h3>}
        </AlertTitle>
        {message && (
          <Typography
            paragraph={true}
            sx={{
              whiteSpace: 'pre-wrap',
              wordBreak: 'break-word',
            }}
            color="common.white"
          >
            {message}
          </Typography>
        )}
      </Alert>
    </Dialog>
  );
};

export default ToastNotification;
