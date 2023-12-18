import { IUserPnl } from '../user';

export interface IPnlType {
  id: number;

  name: string;

  longName: string;

  supervisionType: string;
}
export interface IPnlInfo {
  id: number;
  pnlType: IPnlType;
  statusDate: string;
  kfnExpert: {
    id: number;
    name: string;
  };
  pnlStatus: {
    id: number;
    name: string;
  };
  person: {
    id: number;
    country: {
      id: number;
      countryCode: string;
      name: string;
    };
    addresses: any[];
    eikEgn: string;
    personType: string;
    email: string;
    pnls: IUserPnl[];
    name: string;
    pnlPerson: any[]; //TODO: Define pnlPerson interface when data is available
    leiCode: string;
  };
}
