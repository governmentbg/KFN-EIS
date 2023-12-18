import { FieldMetaProps } from 'formik';
import { IsRequiredFieldConditions } from '../../../contracts/types/serviceRequest/formComponents/fields';
import compareValues from './compareValues';

const isRequiredField = (
  conditions: IsRequiredFieldConditions,
  getFieldMeta: (name: string) => FieldMetaProps<any>
): boolean => {
  if (!conditions || conditions.length === 0) return false;

  let result = true;

  for (const { fieldName, operator, valueToCompareWith } of conditions) {
    const fieldMeta = getFieldMeta(fieldName);

    if (!fieldMeta)
      throw new Error(`Field with such name as '${fieldName}' doesn't exist`);

    const { value: fieldValue } = fieldMeta;

    result = result && compareValues(fieldValue, valueToCompareWith, operator);
  }

  return result;
};

export default isRequiredField;
