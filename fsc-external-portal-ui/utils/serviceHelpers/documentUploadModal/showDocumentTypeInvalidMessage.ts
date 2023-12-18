import { ERROR_SEVERITY } from "../../../contracts/enums/error";
import i18n from "../../../i18n";
import { store } from "../../../store";
import { setError } from "../../../store/error";

  const showDocumentTypeInvalidMessage = (): void => {
    const t = i18n.t;
    store.dispatch(
      setError({
        severity: ERROR_SEVERITY.ERROR,
        title: `${t('error', { ns: 'errors' })}`,
        message: `${t(
          'services.serviceRequest.form.documents.documentTypeInvalid',
          { ns: 'errors' }
        )}`,
      })
    );
  }

  export default showDocumentTypeInvalidMessage