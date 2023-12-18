import Box, { BoxProps } from '@mui/material/Box';
import Button, { ButtonProps } from '@mui/material/Button';
import { useTranslation } from 'react-i18next';
import { useAppSelector } from '../../../../../app/hooks';
import { useMarkAllNotificationsAsReadMutation } from '../../../../../store/api/notificationsSlice';
import { IUser, userSelector } from '../../../../../store/user';
import { handleError } from '../../../../../utils/handlers/errorHandlers';

const NotificationsHeaderButton = <C extends React.ElementType>({
  rootProps,
  buttonProps,
}: {
  rootProps?: BoxProps;
  buttonProps?: ButtonProps<C, { component?: C }>;
}) => {
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { t } = useTranslation(['common']);
  const [markAllAsRead] = useMarkAllNotificationsAsReadMutation();
  return (
    <Box {...rootProps}>
      <Button
        size="small"
        onClick={async () => {
          try {
            await markAllAsRead({ accessToken }).unwrap();
          } catch (e: any) {
            handleError(e);
          }
        }}
        {...buttonProps}
      >
        {t('notifications.markAllAsRead')}
      </Button>
    </Box>
  );
};

export default NotificationsHeaderButton;
