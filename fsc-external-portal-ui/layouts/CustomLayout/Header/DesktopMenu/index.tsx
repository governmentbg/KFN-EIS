import Box from '@mui/material/Box';
import NotificationsBadge from '../NotificationsBadge';
import UserContextSelect from '../UserContextSelect';
import LogoutIconButton from '../LogoutIconButton';

const DesktopMenu = () => (
  <Box sx={{ display: { xs: 'none', md: 'flex' }, marginLeft: 'auto' }}>
    <NotificationsBadge />

    <UserContextSelect />

    <LogoutIconButton />
  </Box>
);

export default DesktopMenu;
