import { IService } from '../../contracts/interfaces/services';
import getServiceChildrenIds from './getServiceChildrenIds';

const getServiceNextLevelChildren = (
  services: IService[],
  service: IService
) => {
  const serviceChildrenIds = getServiceChildrenIds(service);

  return services.filter(
    (serviceItem: IService) =>
      serviceItem.catalogElement.level === service.catalogElement.level + 1 &&
      serviceChildrenIds.includes(serviceItem.catalogElement.id)
  );
};

export default getServiceNextLevelChildren;
