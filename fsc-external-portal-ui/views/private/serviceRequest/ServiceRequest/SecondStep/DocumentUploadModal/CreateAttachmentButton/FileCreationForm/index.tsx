import { useFormikContext } from 'formik';
import { useEffect } from 'react';
import { ServiceRequestFormFieldComponentProps } from '../../../../../../../../contracts/types/serviceRequest/formComponents/fields';
import parse from 'html-react-parser';
import getWrapperClassByFormStage from '../../../../../../../../utils/serviceHelpers/commonComponent/getWrapperClassByFormStage';
import getHTMLReactParserOptions from '../../../../../../../../utils/serviceHelpers/blank/getHTMLReactParserOptions';
import getServiceRequestFormikInitialValues from '../../../../../../../../utils/serviceHelpers/blank/getServiceRequestFormikInitialValues';
import { SERVICE_REQUEST_FORM_STAGE } from '../../../../../../../../contracts/enums/serviceRequest';
import BlankHtml from '../../../../commonComponents/BlankHtml';

type FileCreationFormPropsType = {
  componentsData: ServiceRequestFormFieldComponentProps[];
  htmlForm: string;
};

const FileCreationForm = ({
  componentsData,
  htmlForm,
}: FileCreationFormPropsType) => {
  const { setValues } = useFormikContext();

  useEffect(() => {
    const initialValues: { [key: string]: any } =
      getServiceRequestFormikInitialValues(componentsData);

    setValues(initialValues);
  }, [componentsData, setValues]);

  return (
    <BlankHtml
      htmlForm={htmlForm}
      componentsData={componentsData}
      className={getWrapperClassByFormStage(
        SERVICE_REQUEST_FORM_STAGE.CREATING
      )}
    />
  );
};

export default FileCreationForm;
