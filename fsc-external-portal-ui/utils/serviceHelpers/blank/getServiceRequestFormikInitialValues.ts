import { ServiceRequestFormFieldComponentProps } from '../../../contracts/types/serviceRequest/formComponents/fields';
import getGroupTypeComponentInitialValues from '../formComponent/getGroupTypeComponentInitialValues';

const getServiceRequestFormikInitialValues = (
  componentsData: ServiceRequestFormFieldComponentProps[]
): { [key: string]: any } => {
  const initialValues: any = {};

  componentsData?.forEach(
    (componentInfo: ServiceRequestFormFieldComponentProps) => {
      const { name, value, values, type, fields } = componentInfo;

      switch (type) {
        case 'tableGroup': {
          return (initialValues[name] =
            values && values?.length > 0 ? values : []);
        }

        case 'group': {
          return (initialValues[name] =
            Array.isArray(values) || !values
              ? getGroupTypeComponentInitialValues(fields)
              : values);
        }

        case 'checkboxGroup': {
          return (initialValues[name] =
            Array.isArray(values) && values?.length > 0 ? values : []);
        }

        default: {
          return (initialValues[name] = value ?? '');
        }
      }
    }
  );

  return initialValues;
};

export default getServiceRequestFormikInitialValues;
