import Badge from '@mui/material/Badge';
import { useState, MouseEvent, useRef, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { useRouter } from 'next/router';
import { useAppDispatch, useAppSelector } from '../../../../app/hooks/redux';
import {
  IUserNotifications,
  setUserNotifications,
  userNotificationsSelector,
} from '../../../../store/userNotifications';
import { NOTIFICATION_READ_STATUSES } from '../../../../contracts/enums/notifications';
import {
  ROUTES,
  USER_NOTIFICATIONS_CONTAINER_PORTAL_ID,
  USER_NOTIFICATIONS_ICON_BUTTON_ID,
  USER_NOTIFICATIONS_MENU_CONTAINER_ID,
} from '../../../../constants';
import NotificationsPopoverView from '../../../../views/private/notifications/NotificationsPopoverView';
import IconButton from '@mui/material/IconButton';
import NotificationsIcon from '@mui/icons-material/NotificationsOutlined';
import Box from '@mui/material/Box';
import { useTheme } from '@mui/material/styles';
import useMediaQuery from '@mui/material/useMediaQuery';
import Typography from '@mui/material/Typography';
import Popover from '@mui/material/Popover';
import Portal from '@mui/base/Portal';
import { INotification } from '../../../../contracts/interfaces/notifications';
import { useFetchNotificationsQuery } from '../../../../store/api/notificationsSlice';
import { IUser, userSelector } from '../../../../store/user';
interface NotificationsBadgeProps {
  label?: string;
}
const NotificationsBadge = ({ label }: NotificationsBadgeProps) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation(['common']);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const router = useRouter();
  const theme = useTheme();
  const mdDown = useMediaQuery(theme.breakpoints.down('md'));
  const { data: notifications = [] } = useFetchNotificationsQuery({
    accessToken,
  });
  const userNotifications = useAppSelector<IUserNotifications>(
    userNotificationsSelector
  );
  const containerRef = useRef(
    document.getElementById(USER_NOTIFICATIONS_CONTAINER_PORTAL_ID)
  );
  const notificationsMenuId = 'notifications-menu';
  const [notificationsAnchorEl, setNotificationsAnchorEl] =
    useState<null | HTMLElement>(null);

  const handleNotificationsIconClick = (event?: MouseEvent<HTMLElement>) => {
    if (userNotifications.open) {
      dispatch(setUserNotifications(false));
      handleNotificationsMenuClose();
    }
    if (!userNotifications.open && event) {
      dispatch(setUserNotifications(true));
      handleNotificationsMenuOpen(event);
    }
  };
  const handleNotificationsMenuOpen = (event: MouseEvent<HTMLElement>) => {
    setNotificationsAnchorEl(event.currentTarget);
  };

  const handleNotificationsMenuClose = () => {
    setNotificationsAnchorEl(null);
  };

  const onBackdropClick = (reason: string) => {
    if (reason === 'backdropClick' || 'escapeKeyDown') {
      handleNotificationsIconClick();
    }
  };

  useEffect(() => {
    if (userNotifications.open && !notificationsAnchorEl) {
      setNotificationsAnchorEl(
        document.getElementById(USER_NOTIFICATIONS_ICON_BUTTON_ID)
      );
    }
  }, [mdDown, notificationsAnchorEl, userNotifications.open]);
  return (
    <Box sx={{ position: 'relative' }}>
      <IconButton
        id={USER_NOTIFICATIONS_ICON_BUTTON_ID}
        aria-label={t('notifications.showNotifications')}
        aria-controls={USER_NOTIFICATIONS_MENU_CONTAINER_ID}
        aria-haspopup="true"
        size="large"
        sx={{
          mr: '1rem',
          backgroundColor: (t) =>
            router.pathname === ROUTES.NOTIFICATIONS || userNotifications.open
              ? t.palette.background.paper
              : '',
        }}
        onClick={handleNotificationsIconClick}
      >
        <Badge
          badgeContent={
            notifications && notifications.length > 0
              ? notifications.filter(
                  (notification: INotification) =>
                    notification.readStatus ===
                    NOTIFICATION_READ_STATUSES.UNREAD
                ).length
              : 0
          }
          color="error"
        >
          <NotificationsIcon />
        </Badge>
        <Typography variant="h2">{label}</Typography>
      </IconButton>

      {userNotifications.open && !mdDown && (
        <Popover
          id={notificationsMenuId}
          anchorEl={notificationsAnchorEl}
          anchorOrigin={{
            vertical: 'bottom',
            horizontal: 'right',
          }}
          transformOrigin={{
            vertical: 'top',
            horizontal: 'right',
          }}
          sx={{
            '.MuiPopover-paper': {
              maxHeight: '90%',
              maxWidth: {
                xs: '100%',
                sm: '100%',
                md: '50%',
                lg: '45%',
                xl: '40%',
              },
            },
          }}
          keepMounted
          open={userNotifications.open && Boolean(notificationsAnchorEl)}
          onClose={(_, reason) => onBackdropClick(reason)}
        >
          <NotificationsPopoverView />
        </Popover>
      )}

      {userNotifications.open && mdDown && (
        <Portal container={containerRef.current}>
          <NotificationsPopoverView />
        </Portal>
      )}
    </Box>
  );
};

export default NotificationsBadge;
