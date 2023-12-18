import { NOTIFICATION_READ_STATUSES } from '../../enums/notifications';

export interface INotification {
  id: string;
  recipient: string;
  readStatus: NOTIFICATION_READ_STATUSES;
  subject: string;
  type: string;
  body: string;
  createdDate: Date;
}
