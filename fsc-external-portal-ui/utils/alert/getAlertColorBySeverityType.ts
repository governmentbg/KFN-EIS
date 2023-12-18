import { AlertColor } from '@mui/material/Alert';
import { ERROR_SEVERITY } from '../../contracts/enums/error';

const getAlertColorBySeverityType = (
  errorSeverity: ERROR_SEVERITY
): AlertColor => {
  switch (errorSeverity) {
    case ERROR_SEVERITY.ERROR:
      return 'error';
    case ERROR_SEVERITY.SUCCESS:
      return 'success';
    case ERROR_SEVERITY.WARNING:
      return 'warning';
    case ERROR_SEVERITY.INFO:
      return 'info';
    default:
      return 'error';
  }
};

export default getAlertColorBySeverityType;
