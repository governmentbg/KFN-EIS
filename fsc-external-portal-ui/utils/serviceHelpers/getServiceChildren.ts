import { IService } from '../../contracts/interfaces/services';
import getServiceChildrenIds from './getServiceChildrenIds';

const getServiceChildren = (
  services: IService[],
  service: IService
): IService[] => {
  if (!services || !services.length || services.length === 0) return [];
  if (!service) return [];

  const serviceChildrenIds = getServiceChildrenIds(service);

  return services.filter((serviceElement: IService) =>
    serviceChildrenIds.includes(serviceElement.catalogElement.id)
  );
};

export default getServiceChildren;
