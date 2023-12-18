import { useState } from 'react';
import Sidebar from './Sidebar';
import Logo from './Logo';
import UserDossierMenu from '../components/UserDossierMenu';
import Drawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import CloseIcon from '@mui/icons-material/Close';
import useIsAuthenticated from '../app/hooks/auth/useIsAuthenticated';
import NavAuthButton from '../layouts/DefaultLayout/Navigation/NavAuthButton';
import { IService } from '../contracts/interfaces/services';

const MobileDrawerNav = ({ services }: { services: IService[] | [] }) => {
  const [isMobileNavOpen, setIsMobileNavOpen] = useState<boolean>(false);
  const isAuthenticated = useIsAuthenticated();

  return (
    <>
      <IconButton
        size="large"
        edge="start"
        color="primary"
        aria-label="open dropdown"
        onClick={() => setIsMobileNavOpen(!isMobileNavOpen)}
        sx={{ mr: 2 }}
      >
        <MenuIcon />
      </IconButton>

      <Box component="nav" display={isMobileNavOpen ? 'block' : 'none'}>
        <Drawer
          variant="temporary"
          open={isMobileNavOpen}
          onClose={() => setIsMobileNavOpen(false)}
          ModalProps={{
            keepMounted: true, // Better open performance on mobile.
          }}
          sx={{
            display: { xs: 'block', md: 'none' },
            '& .MuiDrawer-paper': {
              boxSizing: 'border-box',
              width: { xs: '90%', sm: '70%' },
              backgroundColor: '#ffffff',
            },
          }}
        >
          <Box sx={{ height: '100vh', paddingTop: '3.6rem' }}>
            <Box
              sx={{
                position: 'fixed',
                top: '0',
                left: '0',
                zIndex: '1',
                display: 'flex',
                width: { xs: '90%', sm: '70%' },
                marginBottom: '0.3125rem',
                padding: '0 0 0 1rem',
                backgroundColor: '#ffffff',
                boxShadow:
                  '0px 3px 1px -2px rgb(0 0 0 / 20%), 0px 2px 2px 0px rgb(0 0 0 / 14%), 0px 1px 5px 0px rgb(0 0 0 / 12%)',
              }}
            >
              <Box
                sx={{
                  display: 'flex',
                  alignItems: 'center',
                  width: '7.8125rem',
                  margin: '1rem 2rem 1rem 0',
                }}
              >
                <Logo />
              </Box>

              <IconButton
                onClick={() => setIsMobileNavOpen(false)}
                sx={{ marginLeft: 'auto' }}
              >
                <CloseIcon />
              </IconButton>
            </Box>

            <Box sx={{ padding: '1rem' }}>
              {isAuthenticated && <UserDossierMenu />}

              <Sidebar levels={[1, 2, 3, 4, 5]} services={services} />

              <Box sx={{ marginTop: '1rem' }}>
                <NavAuthButton color="primary" />
              </Box>
            </Box>
          </Box>
        </Drawer>
      </Box>
    </>
  );
};

export default MobileDrawerNav;
