import { ERROR_SEVERITY } from '../../../contracts/enums/error';
import i18n from '../../../i18n';
import { store } from '../../../store';
import { setError } from '../../../store/error';

const showDocumentDescriptionRequiredMessage = (): void => {
  const t = i18n.t;
  store.dispatch(
    setError({
      severity: ERROR_SEVERITY.ERROR,
      title: `${t('error', { ns: 'errors' })}`,
      message: `${t(
        'services.serviceRequest.form.documents.documentDescriptionRequired',
        { ns: 'errors' }
      )}`,
    })
  );
};

export default showDocumentDescriptionRequiredMessage;
