export interface IRequestedServicesFilter {
  serviceName: string | null;
  incomingNumber: string | null;
  submissionDate: string | undefined | Date | null;
  reportDateFrom?: string | null;
  reportDateTo?: string | null;
  status: { value: string; label: string }[] | [];
}
