import { NOTIFICATION_READ_STATUSES } from '../../../../contracts/enums/notifications';
import { ROUTES } from '../../../../constants';
import Box from '@mui/material/Box';
import NotificationsFooter from '../Components/NotificationsFooter';
import NotificationsHeaderTitle from '../Components/NotificationsHeaderTitle';
import Notifications from '../Notifications';
import NotificationsHeaderButton from '../Components/NotificationsHeaderButton';
import NotificationsImage from '../Components/NotificationsImage';
import NotificationsList from '../Components/NotificationsList';
import NotificationsNoContentTextHeadlineTwo from '../Components/NotificationsNoContentTextHeadlineTwo';
import NotificationsNoContentTextHeadlineThree from '../Components/NotificationsNoContentTextHeadlineThree';

const NotificationsPopoverView = () => {
  return (
    <Notifications>
      <Box sx={{ position: 'sticky', top: 0, zIndex: 1 }}>
        <Notifications.Header
          rootProps={{
            sx: {
              backgroundColor: (t) => t.palette.background.default,
              p: '1rem',
              display: 'flex',
              justifyContent: 'space-between',
            },
          }}
        >
          <Notifications.HeaderTitle>
            <NotificationsHeaderTitle />
          </Notifications.HeaderTitle>

          <Notifications.HeaderButton>
            <NotificationsHeaderButton
              buttonProps={{
                sx: (t) => ({
                  fontSize: t.typography.h5.fontSize,
                  fontWeight: t.typography.fontWeightMedium,
                  color: t.palette.primary.main,
                  textTransform: 'none',
                }),
              }}
            />
          </Notifications.HeaderButton>
        </Notifications.Header>

        <Notifications.TabList
          exclude={[NOTIFICATION_READ_STATUSES.READ]}
          rootProps={{
            sx: {
              backgroundColor: 'common.white',
              pl: '1rem',
            },
          }}
          tabsProps={{
            sx: {
              '.MuiButtonBase-root': {
                textTransform: 'none',
                minWidth: '0rem',
                p: ' 0rem',
                mr: '1rem',
              },
            },
          }}
        />
      </Box>

      <Notifications.TabPanels>
        <Notifications.TabPanel index={0}>
          <NotificationsList readStatus={NOTIFICATION_READ_STATUSES.ALL} />
        </Notifications.TabPanel>

        <Notifications.TabPanel index={1}>
          <NotificationsList readStatus={NOTIFICATION_READ_STATUSES.UNREAD} />
        </Notifications.TabPanel>
      </Notifications.TabPanels>

      <Notifications.NoContentBody
        rootProps={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <NotificationsImage
          rootProps={{
            sx: {
              margin: '2rem 0rem',
              width: { xs: '50%', sm: '35%', md: '50%' },
            },
          }}
        />

        <NotificationsNoContentTextHeadlineTwo
          rootProps={{
            sx: {
              width: '90%',
            },
          }}
          typographyProps={{
            sx: {
              textAlign: 'center',
              fontWeight: (t) => t.typography.fontWeightMedium,
            },
          }}
        />
        <NotificationsNoContentTextHeadlineThree
          rootProps={{
            sx: {
              width: '80%',
            },
          }}
          typographyProps={{
            sx: {
              textAlign: 'center',
            },
          }}
        />
      </Notifications.NoContentBody>

      <Notifications.Footer
        rootProps={{
          sx: {
            position: 'sticky',
            bottom: 0,
            zIndex: 1,
            backgroundColor: (t) => t.palette.background.default,
          },
        }}
      >
        <NotificationsFooter
          nextLinkProps={{ href: `${ROUTES.NOTIFICATIONS}` }}
          linkProps={{
            component: 'button',
            variant: 'h5',
            sx: (t) => ({
              fontWeight: t.typography.fontWeightMedium,
              textAlign: 'left',
              p: '1rem',
              textTransform: 'none',
              backgroundColor: 'white',
              textDecoration: 'none',
            }),
          }}
        />
      </Notifications.Footer>
    </Notifications>
  );
};

export default NotificationsPopoverView;
