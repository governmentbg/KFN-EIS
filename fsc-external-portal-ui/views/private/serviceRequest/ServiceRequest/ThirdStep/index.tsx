import Paper from '@mui/material/Paper';
import React, { useContext } from 'react';
import ActionButtons from '../commonComponents/ActionButtons';
import { useAppDispatch, useAppSelector } from '../../../../../app/hooks/redux';
import { userSelector } from '../../../../../store/user';
import ServiceRequestContext, {
  IServiceRequestContext,
} from '../ServiceRequestContext';
import BlankHtml from '../commonComponents/BlankHtml';
import getWrapperClassByFormStage from '../../../../../utils/serviceHelpers/commonComponent/getWrapperClassByFormStage';
import { SERVICE_REQUEST_FORM_STAGE } from '../../../../../contracts/enums/serviceRequest';
import { bissGetPort, signBISS } from '../../../../../utils/BISS';
import useFetchDocumentFile from '../../../../../store/hooks/files/useFetchDocumentFIle';
import { handleError } from '../../../../../utils/handlers/errorHandlers';
import { ENVIRONMENTS, ROUTES } from '../../../../../constants';
import { setError } from '../../../../../store/error';
import { ERROR_SEVERITY } from '../../../../../contracts/enums/error';
import { useTranslation } from 'react-i18next';
import { setLoader } from '../../../../../store/loader';
import showSuccessfullySubmittedServiceRequestMessage from '../../../../../utils/serviceHelpers/serviceRequestReview/showSuccessfullySubmittedServiceRequestMessage';
import { useRouter } from 'next/router';
import useSubmitServiceRequest from '../../../../../store/hooks/serviceRequest/useSubmitServiceRequest';
import useServiceAuthenticationLevel from '../../../../../app/hooks/service/useServiceAuthenticationLevel';
import { SERVICE_AUTHENTICATION_LEVELS } from '../../../../../contracts/enums/services';
import { getPopulatedFormAsPdfPublic } from '../../../../../store/serviceRequest';
import showBISSServerNotFound from '../../../../../utils/serviceHelpers/serviceRequestReview/showBISSServerNotFound';
import useServiceRequestHandleUserContextChange from '../../../../../app/hooks/serviceRequest/useServiceRequestHandleUserContextChange';
import { useUploadFilePublicMutation } from '../../../../../store/api/filesSlice';

const ThirdStep = () => {
  const dispatch = useAppDispatch();
  const { t } = useTranslation();
  const router = useRouter();
  const { accessToken } = useAppSelector(userSelector);
  const {
    steps,
    activeStep,
    handleBack,
    serviceRequestId,
    htmlForm,
    setServiceRequestId,
  } = useContext<IServiceRequestContext>(ServiceRequestContext);
  const { serviceAuthenticationLevel } = useServiceAuthenticationLevel();
  const { fetchDocumentFile } = useFetchDocumentFile();
  const [uploadFilePublic] = useUploadFilePublicMutation();
  const { submitServiceRequest } = useSubmitServiceRequest();

  useServiceRequestHandleUserContextChange(activeStep);

  const handleSubmit = async () => {
    try {
      if (!serviceRequestId) throw new Error('Service request id is missing!');

      switch (serviceAuthenticationLevel) {
        case SERVICE_AUTHENTICATION_LEVELS.PRIVATE: {
          try {
            await dispatch(setLoader({ active: true }));

            let bissPort = await bissGetPort();
            if (!bissPort) return showBISSServerNotFound();

            await signBISS(
              bissPort,
              dispatch,
              fetchDocumentFile,
              serviceRequestId,
              accessToken
            );

            await submitServiceRequest({
              serviceRequestId,
            });

            setServiceRequestId(undefined);

            showSuccessfullySubmittedServiceRequestMessage(() => {
              router.push(ROUTES.USER.PROFILE);
            });
          } catch (e) {
            process.env.NODE_ENV === ENVIRONMENTS.DEVELOPMENT && console.log(e);

            dispatch(
              setError({
                severity: ERROR_SEVERITY.ERROR,
                title: '',
                message: t('errorDuringSigning', { ns: 'errors' }),
              })
            );
          } finally {
            dispatch(setLoader({ active: false }));
            break;
          }
        }

        case SERVICE_AUTHENTICATION_LEVELS.PUBLIC: {
          try {
            const populatedPdfAsString: string = await dispatch(
              getPopulatedFormAsPdfPublic({ serviceRequestId })
            ).unwrap();

            const fileType = 'application/pdf';

            const fileName = `service-request-${serviceRequestId}`;
            const file = new File([populatedPdfAsString], fileName, {
              type: fileType,
            });

            const uploadFileActionArgs = {
              file,
              fileName,
            };

            const { fileRef: fileReference } = await uploadFilePublic(
              uploadFileActionArgs
            ).unwrap();

            await submitServiceRequest({
              serviceRequestId,
              fileReference,
            });

            showSuccessfullySubmittedServiceRequestMessage(() => {
              router.push(ROUTES.USER.PROFILE);

              setServiceRequestId(undefined);
            });
          } catch (e) {
            handleError(e);
          } finally {
            break;
          }
        }

        default: {
          throw new Error('No Service authentication level provided!');
        }
      }
    } catch (error) {
      handleError(error);
    }
  };

  return (
    <>
      <Paper elevation={2}>
        <BlankHtml
          htmlForm={htmlForm}
          className={getWrapperClassByFormStage(
            SERVICE_REQUEST_FORM_STAGE.PREVIEW
          )}
        />
      </Paper>

      <ActionButtons
        handleBack={handleBack}
        handleSubmit={handleSubmit}
        activeStep={3}
        steps={steps}
        rootProps={{
          flexDirection: { md: 'row' },
          margin: '1rem 0rem',
          padding: '1rem 0rem',
        }}
      />
    </>
  );
};
export default ThirdStep;
