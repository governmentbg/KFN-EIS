import { IService } from '../../contracts/interfaces/services';
import { SERVICE_TYPES } from '../../contracts/enums/services';
import { ROUTES } from '../../constants';

const getServiceRoute = (service: IService): string => {
  let serviceRoute: string | null = null;
  if (
    service.catalogElement.children.length > 0 ||
    service.catalogElement.hasSpecialDocs
  ) {
    serviceRoute = `/services/${service.catalogElement.id}`;
  } else {
    switch (service.catalogElement.serviceType) {
      case SERVICE_TYPES.FORM:
        serviceRoute = `${ROUTES.SERVICES.FORM}/${service.catalogElement.id}`;
        break;

      case SERVICE_TYPES.REPORT:
        serviceRoute = `${ROUTES.SERVICES.FORM}/${service.catalogElement.id}`;
        break;

      case SERVICE_TYPES.REGISTER:
        serviceRoute = `${ROUTES.SERVICES.PUBLIC_REGISTER}/${service.catalogElement.id}`;
        break;

      case SERVICE_TYPES.PENSION_SHARE:
        serviceRoute = `${ROUTES.SERVICES.PENSION_SHARE}/${service.catalogElement.id}`;
        break;

      default:
        serviceRoute = ROUTES.NOT_FOUND;
        break;
    }
  }
  return serviceRoute;
};

export default getServiceRoute;
