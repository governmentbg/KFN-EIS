import { UserContext_Types } from '../../enums/userContext';

export interface IUserContext {
  personId: number;
  pnlId: number;
  name: string;
  userContextType: UserContext_Types;
  pnlType: string | null;
}
