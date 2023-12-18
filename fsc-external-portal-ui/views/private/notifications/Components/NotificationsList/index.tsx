import Typography from '@mui/material/Typography';
import React, { useContext } from 'react';
import { useTranslation } from 'react-i18next';
import {
  NOTIFICATION_DATE_RANGES,
  NOTIFICATION_READ_STATUSES,
} from '../../../../../contracts/enums/notifications';
import { INotification } from '../../../../../contracts/interfaces/notifications';
import { NotificationsContextProps } from '../../../../../contracts/types/notifications';
import filterNotificationsFromDateRange from '../../../../../utils/notifications/filterNotificationsFromDateRange';
import { NotificationsContext } from '../../Notifications';
import NotificationsItem from '../NotificationsItem';

const NotificationsList = ({
  readStatus,
}: {
  readStatus: NOTIFICATION_READ_STATUSES;
}) => {
  const { t } = useTranslation(['common']);
  const { all, unread, read } =
    useContext<NotificationsContextProps>(NotificationsContext);
  const todayDate = new Date();

  let computedNotifications: INotification[] = [];
  switch (readStatus) {
    case NOTIFICATION_READ_STATUSES.READ:
      computedNotifications = read;
      break;
    case NOTIFICATION_READ_STATUSES.UNREAD:
      computedNotifications = unread;
      break;
    case NOTIFICATION_READ_STATUSES.ALL:
      computedNotifications = all;
      break;
    default:
      break;
  }

  return (
    <>
      {Object.values(NOTIFICATION_DATE_RANGES).map(
        (notificationDateRange: NOTIFICATION_DATE_RANGES) => {
          const notificationsList = filterNotificationsFromDateRange(
            todayDate,
            notificationDateRange,
            computedNotifications
          );
          if (notificationsList.length > 0) {
            return (
              <React.Fragment key={notificationDateRange}>
                <Typography
                  key={notificationDateRange}
                  sx={{
                    backgroundColor: '#F7F7F7',
                    color: 'text.secondary',
                    p: ' .5rem 1rem',
                  }}
                >
                  {t(`${notificationDateRange}`)}
                </Typography>
                {notificationsList.map((notification: INotification) => (
                  <NotificationsItem
                    notification={notification}
                    key={notification.subject}
                  />
                ))}
              </React.Fragment>
            );
          }
        }
      )}
    </>
  );
};

export default NotificationsList;
