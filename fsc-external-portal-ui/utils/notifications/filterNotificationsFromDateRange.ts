import { NOTIFICATION_DATE_RANGES } from '../../contracts/enums/notifications';
import { INotification } from '../../contracts/interfaces/notifications';

const filterNotificationsFromDateRange = (
  todayDate: Date,
  from: NOTIFICATION_DATE_RANGES,
  notifications: INotification[] = []
): INotification[] => {
  if (!notifications || notifications.length === 0) return [];

  switch (from) {
    case NOTIFICATION_DATE_RANGES.TODAY: {
      return notifications.filter((notification: INotification) => {
        const notificationCreatedDate = new Date(notification.createdDate);
        if (
          todayDate.getFullYear() === notificationCreatedDate.getFullYear() &&
          todayDate.getMonth() === notificationCreatedDate.getMonth() &&
          todayDate.getDate() === notificationCreatedDate.getDate()
        ) {
          return notification;
        }
      });
    }
    case NOTIFICATION_DATE_RANGES.YESTERDAY: {
      return notifications.filter((notification: INotification) => {
        const notificationCreatedDate = new Date(notification.createdDate);
        if (
          todayDate.getFullYear() === notificationCreatedDate.getFullYear() &&
          todayDate.getMonth() === notificationCreatedDate.getMonth() &&
          todayDate.getDate() - 1 === notificationCreatedDate.getDate()
        ) {
          return notification;
        }
      });
    }
    case NOTIFICATION_DATE_RANGES.EARLIER_THAN_YESTERDAY: {
      return notifications.filter((notification: INotification) => {
        const notificationCreatedDate = new Date(notification.createdDate);
        if (
          todayDate.getFullYear() !== notificationCreatedDate.getFullYear() ||
          todayDate.getMonth() !== notificationCreatedDate.getMonth() ||
          todayDate.getDate() - 1 > notificationCreatedDate.getDate()
        ) {
          return notification;
        }
      });
    }
    default:
      return notifications;
  }
};

export default filterNotificationsFromDateRange;
