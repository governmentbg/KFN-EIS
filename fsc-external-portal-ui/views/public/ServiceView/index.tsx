import { IService } from '../../../contracts/interfaces/services';
import Service from './Service';

const ServiceView = ({ service }: { service: IService }) => (
  <Service service={service} />
);

export default ServiceView;
