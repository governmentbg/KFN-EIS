import { STORE } from '../../constants/store/utils';
import { UserContext_Types } from '../../contracts/enums/userContext';
import { IUserContext } from '../../contracts/interfaces/userContext';
import { getContextIdForType } from '../../contracts/types/store/utils';
import { IPersonStoreState } from '../../store/person';
import { IPnlStoreState } from '../../store/pnl';
import getContextId from './getContextId';

const handleLoginFulfilled = (
  userContexts: IUserContext[],
  getContextIdFor: getContextIdForType
): IPersonStoreState | IPnlStoreState | undefined => {
  const userContextTypeProp = getContextId(getContextIdFor);

  if (!userContextTypeProp) throw new Error('Something went wrong');

  if (userContexts && userContexts.length > 0) {
    return {
      id: userContexts.filter(
        (c) => c.userContextType === UserContext_Types.ME
      )?.[0][userContextTypeProp],
    };
  }
};

export default handleLoginFulfilled;
