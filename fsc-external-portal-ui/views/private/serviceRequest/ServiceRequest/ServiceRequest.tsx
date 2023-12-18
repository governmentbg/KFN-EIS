import React, { useCallback, useEffect, useMemo, useState } from 'react';
import { useRouter } from 'next/router';
import { Provider } from './ServiceRequestContext';
import { useAppDispatch, useAppSelector } from '../../../../app/hooks/redux';
import { IUser, userSelector } from '../../../../store/user';
import { useFetchServiceQuery } from '../../../../store/api/servicesSlice';
import {
  getDocumentTypesByServiceId,
  getDocumentTypesByServiceIdPublic,
  getServiceRequestDocuments,
} from '../../../../store/serviceRequest';
import { handleError } from '../../../../utils/handlers/errorHandlers';
import LinearStepper from '../../../../components/LinearStepper';
import FirstStep from './FirstStep';
import SecondStep from './SecondStep';
import ThirdStep from './ThirdStep';
import Stack from '@mui/material/Stack';
import Box from '@mui/material/Box';
import StepHeader from './commonComponents/StepHeader';
import useServiceRequestFormData from '../../../../app/hooks/serviceRequest/useServiceRequestFormData';
import { IDocument } from '../../../../contracts/interfaces/document';
import { SERVICE_AUTHENTICATION_LEVELS } from '../../../../contracts/enums/services';
import useServiceAuthenticationLevel from '../../../../app/hooks/service/useServiceAuthenticationLevel';
import isNullOrUndefined from '../../../../utils/isNullOrUndefined';
import usePreviousAndCurrent from '../../../../app/hooks/shared/usePreviousAndCurrent';
import { IPnlStoreState, pnlSelector } from '../../../../store/pnl';

const initialActiveStep: number = 1;
const stepsLength = 3;

const ServiceRequest = () => {
  const dispatch = useAppDispatch();
  const router = useRouter();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);
  const { serviceAuthenticationLevel } = useServiceAuthenticationLevel();
  const { data: service } = useFetchServiceQuery(
    { serviceId: router?.query?.id?.toString() ?? '' },
    { skip: !router?.query?.id }
  );
  const [serviceRequestId, setServiceRequestId] = useState<number | undefined>(
    undefined
  );
  const [documents, setDocuments] = useState<IDocument[]>([]);
  const [activeStep, setActiveStep] = useState<number>(initialActiveStep);
  const [firstTimeOpenedService, setFirstTimeOpenedService] =
    useState<boolean>(true);

  const [previousPnlId, currentPnlId] = usePreviousAndCurrent<
    number | undefined
  >(pnlId);

  const { componentsData, htmlForm } = useServiceRequestFormData(
    activeStep,
    serviceRequestId
  );

  const steps = useMemo(
    () => [`Обща информация`, `Добавяне на документи`, `Преглед и подаване`],
    []
  );

  const handleNext = useCallback(
    () => activeStep < stepsLength && setActiveStep(activeStep + 1),
    [activeStep]
  );

  const handleBack = useCallback(
    () => activeStep > 1 && setActiveStep(activeStep - 1),
    [activeStep]
  );

  const renderStep = useCallback((step: number) => {
    switch (step) {
      case 1:
        return <FirstStep />;
      case 2:
        return <SecondStep />;
      case 3:
        return <ThirdStep />;
      default:
        return null;
    }
  }, []);

  useEffect(() => {
    const getServiceDocumentTypes = async () => {
      const serviceId = router.query.id;
      try {
        if (!serviceId || isNullOrUndefined(serviceAuthenticationLevel)) return;

        const getDocumentTypesActionArgs = {
          serviceId: Number(serviceId),
        };

        serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PRIVATE
          ? await dispatch(
              getDocumentTypesByServiceId({
                accessToken,
                ...getDocumentTypesActionArgs,
              })
            ).unwrap()
          : await dispatch(
              getDocumentTypesByServiceIdPublic(getDocumentTypesActionArgs)
            ).unwrap();
      } catch (e) {
        handleError(e);
      }
    };

    getServiceDocumentTypes();
  }, [accessToken, dispatch, router.query.id, serviceAuthenticationLevel]);

  useEffect(() => {
    const loadServiceRequestDocuments = async () => {
      if (
        !serviceRequestId ||
        serviceAuthenticationLevel === undefined ||
        serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PUBLIC
      )
        return;

      try {
        const documents = await dispatch(
          getServiceRequestDocuments({
            accessToken,
            serviceRequestId,
          })
        ).unwrap();

        if (documents?.length > 0) {
          setDocuments(documents);
        }
      } catch (error: any) {
        handleError(error);
      }
    };

    loadServiceRequestDocuments();
  }, [accessToken, serviceRequestId, dispatch, serviceAuthenticationLevel]);

  useEffect(() => {
    if (currentPnlId !== previousPnlId) {
      setFirstTimeOpenedService(true);
    }
  }, [currentPnlId, previousPnlId, router, setFirstTimeOpenedService]);

  const contextValues = useMemo(
    () => ({
      componentsData,
      htmlForm,
      firstTimeOpenedService,
      setFirstTimeOpenedService,
      steps,
      activeStep,
      handleNext,
      handleBack,
      serviceRequestId,
      setServiceRequestId,
      documents,
      setDocuments,
    }),
    [
      activeStep,
      componentsData,
      documents,
      firstTimeOpenedService,
      handleBack,
      handleNext,
      htmlForm,
      serviceRequestId,
      steps,
    ]
  );

  return (
    <Provider value={contextValues}>
      <Stack>
        <StepHeader
          rootProps={{
            sx: {
              backgroundColor: 'secondary.main',
              padding: '.875rem',
              alignItems: 'center',
            },
            square: true,
            elevation: 0,
          }}
          typographyProps={{
            component: 'h1',
            variant: 'h2',
          }}
          title={service?.name}
        />

        <Box
          sx={{
            backgroundColor: 'common.white',
          }}
        >
          <LinearStepper
            steps={steps}
            activeStep={activeStep}
            rootProps={{
              sx: {
                p: '2rem',
                width: { md: '100%', lg: '90%', xl: '70%' },
                margin: 'auto',
              },
            }}
          />
        </Box>
        <Box
          p="0rem 1rem"
          sx={{ position: 'relative', maxHeight: '100%', maxWidth: '100%' }}
        >
          {renderStep(activeStep)}
        </Box>
      </Stack>
    </Provider>
  );
};
export default ServiceRequest;
