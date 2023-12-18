import CustomLayout from '../../layouts/CustomLayout';
import ServiceRequest from '../../views/private/serviceRequest';
import useIsAuthenticated from '../../app/hooks/auth/useIsAuthenticated';
import DefaultServiceLayout from '../../layouts/DefaultServiceLayout';
import {
  useFetchServiceQuery,
  useFetchServicesQuery,
} from '../../store/api/servicesSlice';
import { useRouter } from 'next/router';
import getServiceParents from '../../utils/serviceHelpers/getServiceParents';
import ServiceAuthGuard from '../../components/ServiceAuthGuard';

const Service = () => {
  const isAuthenticated = useIsAuthenticated();
  const router = useRouter();
  const { data: services = [] } = useFetchServicesQuery(undefined);
  const { data: service } = useFetchServiceQuery(
    { serviceId: router?.query?.id?.toString() ?? '' },
    { skip: !router?.query?.id }
  );

  if (!service) return null;

  let view = null;

  if (isAuthenticated) {
    view = (
      <CustomLayout>
        <ServiceRequest />
      </CustomLayout>
    );
  } else {
    view = (
      <DefaultServiceLayout
        service={service}
        serviceParents={getServiceParents(services, service)}
      >
        <ServiceRequest />
      </DefaultServiceLayout>
    );
  }
  return <ServiceAuthGuard service={service}>{view}</ServiceAuthGuard>;
};

export default Service;
