import {
  SERVICE_AUTHENTICATION_LEVELS,
  SERVICE_TYPES,
} from '../../enums/services';
import { IPnlType } from '../pnl';

export interface IServiceChild {
  childId: number;

  orderNum: number;
}

export interface IServiceCatalogElement {
  id: number;

  level: number;

  catalogStatus: string;

  catalogType: string;

  parent: number;

  correspondenceTypeId: number;

  children: IServiceChild[];

  pnlType: IPnlType;

  serviceType: SERVICE_TYPES;

  serviceAuthenticationLevel: SERVICE_AUTHENTICATION_LEVELS;

  hasSpecialDocs: boolean;
}
export interface IService {
  name: string;

  descriptionShort: string;

  descriptionLong: string;

  catalogElement: IServiceCatalogElement;
}

export interface IServiceCard {
  header: string;

  content: Array<IServiceCardContent>;

  key?: number;
}

export interface IServiceCardContent {
  id: number;

  title: string;
}

export interface IRoute {
  id: number;

  path: string;
}
