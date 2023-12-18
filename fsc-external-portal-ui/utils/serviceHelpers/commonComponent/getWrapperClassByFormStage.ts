import { SERVICE_REQUEST_FORM_STAGE } from '../../../contracts/enums/serviceRequest';

const getWrapperClassByFormStage = (
  stage: SERVICE_REQUEST_FORM_STAGE
): string => {
  switch (stage) {
    case SERVICE_REQUEST_FORM_STAGE.CREATING:
      return 'dynamic-form-first-step-form-wrapper';
    case SERVICE_REQUEST_FORM_STAGE.PREVIEW:
      return 'dynamic-form-third-step-form-wrapper';
    default:
      return '';
  }
};

export default getWrapperClassByFormStage;
