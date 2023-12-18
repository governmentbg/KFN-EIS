import Box from '@mui/material/Box';
import { NOTIFICATION_READ_STATUSES } from '../../../../contracts/enums/notifications';
import NotificationsHeaderTitle from '../Components/NotificationsHeaderTitle';
import Notifications from '../Notifications';
import NotificationsHeaderButton from '../Components/NotificationsHeaderButton';
import NotificationsImage from '../Components/NotificationsImage';
import NotificationsNoContentTextHeadlineTwo from '../Components/NotificationsNoContentTextHeadlineTwo';
import NotificationsNoContentTextHeadlineThree from '../Components/NotificationsNoContentTextHeadlineThree';
import NotificationsList from '../Components/NotificationsList';

const NotificationsPageView = () => {
  return (
    <Notifications>
      <Box sx={{ position: 'sticky', top: 0, zIndex: 1 }}>
        <Notifications.Header
          rootProps={{
            sx: {
              backgroundColor: (t) => t.palette.background.paper,
              display: 'flex',
              flexDirection: 'column',
              justifyContent: 'space-between',
            },
          }}
        >
          <Notifications.HeaderTitle rootProps={{ sx: { p: '1rem' } }}>
            <NotificationsHeaderTitle />
          </Notifications.HeaderTitle>

          <Box
            sx={{
              display: 'flex',
              flexDirection: { xs: 'column-reverse', sm: 'row' },
              justifyContent: 'space-between',
              alignItems: { xs: 'flex-start', sm: 'center' },
              flexWrap: 'wrap',
              backgroundColor: 'common.white',
              padding: '0rem 1rem',
            }}
          >
            <Notifications.TabList
              exclude={[NOTIFICATION_READ_STATUSES.READ]}
              rootProps={{
                sx: {
                  backgroundColor: 'common.white',
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
            <Notifications.HeaderButton>
              <NotificationsHeaderButton
                buttonProps={{
                  sx: (t) => ({
                    fontSize: t.typography.h5.fontSize,
                    fontWeight: t.typography.fontWeightMedium,
                    color: t.palette.primary.main,
                    textTransform: 'none',
                    textAlign: 'left',
                    paddingLeft: { xs: 0 },
                  }),
                }}
              />
            </Notifications.HeaderButton>
          </Box>
        </Notifications.Header>
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
              width: {
                xs: '50%',
                sm: '35%',
                md: '30%',
                lg: '25%',
                xl: 'clamp(15%,20%,21.25rem)',
              },
            },
          }}
        />

        <NotificationsNoContentTextHeadlineTwo
          typographyProps={{
            sx: {
              fontWeight: (t) => t.typography.fontWeightMedium,
            },
          }}
        />
        <NotificationsNoContentTextHeadlineThree />
      </Notifications.NoContentBody>
    </Notifications>
  );
};

export default NotificationsPageView;
