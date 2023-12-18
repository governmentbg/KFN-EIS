import Paper from '@mui/material/Paper';
import { Form, Formik, FormikHelpers } from 'formik';
import { ConnectedFocusError } from 'focus-formik-error';
import { useRouter } from 'next/router';
import { useCallback, useContext } from 'react';
import { useAppDispatch, useAppSelector } from '../../../../../app/hooks';
import { IPersonStoreState, personSelector } from '../../../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../../../store/pnl';
import {
  createServiceRequest,
  createServiceRequestPublic,
} from '../../../../../store/serviceRequest';
import { IUser, userSelector } from '../../../../../store/user';
import ActionButtons from '../commonComponents/ActionButtons';
import ServiceRequestContext, {
  IServiceRequestContext,
} from '../ServiceRequestContext';
import ExistingServiceRequestPromptModal from './ExistingServiceRequestPromptModal';
import FormData from './FormData';
import getServiceRequestFormikInitialValues from '../../../../../utils/serviceHelpers/blank/getServiceRequestFormikInitialValues';
import { useTranslation } from 'react-i18next';
import { handleError } from '../../../../../utils/handlers/errorHandlers';
import useServiceAuthenticationLevel from '../../../../../app/hooks/service/useServiceAuthenticationLevel';
import { SERVICE_AUTHENTICATION_LEVELS } from '../../../../../contracts/enums/services';
import isNullOrUndefined from '../../../../../utils/isNullOrUndefined';
import { ERRORS } from '../../../../../constants/errors';

const FirstStep = () => {
  const dispatch = useAppDispatch();
  const router = useRouter();
  const { t } = useTranslation(['common']);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);
  const { id: personId } = useAppSelector<IPersonStoreState>(personSelector);
  const {
    firstTimeOpenedService,
    handleNext,
    handleBack,
    activeStep,
    steps,
    componentsData,
    htmlForm,
    serviceRequestId,
    setServiceRequestId,
  } = useContext<IServiceRequestContext>(ServiceRequestContext);
  const { serviceAuthenticationLevel } = useServiceAuthenticationLevel();
  const handleSubmit = useCallback(
    async (values: any, { setSubmitting, setErrors }: FormikHelpers<any>) => {
      setSubmitting(false);

      try {
        const createServiceRequestActionArgs = {
          serviceId: Number(router.query.id),
          submitData: values,
          serviceRequestId,
        };

        if (isNullOrUndefined(serviceAuthenticationLevel))
          throw new Error(
            ERRORS.SERVICES.SERVICE_AUTHENTICATION_LEVEL_IS_NOT_PROVIDED
          );

        const response =
          serviceAuthenticationLevel ===
            SERVICE_AUTHENTICATION_LEVELS.PRIVATE && personId
            ? await dispatch(
                createServiceRequest({
                  accessToken,
                  pnlId: pnlId ?? null,
                  personId,
                  ...createServiceRequestActionArgs,
                })
              ).unwrap()
            : await dispatch(
                createServiceRequestPublic(createServiceRequestActionArgs)
              ).unwrap();

        setServiceRequestId(response.serviceRequestId);
        handleNext();
      } catch (e: any) {
        const apiErrors = e?.response?.data?.errors as { [key: string]: any };

        if (apiErrors) {
          setErrors(apiErrors);
          return;
        }

        handleError(e);
      }
    },
    [
      accessToken,
      dispatch,
      handleNext,
      personId,
      pnlId,
      router.query.id,
      serviceAuthenticationLevel,
      serviceRequestId,
      setServiceRequestId,
    ]
  );

  return (
    <>
      {!firstTimeOpenedService && (
        <Formik
          initialValues={getServiceRequestFormikInitialValues(componentsData)}
          onSubmit={handleSubmit}
        >
          <Form noValidate>
            <ConnectedFocusError />
            <Paper
              elevation={2}
              sx={{ p: '1rem', backgroundColor: 'common.white' }}
            >
              <FormData componentsData={componentsData} htmlForm={htmlForm} />
            </Paper>

            <ActionButtons
              handleBack={handleBack}
              activeStep={activeStep}
              steps={steps}
              rootProps={{ flexDirection: { md: 'row' }, margin: '1rem 0rem' }}
            />
          </Form>
        </Formik>
      )}

      {firstTimeOpenedService && <ExistingServiceRequestPromptModal />}
    </>
  );
};

export default FirstStep;
