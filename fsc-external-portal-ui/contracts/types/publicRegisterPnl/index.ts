import { PERSON_TYPE } from '../../enums/person';

export type PublicRegisterPnlResponseDocsInfoType = {
  documentTypeNameLong: string | null;
  documentDate: string | null;
  documentNumber: string;
  osnovanie: string | null;
};

export type PublicRegisterPnlResponseRelationsInfoType = {
  relation: string | null;
  personId: string;
  personName: string | null;
};

export type PublicRegisterPnlResponseKlonoveInfoType = {
  personName: string | null;
  klonId: string | null;
  klonName: string | null;
  address: string | null;
};

export type PublicRegisterDDSOResolutionType = {
  resolutionNumber: string;
  resolutionTypeName: string;
  resolution: string;
  resolutionEffectiveDate: Date;
};

export type CapitalMemberType = {
  name: string;
};

export type QuaestorByArticle331Type = {
  name: string;
};

export type QuaestorByArticle333Type = {
  name: string;
};
export type PublicRegisterDDSOFundType = {
  name: string;
};

export type PublicRegisterPnlResponseAdditionalInfoType = {
  publicRegisterDDSOResolutionList: PublicRegisterDDSOResolutionType[];
  responsibleActuary: string;
  capitalMemberList: CapitalMemberType[];
  quaestorByArticle331List: QuaestorByArticle331Type[];
  quaestorByArticle333List: QuaestorByArticle333Type[];
  fundList: PublicRegisterDDSOFundType[];
};

export type PublicRegisterPnlResponseType = {
  pnlName: string | null;
  personId: string | null;
  pnlId: string | null;
  countryName: string | null;
  personType: PERSON_TYPE | null;
  eikEgn: string | null;
  leiCode: string | null;
  regOfficeAddress: string | null;
  mailingAddress: string | null;
  managmentAddress: string | null;
  email: string | null;
  website: string | null;
  phone: string | null;
  fax: string | null;
  personName: string | null;
  legalRegistration: string | null;
  registrationDate: string | null;
  registrationMethod: string | null;
  registrationPeriod: string | null;
  docsInfo: PublicRegisterPnlResponseDocsInfoType[] | [];
  relationsInfo: PublicRegisterPnlResponseRelationsInfoType[] | [];
  klonoveInfo: PublicRegisterPnlResponseKlonoveInfoType[] | [];
  additionalInfo: PublicRegisterPnlResponseAdditionalInfoType;
};

export type PublicRegisterSectionType = {
  id: number;
  name: string;
  defaultExpanded: boolean;
};

export type PublicRegisterSectionResponseType = {
  pageResponse: {
    currentPage: number;
    pageSize: number;
    result: PublicRegisterSectionType[];
    totalNumberOfElements: number;
    totalNumberOfPages: number;
  };
  personName: string | null;
  personType: string | null;
};
