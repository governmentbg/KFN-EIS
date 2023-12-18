import { useState, MouseEvent, useEffect } from 'react';
import IconButton from '@mui/material/IconButton';
import Logo from '../../../components/Logo';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import MoreIcon from '@mui/icons-material/MoreVert';
import MobileDrawerNav from '../../../components/MobileDrawerNav';
import { useTheme } from '@mui/material/styles';
import useMediaQuery from '@mui/material/useMediaQuery';
import MobileMenu from './MobileMenu';
import DesktopMenu from './DesktopMenu';
import { useTranslation } from 'react-i18next';
import { useFetchServicesPNLQuery } from '../../../store/api/servicesSlice';
import { IPnlStoreState, pnlSelector } from '../../../store/pnl';
import { IPersonStoreState, personSelector } from '../../../store/person';
import { useAppSelector } from '../../../app/hooks';
import { IUser, userSelector } from '../../../store/user';

const Header = () => {
  const { t } = useTranslation(['navigation']);
  const theme = useTheme();
  const mdDown = useMediaQuery(theme.breakpoints.down('md'));
  const [mobileMoreAnchorEl, setMobileMoreAnchorEl] =
    useState<null | HTMLElement>(null);

  const isMobileMenuOpen = Boolean(mobileMoreAnchorEl);

  const handleMobileMenuOpen = (event: MouseEvent<HTMLElement>) => {
    setMobileMoreAnchorEl(event.currentTarget);
  };

  const handleMobileMenuClose = () => {
    setMobileMoreAnchorEl(null);
  };

  const mobileMenuId = 'mobile-menu-more';

  // filter all available menu options for concrete user
  const [skip, setSkip] = useState(true);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);

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
    <>
      <AppBar
        square={true}
        position="sticky"
        sx={(theme) => ({
          '&.MuiAppBar-root': {
            boxShadow: '0rem .1rem .3rem 0rem rgb(0 0 0 / 12%)',
          },
          backgroundColor: theme.palette.common.white,
        })}
      >
        <Toolbar
          sx={{
            minHeight: { xs: 'auto', sm: '3.75rem' },
            maxHeight: { xs: 'auto', sm: '3.75rem' },
            '&.MuiToolbar-root': {
              padding: '1rem',
            },
          }}
        >
          <Box sx={{ display: { xs: 'flex', md: 'none' } }}>
            <MobileDrawerNav services={filteredServices} />
          </Box>

          <Box
            sx={{
              display: 'inline-flex',
              alignItems: 'center',
              width: '7.8125rem',
            }}
          >
            <Logo />
          </Box>

          {!mdDown && <DesktopMenu />}

          <Box sx={{ display: { xs: 'flex', md: 'none' }, marginLeft: 'auto' }}>
            <IconButton
              size="large"
              aria-label={t('navigation.showMoreButton.label')}
              aria-controls={mobileMenuId}
              aria-haspopup="true"
              onClick={handleMobileMenuOpen}
            >
              <MoreIcon />
            </IconButton>
          </Box>
        </Toolbar>
      </AppBar>

      {mdDown && (
        <MobileMenu
          mobileMoreAnchorEl={mobileMoreAnchorEl}
          mobileMenuId={mobileMenuId}
          isMobileMenuOpen={isMobileMenuOpen}
          handleMobileMenuClose={handleMobileMenuClose}
        />
      )}
    </>
  );
};

export default Header;
