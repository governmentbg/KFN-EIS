import { OBLIGATION_STATUSES } from '../../enums/dossier/account/obligations';
import { IObligationsFilter } from '../../interfaces/dossier/account/obligationsFilter';

export type ObligationsResponseResultType = {
  chargeId: number;
  chargeType?: string;
  chargeDate?: string;
  chargeValueMain?: string;
  chargeValueMainCurrency?: string;
  chargeValueSecond?: string;
  chargeValueSecondCurrency?: string;
  currentInterestValueMain?: string;
  currentInterestValueSecond?: string;
  currentPrincipalValueMain?: string;
  currentPrincipalValueSecond?: string;
  paymentDueDate?: string;
  paymentRequestId?: string;
  paymentAccessCode?: string;
  status?: OBLIGATION_STATUSES;
};
export type ObligationsResponseType = {
  totalNumberOfPages: number;
  totalNumberOfElements: number;
  currentPage: number;
  pageSize: number;
  result: ObligationsResponseResultType[] | [];
};

export type TotalObligationsResponseType = {
  totalDueValue: number;
  totalDueCurrency: string;
};

export type ChargesPaymentsType = {
  id: number;
  createdOn: string | null;
  principalValueMain: string | null;
  interestValueMain: string | null;
  currencyMainCode: string | null;
  principalValueSecond: string | null;
  interestValueSecond: string | null;
  currencySecondCode: string | null;
};

export type ObligationDetailedInfoResponseType = {
  personId: number;
  chargeId: number;
  createdOn: string | null;
  chargeTypeName: string | null;
  serviceName: string | null;
  dueDate: string | null;
  periodFrom: string | null;
  periodTo: string | null;
  status: OBLIGATION_STATUSES | null;
  principalValueMain: string | null;
  interestValueMain: string | null;
  currencyMainCode: string | null;
  principalValueSecond: string | null;
  interestValueSecond: string | null;
  currencySecondCode: string | null;
  notPaidMainPrincipal: string | null;
  notPaidMainInterest: string | null;
  notPaidMainTotal: string | null;
  notPaidSecondPrincipal: string | null;
  notPaidSecondInterest: string | null;
  notPaidSecondTotal: string | null;
  chargesPayments: ChargesPaymentsType[] | [];
  paymentAccessCode?: string;
};

export type UpdateObligationsFilterFunction = (
  newParams: IObligationsFilter
) => void;

export type GetObligationsPaymentIdResponseType = {
  accessCode: string;
  id: string;
};

export type GetObligationsPaymentIdPayloadType = {
  accessToken: string;
  obligations: { chargeId: number; value: number }[];
};

export type GetObligationsPaymentAccessCodeResponseType = {
  accessCode: string;
  id: string;
};

export type GetObligationsPaymentAccessCodePayloadType = {
  accessToken: string;
  id: string;
};
