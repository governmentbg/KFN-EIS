export type RequestedServicesResponseResultType = {
  serviceId: number;
  serviceName: string;
  reportDateFrom: string;
  reportDateTo: string;
  incomingNumber: string;
  submissionDate: string;
  status:
    | 'REQUESTED'
    | 'ACCEPTED'
    | 'AWAITS_PROCESSING'
    | 'CORRECTION'
    | 'CORRECTED'
    | null;
};
export type RequestedServicesResponseType = {
  totalNumberOfPages: number;
  totalNumberOfElements: number;
  currentPage: number;
  pageSize: number;
  result: RequestedServicesResponseResultType[];
};
