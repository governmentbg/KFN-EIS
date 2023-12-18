import { REQUIRED_FIELD } from '../../../constants/validation';
import { ServiceRequestFormInputValidate } from '../../../contracts/types/serviceRequest/formComponents/fields';
import validateCustomField from '../../../validation/serviceRequest/firstStepFormDataValidation/validateCustomField';

type validateFieldPropsType = {
  isRequired: boolean | undefined;
  value: string;
  inputValidate: ServiceRequestFormInputValidate | undefined;
};
const validateField = ({
  isRequired,
  value,
  inputValidate,
}: validateFieldPropsType): string | undefined => {
  if (typeof value !== 'string') throw new Error('Invalid value type');

  if (!value && isRequired) {
    return REQUIRED_FIELD;
  }

  if (value && inputValidate) {
    const validationResponse: string | undefined = validateCustomField(
      value,
      inputValidate
    );

    if (typeof validationResponse === 'string') {
      return validationResponse;
    }
  }

  return undefined;
};

export default validateField;
