import { ServiceRequestFormFieldComponentProps } from '../../../contracts/types/serviceRequest/formComponents/fields';
type GetGroupTypeComponentInitialValuesReturnType<T> = {
  [key: string]: T | GetGroupTypeComponentInitialValuesReturnType<T>;
};

const getGroupTypeComponentInitialValues = (
  fields: ServiceRequestFormFieldComponentProps[] | undefined
): GetGroupTypeComponentInitialValuesReturnType<any> => {
  const groupValues: {
    [key: string]:
      | string
      | GetGroupTypeComponentInitialValuesReturnType<string>
      | GetGroupTypeComponentInitialValuesReturnType<string>[];
  } = {};

  fields?.forEach(
    ({
      type,
      name,
      value,
      fields: groupFields,
      values,
    }: ServiceRequestFormFieldComponentProps) => {
      if (type === 'group') {
        groupValues[name] = getGroupTypeComponentInitialValues(groupFields);
      } else if (type === 'tableGroup') {
        groupValues[name] =
          Array.isArray(values) && values.length > 0
            ? (values as GetGroupTypeComponentInitialValuesReturnType<string>[])
            : [];
      } else {
        groupValues[name] = value ?? '';
      }
    }
  );

  return groupValues;
};

export default getGroupTypeComponentInitialValues;
