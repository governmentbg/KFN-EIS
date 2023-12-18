import { createContext, Dispatch, SetStateAction } from 'react';
import { IDocument } from '../../../../contracts/interfaces/document';
import { ServiceRequestFormFieldComponentProps } from '../../../../contracts/types/serviceRequest/formComponents/fields';

export interface IServiceRequestContext {
  serviceRequestId: number | undefined;
  setServiceRequestId: Dispatch<SetStateAction<number | undefined>>;
  documents: IDocument[];
  setDocuments: Dispatch<SetStateAction<IDocument[] | []>>;
  firstTimeOpenedService: boolean;
  componentsData: ServiceRequestFormFieldComponentProps[];
  htmlForm: string;
  setFirstTimeOpenedService: Dispatch<SetStateAction<boolean>>;
  steps: string[];
  activeStep: number;
  handleNext: Function;
  handleBack: Function;
}
const ServiceRequestContext = createContext({} as IServiceRequestContext);

export default ServiceRequestContext;

export const { Provider, Consumer } = ServiceRequestContext;
