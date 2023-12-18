import { DOCUMENT_TYPE_NECESSITY_OPTIONS } from '../../../contracts/enums/documentTypes';
import {
  IDocument,
  IDocumentType,
} from '../../../contracts/interfaces/document';

const checkForUniqueServiceRequestDocumentBySceDocumentTypeId = (
  documents: IDocument[],
  documentTypes: IDocumentType[],
  sceDocumentTypeId: string
): IDocument | undefined =>
  documents?.find(
    (document: IDocument) =>
      document.sceDocumentTypeId?.toString() ===
        sceDocumentTypeId?.toString() &&
      documentTypes?.find(
        (documentType: IDocumentType) =>
          documentType.id?.toString() === sceDocumentTypeId
      )?.mandatory !== DOCUMENT_TYPE_NECESSITY_OPTIONS.OPTIONAL
  );

export default checkForUniqueServiceRequestDocumentBySceDocumentTypeId;
