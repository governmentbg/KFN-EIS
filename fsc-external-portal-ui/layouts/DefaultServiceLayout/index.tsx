import { ReactNode, useEffect, useState, useCallback } from 'react';
import Head from 'next/head';
import Breadcrumbs from '@mui/material/Breadcrumbs';
import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import DefaultLayout from '../../layouts/DefaultLayout';
import Sidebar from '../../components/Sidebar';
import AppLink from '../../components/AppLink';
import { IService } from '../../contracts/interfaces/services';
import { useRouter } from 'next/router';
import { useTranslation } from 'react-i18next';
import { useFetchServicesQuery } from '../../store/api/servicesSlice';

const DefaultServiceLayout = ({
  service,
  serviceParents,
  children,
}: {
  service: IService;
  serviceParents: IService[] | [];
  children: ReactNode;
}) => {
  const { t } = useTranslation(['common']);
  const { data: services = [] } = useFetchServicesQuery(undefined);
  const [activePageHeight, setactivePageHeight] = useState<string | undefined>(
    '100%'
  );

  const calculateActivePageHeight = useCallback(() => {
    const breadcrumbsComponent = document.getElementById('breadcrumbs');
    const pageHeight = breadcrumbsComponent?.offsetHeight
      ? 'calc(100% - ' + breadcrumbsComponent?.offsetHeight + 'px)'
      : '100%';
    setactivePageHeight(pageHeight);
  }, []);

  useEffect(() => {
    calculateActivePageHeight();

    window.addEventListener('resize', calculateActivePageHeight);
    return () =>
      window.removeEventListener('resize', calculateActivePageHeight);
  }, [calculateActivePageHeight]);

  if (useRouter().isFallback) return null;

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        height: { xs: 'auto', md: '100vh' },
        overflow: { xs: 'auto', md: 'hidden' },
      }}
    >
      <Head>
        <title>{service.name}</title>
      </Head>

      <DefaultLayout>
        <Box
          sx={{
            padding: { xs: '1rem 0 1rem 1rem', md: '1rem 0 1rem 2rem' },
            flexWrap: 'wrap',
            minWidth: '100%',
            width: { xs: '100%', sm: 'auto' },
            height: '100%',
          }}
        >
          <Box
            sx={{ flex: '100%', paddingRight: { xs: '1rem', md: '2rem' } }}
            id="breadcrumbs"
          >
            <Breadcrumbs aria-label="breadcrumb">
              <Link underline="hover" color="primary" href="/">
                {t('home')}
              </Link>
              {serviceParents && serviceParents.length > 0
                ? serviceParents.map((service: IService) => (
                    <div key={service.catalogElement.id}>
                      <AppLink href={`/services/${service.catalogElement.id}`}>
                        <Link
                          underline="hover"
                          color="primary"
                          sx={{ cursor: 'pointer' }}
                        >
                          {service.name}
                        </Link>
                      </AppLink>
                    </div>
                  ))
                : null}

              <Typography color="text.primary">{service.name}</Typography>
            </Breadcrumbs>
          </Box>

          <Box
            sx={{
              padding: { xs: '1rem 1rem 1rem 0', md: '1rem 0' },
              display: 'flex',
              flexWrap: 'wrap',
              flexDirection: { xs: 'column', md: 'row' },
              height: { xs: 'auto', md: activePageHeight },
            }}
          >
            <Box
              sx={{
                display: { xs: 'none', md: 'flex' },
                flex: { xs: '100%', md: '30%', lg: '20%' },
                height: { xs: 'auto', md: '100%' },
                border: '0.1px solid #d3d3d3',
                overflow: 'auto',
                mb: { xs: '1rem', md: '0rem' },
              }}
            >
              <Sidebar
                levels={[2, 3, 4, 5]}
                topLevelService={
                  service?.catalogElement.level === 1
                    ? service
                    : serviceParents.length > 0
                    ? serviceParents[0]
                    : undefined
                }
                services={services}
              />
            </Box>
            <Box
              sx={{
                maxHeight: { xs: '100%' },
                alignContent: 'flex-start',
                flex: { xs: '100%', md: '70%', lg: '80%' },
                width: { xs: '100%', sm: 'auto' },
                overflowY: 'auto',
              }}
            >
              {children}
            </Box>
          </Box>
        </Box>
      </DefaultLayout>
    </Box>
  );
};

export default DefaultServiceLayout;
