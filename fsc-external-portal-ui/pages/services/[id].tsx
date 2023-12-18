import { GetStaticPaths, GetStaticPropsContext } from 'next';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import DefaultServiceLayout from '../../layouts/DefaultServiceLayout';
import { store, wrapper } from '../../store';
import AppLink from '../../components/AppLink';
import { IService } from '../../contracts/interfaces/services';
import ServiceView from '../../views/public/ServiceView';
import { ENVIRONMENTS, REVALIDATE_TIME, ROUTES } from '../../constants';
import getServiceChildren from '../../utils/serviceHelpers/getServiceChildren';
import getServiceParents from '../../utils/serviceHelpers/getServiceParents';
import {
  getCachedServices,
  setCachedServices,
} from '../../utils/serviceHelpers/caching';
import { getAllServices } from '../../utils/server/services';
import { useTranslation } from 'react-i18next';
import { SERVICE_TYPES } from '../../contracts/enums/services';
import { useRouter } from 'next/router';
import SpecialDocumentsDataGrid from '../../components/SpecialDocumentsDataGrid';

export const getStaticPaths: GetStaticPaths = async () => {
  let services: IService[] = [];
  let cachedServices: IService[] | undefined = [];

  if (
    !process.env.NODE_ENV ||
    process.env.NODE_ENV === ENVIRONMENTS.DEVELOPMENT
  ) {
    cachedServices = await getCachedServices();
  }

  if (cachedServices && cachedServices.length > 0) {
    services = cachedServices;
  } else {
    services = await getAllServices(store);
  }

  return {
    paths: [
      {
        params: {
          id: services?.[0].catalogElement.id.toString(),
        },
      },
    ],
    fallback: true,
  };
};

export const getStaticProps = wrapper.getStaticProps(
  (store) => async (context: GetStaticPropsContext) => {
    let services: IService[] = [];

    const { params } = context;
    if (!params || !params.id) {
      return { notFound: true, revalidate: REVALIDATE_TIME.NOT_FOUND };
    }

    services = await getAllServices(store);

    await setCachedServices(services);

    const foundService: IService | undefined =
      services.length > 0
        ? services.find(
            (service: IService) =>
              service.catalogElement.id === Number(params?.id)
          )
        : undefined;

    const service: IService | null = foundService ? foundService : null;

    if (!service)
      return { notFound: true, revalidate: REVALIDATE_TIME.NOT_FOUND };

    const serviceChildren: IService[] = getServiceChildren(services, service);

    const serviceParents: IService[] = getServiceParents(services, service);

    let serviceRoute: string | null = null;
    switch (service.catalogElement.serviceType) {
      case SERVICE_TYPES.FORM:
        serviceRoute = `${ROUTES.SERVICES.FORM}/${params.id}`;
        break;

      case SERVICE_TYPES.REGISTER:
        serviceRoute = `${ROUTES.SERVICES.PUBLIC_REGISTER}/${params.id}`;
        break;

      case SERVICE_TYPES.PENSION_SHARE:
        serviceRoute = `${ROUTES.SERVICES.PENSION_SHARE}/${params.id}`;
        break;

      default:
        serviceRoute = ROUTES.NOT_FOUND;
        break;
    }

    return {
      props: {
        service,
        serviceChildren,
        serviceParents,
        serviceRoute,
      },
      revalidate: REVALIDATE_TIME.DEFAULT,
    };
  }
);

const ServicePage = ({
  service,
  serviceChildren,
  serviceParents,
  serviceRoute,
}: {
  service: IService;
  serviceChildren: IService[] | [];
  serviceParents: IService[] | [];
  serviceRoute: string;
}) => {
  const { t } = useTranslation(['common']);

  if (useRouter().isFallback) return null;

  return (
    <DefaultServiceLayout service={service} serviceParents={serviceParents}>
      <Box sx={{ padding: '0rem 0rem 3rem 2rem' }}>
        <Typography variant="h2" component="h1" color="#3F3F3F">
          {service.name}
        </Typography>

        <Typography variant="body1" component="span">
          {service.descriptionLong}
        </Typography>

        {serviceChildren.length === 0 ? (
          <Box sx={{ pt: '1rem' }}>
            {service.catalogElement.hasSpecialDocs && (
              <SpecialDocumentsDataGrid />
            )}
            <AppLink href={serviceRoute}>
              <Button component="a" color="primary" variant="contained">
                {t('continue')}
              </Button>
            </AppLink>
          </Box>
        ) : null}
      </Box>

      <Box sx={{ display: 'flex', flexWrap: 'wrap' }}>
        {serviceChildren.length > 0
          ? serviceChildren.map((child: IService) => (
              <Box
                sx={{
                  padding: '1rem 2rem',
                  flex: { xs: '100%', md: '50%' },
                }}
                key={child.catalogElement.id}
              >
                <ServiceView service={child} />
              </Box>
            ))
          : null}
      </Box>
    </DefaultServiceLayout>
  );
};

export default ServicePage;
