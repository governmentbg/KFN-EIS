import FormComponentWrapper from '../../../components/FormComponentWrapper';
import { ServiceRequestFormFieldComponentProps } from '../../../contracts/types/serviceRequest/formComponents/fields';
import FormComponent from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormComponent';

const getFieldKeyComponentPairs = (
  componentsData?: ServiceRequestFormFieldComponentProps[]
): { [key: string]: JSX.Element } => {
  let preparedComponents: { [key: string]: JSX.Element } = {};

  componentsData?.forEach(
    (componentProps: ServiceRequestFormFieldComponentProps) => {
      const { name, type, fields } = componentProps || {};

      if (type === 'group') {
        fields?.forEach((field: ServiceRequestFormFieldComponentProps) => {
          if (field.type === 'group') {
            const groupFieldKeyComponentPair = getFieldKeyComponentPairs([
              {
                ...field,
                name: `${name}.${field.name}`,
              },
            ]);

            preparedComponents = {
              ...preparedComponents,
              ...groupFieldKeyComponentPair,
            };
          } else {
            const component = (
              <FormComponentWrapper componentProps={field}>
                <FormComponent
                  componentProps={{
                    ...field,
                    name: `${name}.${field.name}`,
                  }}
                />
              </FormComponentWrapper>
            );

            preparedComponents[`${name}.${field.name}`] = component;
            preparedComponents[`${name}[${field.name}]`] = component;
          }
        });
        return;
      }

      preparedComponents[name] = (
        <FormComponentWrapper componentProps={componentProps}>
          <FormComponent componentProps={componentProps} />
        </FormComponentWrapper>
      );
    }
  );

  return preparedComponents;
};

export default getFieldKeyComponentPairs;
