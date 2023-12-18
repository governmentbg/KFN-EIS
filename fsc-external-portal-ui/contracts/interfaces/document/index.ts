import { DOCUMENT_TYPE_NECESSITY_OPTIONS } from '../../enums/documentTypes';

export interface IDocumentFileReference {
  path: string;
  fileName: string;
  storageName?: string;
  parameters?: { size?: string };
  contentType?: string;
}

export interface IDocument {
  id: number;
  documentTypeId: string;
  sceDocumentTypeId: string;
  createdDate: string;
  documentDescription: string;
  fileRef: IDocumentFileReference;
  fileReferenceStr: string | null;
  file?: File;
}

export interface IDocumentType {
  id: number;
  longName: string;
  shortName: string;
  mandatory: DOCUMENT_TYPE_NECESSITY_OPTIONS;
  documentTypeId: number;
  templateFileReference: IDocumentFileReference | null;
  templateFileReferenceStr: string | null;
}

export interface IFileValidationResponseObject {
  lineNumber: number;
  message: string;
}
