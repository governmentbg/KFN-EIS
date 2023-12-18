import Box from '@mui/material/Box';
import Divider from '@mui/material/Divider';
import Collapse from '@mui/material/Collapse';
import List from '@mui/material/List';
import makeStyles from '@mui/styles/makeStyles';
import { styled, Theme } from '@mui/material/styles';
import React, { useEffect, useState } from 'react';
import AppLink from './AppLink';
import ServiceLinkList from './ServiceLinkList';
import Link from '@mui/material/Link';
import { IService } from '../contracts/interfaces/services';
import getServiceNextLevelChildren from '../utils/serviceHelpers/getServiceNextLevelChildren';
import { useFetchServicesQuery } from '../store/api/servicesSlice';

const useStyles = makeStyles((theme: Theme) => ({
  navLink: {
    color: theme.link.main,
  },
  navLinkActive: {
    fontWeight: theme.typography.fontWeightBold,
    color: theme.link.active,
    textDecoration: 'underline',
    textUnderlineOffset: '.2rem',
  },
}));

const StyledList = styled(List)(({ theme }) => ({
  display: 'flex',
  flexDirection: 'column',
  position: 'absolute',
  left: 0,
  top: 0,
  bottom: 0,
  right: 0,
  height: '100%',
  overflowY: 'auto',
  '&::-webkit-scrollbar-thumb': {
    border: '0.125rem solid transparent',
    background: 'rgba(255, 255, 255, 0.4)',
    backgroundClip: 'content-box',
  },
}));

const HeaderLinksWithDropdown = () => {
  const styles = useStyles();
  const { data: services = [] } = useFetchServicesQuery(undefined);
  const [activeNavLink, setActiveNavLink] = useState<number | null>(null);
  const [isDropdownOpen, setIsDropdownOpen] = useState<boolean>(false);
  const [navTopOffset, setNavTopOffset] = useState<number | undefined>(0);
  const [servicesLevel1, setServicesLevel1] = useState<IService[]>([]);
  const [servicesLevel2, setServicesLevel2] = useState<IService[]>([]);
  const [servicesLevel3, setServicesLevel3] = useState<IService[]>([]);
  const [servicesLevel4, setServicesLevel4] = useState<IService[]>([]);
  const [servicesLevel5, setServicesLevel5] = useState<IService[]>([]);

  const toggleDropdown =
    (open: boolean) => (event: React.KeyboardEvent | React.MouseEvent) => {
      if (
        (event.type === 'keydown' &&
          (event as React.KeyboardEvent).key === 'Tab') ||
        (event as React.KeyboardEvent).key === 'Shift'
      ) {
        return;
      }

      setIsDropdownOpen(open);
      if (!open) {
        setServicesLevel2([]);
        setServicesLevel3([]);
        setServicesLevel4([]);
        setServicesLevel5([]);
      }
    };

  useEffect(() => {
    if (!isDropdownOpen) setActiveNavLink(null);
  }, [isDropdownOpen]);

  useEffect(() => {
    const headerComponent = document.getElementById('headerLinks');
    setNavTopOffset(
      headerComponent?.offsetHeight ? headerComponent?.offsetHeight : 0
    );
  }, []);

  useEffect(() => {
    if (services && services.length > 0) {
      setServicesLevel1(
        services.filter(
          (service: IService) => service.catalogElement.level === 1
        )
      );
    }
  }, [services]);

  return (
    <>
      {servicesLevel1.length > 0
        ? servicesLevel1.map((service: IService) => {
            const name = service.name;
            return (
              <Box
                sx={{ margin: '0rem 1rem' }}
                onMouseOver={() => {
                  setServicesLevel2(
                    getServiceNextLevelChildren(services, service)
                  );

                  //Reset the children services after the next level children of current service
                  [
                    setServicesLevel3,
                    setServicesLevel4,
                    setServicesLevel5,
                  ].forEach((setterFunction: Function) => {
                    setterFunction([]);
                  });
                  setActiveNavLink(service.catalogElement.id);
                }}
                key={service.catalogElement.id}
              >
                <AppLink
                  active={activeNavLink === service.catalogElement.id}
                  href={`/services/${service.catalogElement.id}`}
                  className={styles.navLink}
                  activeClassName={styles.navLinkActive}
                >
                  <Link
                    onMouseOver={toggleDropdown(true)}
                    role="menuitem"
                    tabIndex={0}
                    aria-label={name}
                    aria-haspopup={true}
                    aria-expanded={isDropdownOpen}
                    underline="hover"
                    component="a"
                    variant="h5"
                    sx={{ cursor: 'pointer' }}
                  >
                    {service.name}
                  </Link>
                </AppLink>
              </Box>
            );
          })
        : null}

      <Collapse
        in={isDropdownOpen}
        sx={(theme) => ({
          position: 'fixed',
          top: navTopOffset,
          left: '0',
          right: '0',
          zIndex: '1',
          backgroundColor: theme.dropdown.bgColor,
        })}
        onMouseLeave={toggleDropdown(false)}
      >
        <Box
          sx={{
            width: 'auto',
            padding: '1rem 0rem',
          }}
          role="presentation"
          onClick={toggleDropdown(false)}
          onKeyDown={toggleDropdown(false)}
        >
          {servicesLevel2.length > 0 ? (
            <List
              sx={{
                display: 'flex',
                flexWrap: 'wrap',
                justifyContent: 'space-between',
                padding: '0rem 1rem',
              }}
            >
              <ServiceLinkList
                services={servicesLevel2}
                setNextLevelServices={setServicesLevel3}
                resetters={[setServicesLevel4, setServicesLevel5]}
              />
            </List>
          ) : null}

          {servicesLevel3.length > 0 ? (
            <Box
              sx={(theme) => ({
                display: 'grid',
                gridTemplateColumns: '1fr 1fr 3fr',
                borderTopColor: theme.divider.light,
                borderTopStyle: 'solid',
                borderTopWidth: '.01rem',
                padding: '1rem 1rem',
                height: '40vh',
              })}
            >
              <Box
                sx={{
                  position: 'relative',
                }}
              >
                <StyledList>
                  <ServiceLinkList
                    services={servicesLevel3}
                    setNextLevelServices={setServicesLevel4}
                    resetters={[setServicesLevel5]}
                  />
                </StyledList>
              </Box>
              {servicesLevel4.length > 0 ? (
                <Box
                  sx={(theme) => ({
                    borderLeftWidth: '.01rem',
                    borderLeftStyle: 'solid',
                    borderLeftColor: theme.divider.light,
                    position: 'relative',
                  })}
                >
                  <StyledList>
                    <ServiceLinkList
                      services={servicesLevel4}
                      setNextLevelServices={setServicesLevel5}
                    />
                  </StyledList>
                </Box>
              ) : null}
              {servicesLevel5.length > 0 ? (
                <Box
                  sx={(theme) => ({
                    borderLeftWidth: '.01rem',
                    borderLeftStyle: 'solid',
                    borderLeftColor: theme.divider.light,
                    position: 'relative',
                  })}
                >
                  <StyledList>
                    <Divider
                      orientation="vertical"
                      flexItem
                      sx={(theme) => ({ borderColor: theme.divider.light })}
                    />
                    <ServiceLinkList services={servicesLevel5} />
                  </StyledList>
                </Box>
              ) : null}
            </Box>
          ) : null}
        </Box>
      </Collapse>
    </>
  );
};

export default HeaderLinksWithDropdown;
