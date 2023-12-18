import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import { ReactNode, useEffect, useState } from 'react';
import Sidebar from '../../components/Sidebar';
import UserDossierMenu from '../../components/UserDossierMenu';
import Header from './Header';
import { useTheme } from '@mui/material/styles';
import useMediaQuery from '@mui/material/useMediaQuery';
import { useAppSelector } from '../../app/hooks/redux';
import {
  IUserNotifications,
  userNotificationsSelector,
} from '../../store/userNotifications';
import { USER_NOTIFICATIONS_CONTAINER_PORTAL_ID } from '../../constants';
import { useFetchServicesPNLQuery } from '../../store/api/servicesSlice';
import { IUser, userSelector } from '../../store/user';
import { IPnlStoreState, pnlSelector } from '../../store/pnl';
import { IPersonStoreState, personSelector } from '../../store/person';

const CustomLayout = ({ children }: { children: ReactNode }) => {
  const theme = useTheme();
  const sm = useMediaQuery(theme.breakpoints.down('md'));
  const [skip, setSkip] = useState(true);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);

  const userNotifications = useAppSelector<IUserNotifications>(
    userNotificationsSelector
  );

  // filter all available menu options for concrete user
  const { data: filteredServices = [] } = useFetchServicesPNLQuery(
    {
      personId: person.id,
      pnlId: pnl.id ? pnl.id : null,
      accessToken,
    },
    { skip }
  );

  useEffect(() => {
    if (
      typeof person.id !== 'undefined' &&
      typeof pnl.id !== 'undefined' &&
      typeof accessToken !== 'undefined'
    ) {
      setSkip(false);
    }
  }, [person.id, pnl.id, accessToken]);

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        height: '100vh',
        maxHeight: '100vh',
        overflow: 'hidden',
      }}
    >
      <Header />

      <Box
        id={USER_NOTIFICATIONS_CONTAINER_PORTAL_ID}
        sx={{
          display: userNotifications.open && sm ? 'flex' : 'none',
          flexDirection: { xs: 'column', md: 'row' },
          width: '100%',
          height: '100%',
          maxHeight: '100%',
          backgroundColor: 'common.white',
          overflow: 'auto',
        }}
      ></Box>

      <Box
        sx={{
          display: userNotifications.open && sm ? 'none' : 'flex',
          flexDirection: { xs: 'column', md: 'row' },
          position: 'relative',
          height: '100%',
          maxHeight: { xs: 'auto', md: '100%' },
          backgroundColor: 'common.white',
          overflow: 'auto',
        }}
      >
        <Paper
          sx={{
            display: { xs: 'none', md: 'flex' },
            flexDirection: 'column',
            position: 'relative',
            width: { md: '30%', lg: '20%' },
            height: { xs: 'auto', md: '100%' },
            maxHeight: '100%',
            backgroundColor: 'common.white',
            overflow: 'auto',
            marginRight: '.1rem',
          }}
          square={true}
          elevation={2}
        >
          <UserDossierMenu />
          <Sidebar levels={[1, 2, 3, 4]} services={filteredServices} />
        </Paper>

        <Box
          sx={{
            width: { xs: '100%', md: '70%', lg: '80%' },
            minHeight: '100%',
            maxHeight: '100%',
            overflow: 'auto',
            position: 'relative',
          }}
        >
          <main style={{ width: '100%', height: '100%' }}>{children}</main>
        </Box>
      </Box>
    </Box>
  );
};

export default CustomLayout;
