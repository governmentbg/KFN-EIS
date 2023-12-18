import { ERROR_SEVERITY } from '../../../contracts/enums/error';
import { store } from '../../../store';
import { setLoader } from '../../../store/loader';
import {
  resetNotification,
  setNotification,
} from '../../../store/notification';

const showSuccessfullySubmittedServiceRequestMessage = (
  callback: (args?: any) => any
) => {
  store.dispatch(setLoader({ active: true }));
  store.dispatch(
    setNotification({
      isOpen: true,
      severity: ERROR_SEVERITY.SUCCESS,
      title: `Благодарим Ви! Подаването на документи завърши успешно!`,//TODO: To be translated
      message: '',
    })
  );

  setTimeout(() => {
    store.dispatch(setLoader({ active: false }));
  }, 0);

  setTimeout(() => {
    store.dispatch(resetNotification());
    callback();
  }, 2500);
};

export default showSuccessfullySubmittedServiceRequestMessage;
