export type PublicRegisterResponseResultType = {
  pnlId: number;
  pnlName: string;
  eikEgn: string;
  leiCode: string;
};
export type PublicRegisterResponseType = {
  totalNumberOfPages: number;
  totalNumberOfElements: number;
  currentPage: number;
  pageSize: number;
  result: PublicRegisterResponseResultType[];
};
