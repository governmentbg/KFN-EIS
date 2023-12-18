import { FieldMetaProps } from 'formik';
import { IsRequiredFieldConditions } from '../../../contracts/types/serviceRequest/formComponents/fields';
import isRequiredField from './isRequiredField';

const getIsRequiredFieldDecision = (
  isRequired: boolean | undefined,
  isRequiredFieldConditions: IsRequiredFieldConditions | undefined,
  getFieldMeta: (name: string) => FieldMetaProps<any>
) =>
  isRequired ||
  (isRequiredFieldConditions &&
    isRequiredFieldConditions?.length > 0 &&
    isRequiredField(isRequiredFieldConditions, getFieldMeta));

export default getIsRequiredFieldDecision;
