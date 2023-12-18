import { createContext, Dispatch, SetStateAction } from 'react';
import { IActiveAccountProcessStep } from '../../../../contracts/interfaces/dossier/account';

export type AccountContextProps = {
  selectedObligations: number[];
  setSelectedObligations: Dispatch<SetStateAction<number[]>>;
  activeProcessStep?: IActiveAccountProcessStep;
  setActiveProcessStep: Dispatch<
    SetStateAction<IActiveAccountProcessStep | undefined>
  >;
  handleNext: any;
  handleBack: any;
};

const AccountContext = createContext({} as AccountContextProps);

export default AccountContext;
