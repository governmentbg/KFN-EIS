import { IService } from '../../contracts/interfaces/services';

const getServiceParents = (
  services: IService[] = [],
  service: IService
): IService[] => {
  if (!services || !services.length || services.length === 0) return [];
  if (!service) return [];

  const serviceParents: IService[] = [];

  let currentService: IService = service;

  while (currentService && currentService.catalogElement.parent) {
    const foundedService: IService | undefined = services.find(
      (service: IService) =>
        service.catalogElement.id === currentService.catalogElement.parent
    );
    if (foundedService) {
      serviceParents.unshift(foundedService);
      currentService = { ...foundedService };
    } else {
      break;
    }
  }

  return serviceParents;
};

export default getServiceParents;
