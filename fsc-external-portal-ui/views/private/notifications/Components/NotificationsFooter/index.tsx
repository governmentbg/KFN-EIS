import { useTranslation } from 'react-i18next';
import Box, { BoxProps } from '@mui/material/Box';
import { useAppDispatch } from '../../../../../app/hooks/redux';
import AppLink from '../../../../../components/AppLink';
import { ROUTES } from '../../../../../constants';
import { setUserNotifications } from '../../../../../store/userNotifications';
import Link, { LinkProps } from '@mui/material/Link';
import { LinkProps as NextLinkProps } from 'next/link';
import { border } from '@mui/system';
import React from 'react';

const NotificationsFooter = <C extends React.ElementType>({
  rootProps,
  nextLinkProps,
  linkProps,
}: {
  rootProps?: BoxProps;
  nextLinkProps: NextLinkProps;
  linkProps?: LinkProps<C, { component: C }>;
}) => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation(['common']);
  return (
    <Box {...rootProps}>
      <AppLink  {...nextLinkProps}>
        <Link
          onClick={() => dispatch(setUserNotifications(false))}
          {...linkProps}
        >
          {t('notifications.showAll')}
        </Link>
      </AppLink>
    </Box>
  );
};

export default NotificationsFooter;
