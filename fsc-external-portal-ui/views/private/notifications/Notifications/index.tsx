/* eslint-disable react/display-name */
import {
  useState,
  useContext,
  createContext,
  ReactNode,
  useEffect,
} from 'react';
import { useTranslation } from 'react-i18next';
import isNumber from '../../../../utils/isNumber';
import areThereNotifications from '../../../../utils/notifications/areThereNotifications';
import getNotificationsLengthByReadStatus from '../../../../utils/notifications/getNotificationsLengthByReadStatus';
import useIsMounted from '../../../../app/hooks/shared/useIsMounted';
import { NOTIFICATION_READ_STATUSES } from '../../../../contracts/enums/notifications';
import { NotificationsContextProps } from '../../../../contracts/types/notifications';
import Box, { BoxProps } from '@mui/material/Box';
import Stack, { StackProps } from '@mui/material/Stack';
import Tab, { TabProps } from '@mui/material/Tab';
import Tabs, { TabsProps } from '@mui/material/Tabs';
import { useAppSelector } from '../../../../app/hooks';
import { IUser, userSelector } from '../../../../store/user';
import { useFetchNotificationsQuery } from '../../../../store/api/notificationsSlice';
import { INotification } from '../../../../contracts/interfaces/notifications';

export const NotificationsContext = createContext(
  {} as NotificationsContextProps
);

const Notifications = ({
  children,
  rootProps,
}: {
  children: ReactNode;
  rootProps?: StackProps;
}) => {
  const [all, setAll] = useState<INotification[]>([]);
  const [read, setRead] = useState<INotification[]>([]);
  const [unread, setUnread] = useState<INotification[]>([]);
  const [activeTabIndex, setActiveTabIndex] = useState<number>(0);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { data: notifications = [] } = useFetchNotificationsQuery<{
    data: INotification[];
  }>({ accessToken });

  useEffect(() => {
    if (!areThereNotifications(notifications)) return;

    setAll(notifications);
    setRead(
      notifications.filter(
        (notification: INotification) =>
          notification.readStatus === NOTIFICATION_READ_STATUSES.READ
      )
    );
    setUnread(
      notifications.filter(
        (notification: INotification) =>
          notification.readStatus === NOTIFICATION_READ_STATUSES.UNREAD
      )
    );
  }, [notifications]);

  return (
    <NotificationsContext.Provider
      value={{
        all,
        setAll,
        read,
        setRead,
        unread,
        setUnread,
        activeTabIndex,
        setActiveTabIndex,
      }}
    >
      <Stack {...rootProps}>{children}</Stack>
    </NotificationsContext.Provider>
  );
};
export default Notifications;

const NotificationsHeader = ({
  children,
  rootProps,
}: {
  children: ReactNode;
  rootProps?: BoxProps;
}) => <Box {...rootProps}>{children}</Box>;
Notifications.Header = NotificationsHeader;

const NotificationsHeaderTitle = ({
  children,
  rootProps,
}: {
  children: ReactNode;
  rootProps?: BoxProps;
}) => <Box {...rootProps}>{children}</Box>;

Notifications.HeaderTitle = NotificationsHeaderTitle;

const NotificationsHeaderButton = ({
  children,
  rootProps,
}: {
  children: ReactNode;
  rootProps?: BoxProps;
}) => {
  const { all } = useContext<NotificationsContextProps>(NotificationsContext);

  if (!areThereNotifications(all)) return null;

  return <Box {...rootProps}>{children}</Box>;
};
Notifications.HeaderButton = NotificationsHeaderButton;

const NotificationsTabList = ({
  exclude,
  rootProps,
  tabsProps,
  tabProps,
}: {
  exclude?: NOTIFICATION_READ_STATUSES[];
  rootProps?: BoxProps;
  tabsProps?: TabsProps;
  tabProps?: TabProps;
}) => {
  let index = 0;
  const { t } = useTranslation(['common']);
  const { isMounted } = useIsMounted();
  const { all, read, unread, activeTabIndex, setActiveTabIndex } =
    useContext<NotificationsContextProps>(NotificationsContext);

  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
    setActiveTabIndex(newValue);
  };

  if (!isNumber(activeTabIndex))
    throw new Error('Active tab index must be a number');

  const a11yProps = (index: number) => {
    return {
      id: `tab-${index}`,
      'aria-controls': `tabpanel-${index}`,
    };
  };

  if (!areThereNotifications(all)) return null;

  return (
    <Box {...rootProps}>
      <Tabs
        value={isMounted ? activeTabIndex : false}
        onChange={handleChange}
        aria-label={t('notifications')}
        selectionFollowsFocus
        {...tabsProps}
      >
        {Object.values(NOTIFICATION_READ_STATUSES)
          .filter(
            (notificationReadStatus: NOTIFICATION_READ_STATUSES) =>
              !exclude?.includes(notificationReadStatus)
          )
          .map((notificationReadStatus: NOTIFICATION_READ_STATUSES) => {
            return (
              <Tab
                key={index}
                value={index}
                label={`${t(
                  `notifications.${notificationReadStatus.toLowerCase()}`
                )} (${getNotificationsLengthByReadStatus(
                  new Map([
                    [NOTIFICATION_READ_STATUSES.ALL, all],
                    [NOTIFICATION_READ_STATUSES.READ, read],
                    [NOTIFICATION_READ_STATUSES.UNREAD, unread],
                  ]),
                  notificationReadStatus
                )})`}
                {...a11yProps(index++)}
                {...tabProps}
              />
            );
          })}
      </Tabs>
    </Box>
  );
};
Notifications.TabList = NotificationsTabList;

const NotificationsTabPanels = ({
  children,
  rootProps,
}: {
  children: ReactNode;
  rootProps?: BoxProps;
}) => {
  return <Box {...rootProps}>{children}</Box>;
};
Notifications.TabPanels = NotificationsTabPanels;

const NotificationsTabPanel = ({
  children,
  rootProps,
  index,
}: {
  children: ReactNode;
  index: number;
  rootProps?: StackProps;
}) => {
  const { t } = useTranslation(['errors']);
  const { all, activeTabIndex } =
    useContext<NotificationsContextProps>(NotificationsContext);

  if (!isNumber(activeTabIndex)) throw new Error('Active tab index is missing');

  if (!areThereNotifications(all)) return null;

  return (
    <Stack
      role="tabpanel"
      hidden={activeTabIndex !== index}
      id={`tabpanel-${index}`}
      aria-labelledby={`tab-${activeTabIndex}`}
      sx={{ overflow: 'auto' }}
      {...rootProps}
    >
      {activeTabIndex === index && children}
    </Stack>
  );
};
Notifications.TabPanel = NotificationsTabPanel;

const NotificationsNoContentBody = ({
  children,
  rootProps,
}: {
  children: ReactNode;
  rootProps?: BoxProps;
}) => {
  const { all } = useContext<NotificationsContextProps>(NotificationsContext);

  if (areThereNotifications(all)) return null;

  return <Box {...rootProps}>{children}</Box>;
};
Notifications.NoContentBody = NotificationsNoContentBody;

const NotificationsFooter = ({
  children,
  rootProps,
}: {
  children: ReactNode;
  rootProps?: BoxProps;
}) => {
  const { all } = useContext<NotificationsContextProps>(NotificationsContext);

  if (!areThereNotifications(all)) return null;

  return <Box {...rootProps}>{children}</Box>;
};
Notifications.Footer = NotificationsFooter;
