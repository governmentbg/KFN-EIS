import { STORE } from "../../constants/store/utils";
import { getContextIdForType, getContextIdReturnType } from "../../contracts/types/store/utils";

const getContextId = (
  getContextIdFor: getContextIdForType
): getContextIdReturnType | undefined => {
  switch (getContextIdFor) {
    case STORE.PERSON:
      return STORE.PERSON_ID;
    case STORE.PNL:
      return STORE.PNL_ID;
    default:
      break;
  }
};

export default getContextId;
