import { Dispatch, SetStateAction } from 'react';
import { INotification } from '../../interfaces/notifications';

export type NotificationsContextProps = {
  all: INotification[];
  setAll: Dispatch<SetStateAction<INotification[]>>;
  unread: INotification[];
  setUnread: Dispatch<SetStateAction<INotification[]>>;
  read: INotification[];
  setRead: Dispatch<SetStateAction<INotification[]>>;
  activeTabIndex: number;
  setActiveTabIndex: Dispatch<SetStateAction<number>>;
};
