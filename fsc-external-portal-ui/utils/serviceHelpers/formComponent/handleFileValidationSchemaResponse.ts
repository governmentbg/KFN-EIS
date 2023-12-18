import { ERROR_SEVERITY } from '../../../contracts/enums/error';
import { IFileValidationResponseObject } from '../../../contracts/interfaces/document';
import i18n from '../../../i18n';
import { store } from '../../../store';
import { setError } from '../../../store/error';

const handleFileValidationSchemaResponse = (
  validationResponse: IFileValidationResponseObject[]
): void => {
  const t = i18n.t;
  if (!validationResponse || validationResponse.length === 0) return;

  let message = '';
  const lineBreak = '\n';
  const whiteSpace = ' ';
  const colon = ':';

  validationResponse.forEach(
    ({
      lineNumber: lineNumberValue,
      message: errorMessageValue,
    }: IFileValidationResponseObject) => {
      message = message.concat(
        t(
          'services.serviceRequest.documents.uploadModal.fileValidationErrorTitle.label',
          { ns: 'services' }
        ),
        colon,
        lineBreak,
        `-`,
        whiteSpace,
        t(
          'services.serviceRequest.documents.uploadModal.fileValidationErrorMessage.lineNumber.label',
          { ns: 'services' }
        ),
        colon,
        whiteSpace,
        lineNumberValue.toString(),
        lineBreak,
        `-`,
        whiteSpace,
        t(
          'services.serviceRequest.documents.uploadModal.fileValidationErrorMessage.message.label',
          { ns: 'services' }
        ),
        colon,
        whiteSpace,
        errorMessageValue,
        lineBreak,
        lineBreak
      );
    }
  );
  store.dispatch(
    setError({
      severity: ERROR_SEVERITY.ERROR,
      title: t('error', { ns: 'errors' }),
      message,
    })
  );
};

export default handleFileValidationSchemaResponse;
