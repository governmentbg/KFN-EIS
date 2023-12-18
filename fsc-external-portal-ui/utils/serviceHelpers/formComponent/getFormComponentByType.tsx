import { FormComponentPropsType } from '../../../contracts/types/serviceRequest/formComponents/fields';
import FormInput from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormInput';
import FormSelect from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormSelect';
import FormTextarea from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormTextarea';
import FormCheckbox from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormCheckbox';
import FormRadioButtonGroup from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormRadioButtonGroup';
import FormDatePicker from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormDatePicker';
import FormDataGrid from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormDataGrid';
import FormCheckboxGroup from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/fields/FormCheckboxGroup';

const getFormComponentByType = (props: FormComponentPropsType) => {
  const {
    componentProps: { type },
  } = props;

  switch (type) {
    case 'input':
      return <FormInput {...props} />;
    case 'select':
      return <FormSelect {...props} />;
    case 'textarea':
      return <FormTextarea {...props} />;
    case 'checkbox':
      return <FormCheckbox {...props} />;
    case 'checkboxGroup':
      return <FormCheckboxGroup {...props} />;
    case 'radio':
      return <FormRadioButtonGroup {...props} />;
    case 'datePicker':
      return <FormDatePicker {...props} />;
    case 'tableGroup':
      return <FormDataGrid {...props} />;
    default:
      return null;
  }
};

export default getFormComponentByType;
