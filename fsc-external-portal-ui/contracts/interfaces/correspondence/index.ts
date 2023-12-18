import { IDocument, IDocumentType } from '../document';

export interface ICorrespondence {
  id?: number;
  date?: Date;
}

export interface ICorrespondenceType {
  id: number;
  shortName: string;
  longName: string;
  documentType: IDocumentType;
}
