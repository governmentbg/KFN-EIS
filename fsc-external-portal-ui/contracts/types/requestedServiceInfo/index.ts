import { IDocument } from '../../interfaces/document';

export type RequestedServiceInfoResponseType = {
  allowedOperation?: 'C' | 'R' | null;
  serviceRequestId: string;
  personId: number;
  serviceName: string;
  userWhoSubmitted: string;
  incomingNumber: string;
  reportDateFrom: string;
  reportDateTo: string;
  submissionMethod: string;
  submissionDate: string;
  status:
    | 'REQUESTED'
    | 'ACCEPTED'
    | 'AWAITS_PROCESSING'
    | 'CORRECTION'
    | 'CORRECTED'
    | null;
  statusDate: string;
  versionId?: string;
  mainDocument: IDocument[] | [];
  attachedDocuments: IDocument[] | [];
};
