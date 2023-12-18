export interface IUserInfo {
  _entityName?: string;
  _instanceName?: string;
  id: number;
  person: IUserPerson;
  active: boolean;
  userType: string;
  username: string;
}

export interface IUserPerson {
  _entityName?: string;
  _instanceName?: string;
  id: number;
  lastName: string;
  country: IUserCountryInfo;
  capital: number;
  registrationPeriod: string;
  legalRegistration: string;
  eikEgn: string;
  personType: string;
  fax: string;
  email: string;
  assetsType: string;
  website: string;
  insuranceStatus: string;
  firstName: string;
  pnls: IUserPnl[];
  phone: string;
  name: string;
  middleName: string;
  registrationMethod: string;
  leiCode: string;
  isinCode: string;
  cancelationReason: string;
}

export interface IUserPnl {
  _entityName?: string;
  _instanceName?: string;
  id: number;
  pnlType: IUserPnlType;
  statusDate: string;
  kfnExpert: IUserKfnExpert;
  pnlStatus: IUserPnlStatus;
}

export interface IUserCountryInfo {
  _entityName?: string;
  _instanceName?: string;
  id: number;
  countryCode: string;
  name: string;
}
export interface IUserPnlStatus {
  _entityName?: string;
  _instanceName?: string;
  id: number;
  name: string;
}

export interface IUserKfnExpert {
  _entityName?: string;
  _instanceName?: string;
  id: number;
  name: string;
}

export interface IUserPnlType {
  _entityName?: string;
  _instanceName?: string;
  id: number;
  supervisionType: string;
  name: string;
  longName: string;
}
