import { useRouter } from 'next/router';
import { ROUTES } from '../../../constants/routes';
import SearchField from '../../../components/SearchField';
import MobileDrawerNav from '../../../components/MobileDrawerNav';
import Logo from '../../../components/Logo';
import HeaderLinksWithDropdown from '../../../components/HeaderLinksWithDropdown';
import Navigation from '../Navigation';
import Box from '@mui/material/Box';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import GoToHomeButton from './GoToHomeButton';
import { useFetchServicesQuery } from '../../../store/api/servicesSlice';

const Header = () => {
  const router = useRouter();
  const isLoginPage: boolean = router.pathname.startsWith(ROUTES.AUTH.INDEX);
  const { data: services = [] } = useFetchServicesQuery(undefined);

  return (
    <Box sx={{ flexWrap: 'wrap' }}>
      <AppBar id="header">
        <Toolbar
          id="headerLinks"
          disableGutters={true}
          sx={{
            padding: { xs: '0.5rem 1rem', md: '1rem 2rem' },
            backgroundColor: 'common.white',
            height: { xs: 'auto', md: '5.625rem' },
          }}
        >
          {!isLoginPage && (
            <Box sx={{ display: { xs: 'flex', md: 'none' } }}>
              <MobileDrawerNav services={services} />
            </Box>
          )}

          <Box
            sx={{
              display: 'inline-flex',
              width: { xs: '7.8125rem', md: 'auto' },
              mr: '2rem',
            }}
          >
            <Logo />
          </Box>

          {/*Nav links*/}
          {!isLoginPage && (
            <>
              <Box
                sx={{
                  display: { xs: 'none', md: 'flex' },
                  marginLeft: 'auto',
                }}
              >
                <HeaderLinksWithDropdown />
              </Box>

              <Box sx={{ flex: { xs: '1', md: '0' } }}></Box>

              <SearchField />
            </>
          )}
          {isLoginPage && <GoToHomeButton />}
        </Toolbar>

        <nav>
          <Navigation />
        </nav>
      </AppBar>
    </Box>
  );
};

export default Header;
