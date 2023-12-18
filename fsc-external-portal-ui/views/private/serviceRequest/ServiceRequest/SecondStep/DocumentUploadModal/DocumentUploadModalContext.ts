import { createContext, Dispatch, SetStateAction } from 'react';

export interface IDocumentUploadModalContext {
  fileFormikValues: { [key: string]: any } | undefined;
  setFileFormikValues: Dispatch<
    SetStateAction<{ [key: string]: any } | undefined>
  >;
}
const DocumentUploadModalContext = createContext(
  {} as IDocumentUploadModalContext
);

export default DocumentUploadModalContext;

export const { Provider, Consumer } = DocumentUploadModalContext;
