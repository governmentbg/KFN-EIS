import { formatDistanceToNow, isValid } from 'date-fns';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import CloseIcon from '@mui/icons-material/Close';
import DotIcon from '@mui/icons-material/NoiseControlOff';
import Typography from '@mui/material/Typography';
import { useDateFnsLocale } from '../../../../../app/hooks/date';
import { NOTIFICATION_READ_STATUSES } from '../../../../../contracts/enums/notifications';
import Stack from '@mui/material/Stack';
import makeStyles from '@mui/styles/makeStyles';
import { Theme } from '@mui/material/styles';
import clsx from 'clsx';
import { INotification } from '../../../../../contracts/interfaces/notifications';
import { useTranslation } from 'react-i18next';
import capitalizeFirstLetter from '../../../../../utils/capitalizeFirstLetter';
import { useUpdateNotificationMutation } from '../../../../../store/api/notificationsSlice';
import { useAppSelector } from '../../../../../app/hooks';
import { IUser, userSelector } from '../../../../../store/user';
import { handleError } from '../../../../../utils/handlers/errorHandlers';

const useStyles = makeStyles((theme: Theme) => ({
  root: {
    display: 'flex',
    padding: '1rem 1rem 0rem 1rem',
    '&:hover': {
      backgroundColor: '#DBE5EF',
    },
  },
  rootNotificationsUnread: {
    backgroundColor: theme.palette.background.paper,
  },
  notificationContainer: {
    display: 'flex',
    width: '100%',
    border: '.01rem #d1d1d1',
    borderStyle: 'none none solid none',
    paddingBottom: '1rem',
  },
  dotIconContainer: {
    flex: '5%',
  },
  notificationContentContainer: {
    flex: '90%',
    marginRight: '1rem',
  },
  closeButtonContainer: {
    flex: '5%',
  },
  closeButton: { padding: 0, visibility: 'hidden' },
  notificationsSubject: {
    fontSize: theme.typography.h4.fontSize,
    fontWeight: theme.typography.fontWeightMedium,
  },
  notificationsSubjectUnread: {
    fontWeight: theme.typography.fontWeightBold,
  },
  notificationsRecipient: {
    fontSize: theme.typography.h5.fontSize,
    fontWeight: theme.typography.fontWeightMedium,
    marginTop: '.35rem',
    marginRight: '1rem',
  },
  notificationsRecipientUnread: {
    fontWeight: theme.typography.fontWeightBold,
  },
  notificationsDate: {
    fontSize: theme.typography.h5.fontSize,
    fontWeight: theme.typography.fontWeightMedium,
    marginTop: '.35rem',
    color: theme.palette.text.secondary,
  },
  notificationsDateUnread: {
    fontWeight: theme.typography.fontWeightBold,
    marginTop: '.35rem',
  },
}));
interface NotificationsItemProps {
  notification: INotification;
}

const NotificationsItem = (props: NotificationsItemProps) => {
  const { t } = useTranslation(['common']);
  const { notification } = props;
  const unreadNotification =
    notification.readStatus === NOTIFICATION_READ_STATUSES.UNREAD;
  const styles = useStyles();
  const [dateFnsLocale] = useDateFnsLocale();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const [updateNotification] = useUpdateNotificationMutation();

  const handleClick = async () => {
    try {
      await updateNotification({
        accessToken,
        id: notification.id,
        readStatus:
          notification.readStatus === NOTIFICATION_READ_STATUSES.READ
            ? NOTIFICATION_READ_STATUSES.UNREAD
            : NOTIFICATION_READ_STATUSES.READ,
      }).unwrap();
    } catch (e: any) {
      handleError(e);
    }
  };

  const handleDelete = () => {
    console.log('NotificationsItem deleted handler called');
  };

  // Preparing the data string for converting it to Data object
  const convertForDateObj: string = notification.createdDate?.toString();
  const convertedDate = new Date(
    convertForDateObj.replace(/(\d+[.])(\d+[.])/, '$2$1')
  );

  return (
    <Box
      className={clsx(
        styles.root,
        unreadNotification && styles.rootNotificationsUnread
      )}
      sx={{
        '&:hover': {
          backgroundColor: '#DBE5EF',
        },
      }}
    >
      <Box className={styles.notificationContainer}>
        <Box className={styles.dotIconContainer} onClick={handleClick}>
          {unreadNotification && <DotIcon fontSize="small" />}
        </Box>

        <Stack
          className={styles.notificationContentContainer}
          onClick={handleClick}
        >
          <Typography
            tabIndex={0}
            className={clsx(
              styles.notificationsSubject,
              unreadNotification && styles.notificationsSubjectUnread
            )}
          >
            {notification.subject}
          </Typography>

          <Stack flexDirection="row">
            <Typography
              tabIndex={0}
              className={clsx(
                styles.notificationsRecipient,
                unreadNotification && styles.notificationsRecipientUnread
              )}
            >
              {t('for')}: {notification.recipient}
            </Typography>

            {isValid(convertedDate) && (
              <Typography
                tabIndex={0}
                className={clsx(
                  styles.notificationsDate,
                  unreadNotification && styles.notificationsDateUnread
                )}
              >
                {capitalizeFirstLetter(
                  formatDistanceToNow(convertedDate, {
                    locale: dateFnsLocale,
                    addSuffix: true,
                  })
                )}
              </Typography>
            )}
          </Stack>
        </Stack>

        <Box className={styles.closeButtonContainer}>
          <IconButton onClick={handleDelete} className={styles.closeButton}>
            <CloseIcon fontSize="small" />
          </IconButton>
        </Box>
      </Box>
    </Box>
  );
};

export default NotificationsItem;
