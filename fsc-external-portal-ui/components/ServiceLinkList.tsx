import { Fragment, useState } from 'react';
import { IService } from '../contracts/interfaces/services';
import { useFetchServicesQuery } from '../store/api/servicesSlice';
import ServiceLinkListItem from './ServiceLinkListItem';
import getServiceRoute from '../utils/serviceHelpers/getServiceRoute';

const ServiceLinkList = ({
  services,
  resetters,
  setNextLevelServices = () => {},
}: {
  services: Array<IService>;
  resetters?: Array<Function>;
  setNextLevelServices?: Function;
}) => {
  const { data: allServices = [] } = useFetchServicesQuery(undefined);
  const [active, setActive] = useState<number | null>(null);

  return (
    <>
      {services.map((service: IService) => {
        const serviceRoute: string = getServiceRoute(service);

        return (
          <Fragment key={service.catalogElement.id}>
            <ServiceLinkListItem
              active={active === service.catalogElement.id}
              setActive={setActive}
              services={allServices}
              currentService={service}
              href={serviceRoute}
              setChildrenServices={(services: IService[]) => {
                //Load children services
                setNextLevelServices(services);
                //Reset the services levels after children services of the current
                resetters && resetters.length > 0
                  ? resetters.forEach((resetter: Function) => {
                      resetter([]);
                    })
                  : null;
              }}
            />
          </Fragment>
        );
      })}
    </>
  );
};

export default ServiceLinkList;
