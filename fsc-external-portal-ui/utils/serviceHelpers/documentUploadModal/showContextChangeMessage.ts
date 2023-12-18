import { ERROR_SEVERITY } from '../../../contracts/enums/error';
import i18n from '../../../i18n';
import { store } from '../../../store';
import { setNotification } from '../../../store/notification';

const showContextChangeMessage = (): void => {
  const t = i18n.t;
  store.dispatch(
    setNotification({
      isOpen: true,
      severity: ERROR_SEVERITY.INFO,
      title: ``,
      message: `${t('serviceRequest.contextChangeDuringTheProcess', {
        ns: 'messages',
      })}`,
    })
  );
};

export default showContextChangeMessage;
