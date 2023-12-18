import DefaultServiceLayout from '../../../layouts/DefaultServiceLayout';
import PublicRegisterView from '../../../views/public/PublicRegisterView';
import { GetStaticPaths, GetStaticPropsContext } from 'next';
import { store, wrapper } from '../../../store';
import { IService } from '../../../contracts/interfaces/services';
import { ENVIRONMENTS, REVALIDATE_TIME } from '../../../constants';
import getServiceParents from '../../../utils/serviceHelpers/getServiceParents';
import {
  getCachedServices,
  setCachedServices,
} from '../../../utils/serviceHelpers/caching';
import { getAllServices } from '../../../utils/server/services';
import ServiceAuthGuard from '../../../components/ServiceAuthGuard';

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

    const serviceParents: IService[] = getServiceParents(services, service);

    return {
      props: {
        service,
        serviceParents,
      },
      revalidate: REVALIDATE_TIME.DEFAULT,
    };
  }
);

const PublicRegisterPage = ({
  service,
  serviceParents,
}: {
  service: IService;
  serviceParents: IService[] | [];
}) => (
  <ServiceAuthGuard service={service}>
    <DefaultServiceLayout service={service} serviceParents={serviceParents}>
      <PublicRegisterView />
    </DefaultServiceLayout>
  </ServiceAuthGuard>
);
export default PublicRegisterPage;
