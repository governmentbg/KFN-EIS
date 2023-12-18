import { IService, IServiceChild } from '../../contracts/interfaces/services';

const getServiceChildrenIds = (service: IService): number[] =>
  service.catalogElement.children.map((child: IServiceChild) => child.childId);

export default getServiceChildrenIds;
