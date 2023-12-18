import { useEffect } from 'react';
import { useFormikContext } from 'formik';
import { ServiceRequestFormFieldComponentProps } from '../../../../../../contracts/types/serviceRequest/formComponents/fields';
import BlankHtml from '../../commonComponents/BlankHtml';
import getServiceRequestFormikInitialValues from '../../../../../../utils/serviceHelpers/blank/getServiceRequestFormikInitialValues';
import { SERVICE_REQUEST_FORM_STAGE } from '../../../../../../contracts/enums/serviceRequest';
import getWrapperClassByFormStage from '../../../../../../utils/serviceHelpers/commonComponent/getWrapperClassByFormStage';

type FormDataPropsType = {
  componentsData: ServiceRequestFormFieldComponentProps[];
  htmlForm: string;
};
const FormData = ({ componentsData, htmlForm }: FormDataPropsType) => {
  const { setValues, errors } = useFormikContext();

  useEffect(() => {
    if (errors && Object.keys(errors)?.length > 0) {
      console.log('Form errors:', errors);
    }
  }, [errors]);

  //TODO: Has to be removed later

  useEffect(() => {
    const initialValues: { [key: string]: any } =
      getServiceRequestFormikInitialValues(componentsData);
    setValues(initialValues);
  }, [componentsData, setValues]);

  return (
    <BlankHtml
      componentsData={componentsData}
      htmlForm={htmlForm}
      className={getWrapperClassByFormStage(
        SERVICE_REQUEST_FORM_STAGE.CREATING
      )}
    />
  );
};

export default FormData;
