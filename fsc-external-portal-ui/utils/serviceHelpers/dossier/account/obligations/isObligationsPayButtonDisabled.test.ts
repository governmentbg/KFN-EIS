import { OBLIGATION_STATUSES } from '../../../../../contracts/enums/dossier/account/obligations';
import isObligationsPayButtonDisabled from './isObligationsPayButtonDisabled';

describe('isObligationsPayButtonDisabled fn', () => {
  it('should return true if the obligation status is paid', () => {
    expect(isObligationsPayButtonDisabled(OBLIGATION_STATUSES.PAID));
  });

  it('should return true if the obligation status is cancelled', () => {
    expect(isObligationsPayButtonDisabled(OBLIGATION_STATUSES.CANCELLED));
  });

  it('should return false if the obligation status is different that PAID or CANCELLED', () => {
    expect(isObligationsPayButtonDisabled(OBLIGATION_STATUSES.NOT_PAID));
  });
});
