import { OBLIGATION_STATUSES } from '../../../../../contracts/enums/dossier/account/obligations';

const isObligationsPayButtonDisabled = (
  obligationsStatus: OBLIGATION_STATUSES
) => (obligationsStatus === OBLIGATION_STATUSES.PAID ? true : false);

export default isObligationsPayButtonDisabled;
