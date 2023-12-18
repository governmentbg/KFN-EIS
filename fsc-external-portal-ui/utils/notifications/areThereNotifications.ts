import { INotification } from '../../contracts/interfaces/notifications';

const areThereNotifications = (notifications: INotification[]): boolean =>
  notifications && notifications.length > 0 ? true : false;

export default areThereNotifications;
