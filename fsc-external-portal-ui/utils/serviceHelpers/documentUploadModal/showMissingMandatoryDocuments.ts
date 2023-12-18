import { ERROR_SEVERITY } from '../../../contracts/enums/error';
import i18n from '../../../i18n';
import { store } from '../../../store';
import { setError } from '../../../store/error';

const showMissingMandatoryDocuments = () => {
  const t = i18n.t;

  store.dispatch(
    setError({
      severity: ERROR_SEVERITY.ERROR,
      title: `${t('error', { ns: 'errors' })}`,
      message: `${t('mandatoryDocumentsAreMissing', { ns: 'errors' })}`,
    })
  );
};

export default showMissingMandatoryDocuments;
