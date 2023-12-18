import { useTranslation } from 'react-i18next';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import NotificationsBadge from '../NotificationsBadge';
import UserContextSelect from '../UserContextSelect';
import LogoutIconButton from '../LogoutIconButton';

interface IMobileMenuProps {
  mobileMoreAnchorEl: null | HTMLElement;
  mobileMenuId: string;
  isMobileMenuOpen: boolean;
  handleMobileMenuClose: (
    event: {},
    reason: 'backdropClick' | 'escapeKeyDown'
  ) => void;
}
const MobileMenu = ({
  mobileMoreAnchorEl,
  mobileMenuId,
  isMobileMenuOpen,
  handleMobileMenuClose,
}: IMobileMenuProps) => {
  const { t } = useTranslation(['navigation']);

  return (
    <Menu
      anchorEl={mobileMoreAnchorEl}
      anchorOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      id={mobileMenuId}
      keepMounted
      transformOrigin={{
        vertical: 'top',
        horizontal: 'right',
      }}
      sx={{
        display: { sm: 'initial', md: 'none' },
        mt:4
      }}
      open={isMobileMenuOpen}
      onClose={handleMobileMenuClose}
    >
      <MenuItem>
        <NotificationsBadge label={t('notifications', { ns: 'common' })} />
      </MenuItem>

      <MenuItem>
        <UserContextSelect />
      </MenuItem>

      <MenuItem>
        <LogoutIconButton label={t('navigation.logout')} />
      </MenuItem>
    </Menu>
  );
};

export default MobileMenu;
