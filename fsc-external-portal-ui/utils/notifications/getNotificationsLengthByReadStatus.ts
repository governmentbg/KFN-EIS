
import { NOTIFICATION_READ_STATUSES } from '../../contracts/enums/notifications';
import { INotification } from '../../contracts/interfaces/notifications';
import i18n from '../../i18n';

const getNotificationsLengthByReadStatus = (
  notifications: Map<NOTIFICATION_READ_STATUSES, INotification[]>,
  notificationReadStatus: NOTIFICATION_READ_STATUSES
): number | undefined => {
  if (notifications.has(notificationReadStatus))
    return notifications.get(notificationReadStatus)!.length;

  throw new Error(i18n.t('SomethingWentWrong'));
};

export default getNotificationsLengthByReadStatus;
