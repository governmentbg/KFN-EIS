import { ERROR_SEVERITY } from '../../../contracts/enums/error';
import { t } from '../../../i18n';
import { store } from '../../../store';
import { setLoader } from '../../../store/loader';
import {
  resetNotification,
  setNotification,
} from '../../../store/notification';

const showBISSServerNotFound = (callback?: (args?: any) => any) => {
  store.dispatch(setLoader({ active: true }));
  store.dispatch(
    setNotification({
      isOpen: true,
      severity: ERROR_SEVERITY.ERROR,
      title: `${t('BISSServerWasNotFound', { ns: 'errors' })}`,
      message: '',
    })
  );

  setTimeout(() => {
    store.dispatch(setLoader({ active: false }));
  }, 0);

  setTimeout(() => {
    store.dispatch(resetNotification());
    callback && callback();
  }, 4500);
};

export default showBISSServerNotFound;
