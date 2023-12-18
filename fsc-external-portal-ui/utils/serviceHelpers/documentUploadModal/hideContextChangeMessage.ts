import { store } from '../../../store';
import { resetNotification } from '../../../store/notification';

const hideContextChangeMessage = (): void => {
  store.dispatch(resetNotification());
};

export default hideContextChangeMessage;
