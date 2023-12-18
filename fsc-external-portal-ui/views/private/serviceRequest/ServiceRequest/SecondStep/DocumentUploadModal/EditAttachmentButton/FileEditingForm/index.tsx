import getWrapperClassByFormStage from '../../../../../../../../utils/serviceHelpers/commonComponent/getWrapperClassByFormStage';
import parse from 'html-react-parser';
import getHTMLReactParserOptions from '../../../../../../../../utils/serviceHelpers/blank/getHTMLReactParserOptions';
import getFieldKeyComponentPairs from '../../../../../../../../utils/serviceHelpers/blank/getFieldKeyComponentPairs';
import { ServiceRequestFormFieldComponentProps } from '../../../../../../../../contracts/types/serviceRequest/formComponents/fields';
import { useEffect } from 'react';
import { useFormikContext } from 'formik';
import { SERVICE_REQUEST_FORM_STAGE } from '../../../../../../../../contracts/enums/serviceRequest';
import BlankHtml from '../../../../commonComponents/BlankHtml';

type FileEditingFormPropsType = {
  componentsData: ServiceRequestFormFieldComponentProps[];
  htmlForm: string;
  fileFormikValues: { [key: string]: any } | undefined;
};

const FileEditingForm = ({
  componentsData,
  htmlForm,
  fileFormikValues,
}: FileEditingFormPropsType) => {
  const { setValues } = useFormikContext<any>();

  useEffect(() => {
    fileFormikValues && setValues(fileFormikValues);
  }, [fileFormikValues, setValues]);

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

export default FileEditingForm;
