import { useRouter } from 'next/router';
import { useCallback, useEffect, useState } from 'react';
import { SERVICE_AUTHENTICATION_LEVELS } from '../../../contracts/enums/services';
import { ServiceRequestFormFieldComponentProps } from '../../../contracts/types/serviceRequest/formComponents/fields';
import { IPersonStoreState, personSelector } from '../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../store/pnl';
import {
  getPopulatedServiceRequestFormHtml,
  getPopulatedServiceRequestFormHtmlPublic,
  getServiceRequestFormData,
  getServiceRequestFormDataPublic,
} from '../../../store/serviceRequest';
import { IUser, userSelector } from '../../../store/user';
import { handleError } from '../../../utils/handlers/errorHandlers';
import { useAppDispatch, useAppSelector } from '../redux';
import useServiceAuthenticationLevel from '../service/useServiceAuthenticationLevel';

const useServiceRequestFormData = (
  activeStep: number,
  serviceRequestId: number | undefined
) => {
  const dispatch = useAppDispatch();
  const router = useRouter();
  const { serviceAuthenticationLevel } = useServiceAuthenticationLevel();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);
  const { id: personId } = useAppSelector<IPersonStoreState>(personSelector);
  const [componentsData, setComponentsData] = useState<
    ServiceRequestFormFieldComponentProps[]
  >([]);
  const [htmlForm, setHtmlForm] = useState<string>('');

  useEffect(() => {
    const initData = async () => {
      const serviceId = router.query?.id ? Number(router.query?.id) : null;
      const keyData = router.query?.keyData
        ? router.query?.keyData.toString()
        : null;
      if (!serviceId || activeStep !== 1) return;

      try {
        const getServiceRequestFormDataActionArgs = {
          serviceId,
        };

        let formData = null;

        switch (Number(serviceAuthenticationLevel)) {
          case Number(SERVICE_AUTHENTICATION_LEVELS.PRIVATE): {
            formData = personId
              ? await dispatch(
                  getServiceRequestFormData({
                    accessToken,
                    pnlId,
                    personId,
                    serviceRequestId,
                    ...(keyData && { keyData }),
                    ...getServiceRequestFormDataActionArgs,
                  })
                ).unwrap()
              : null;

            break;
          }
          case Number(SERVICE_AUTHENTICATION_LEVELS.PUBLIC): {
            formData = await dispatch(
              getServiceRequestFormDataPublic(
                getServiceRequestFormDataActionArgs
              )
            ).unwrap();
            break;
          }
          default:
            break;
        }

        const { jsonForm: componentsData, htmlForm } = formData || {};

        setComponentsData(
          componentsData && componentsData?.length > 0
            ? JSON.parse(componentsData)
            : []
        );
        setHtmlForm(htmlForm ?? '');
      } catch (e: any) {
        handleError(e);
      }
    };

    initData();
  }, [
    accessToken,
    activeStep,
    dispatch,
    personId,
    pnlId,
    router.query?.id,
    router.query?.keyData,
    serviceAuthenticationLevel,
    serviceRequestId,
  ]);

  useEffect(() => {
    const getPopulatedHtmlForm = async () => {
      if (!serviceRequestId || activeStep !== 3) return;

      try {
        const getPopulatedServiceRequestFormHtmlActionArgs = {
          serviceRequestId,
        };

        const responseHtml: { htmlForm: string } =
          serviceAuthenticationLevel &&
          serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PRIVATE
            ? await dispatch(
                getPopulatedServiceRequestFormHtml({
                  accessToken,
                  ...getPopulatedServiceRequestFormHtmlActionArgs,
                })
              ).unwrap()
            : await dispatch(
                getPopulatedServiceRequestFormHtmlPublic(
                  getPopulatedServiceRequestFormHtmlActionArgs
                )
              ).unwrap();

        setHtmlForm(responseHtml.htmlForm);
      } catch (e: any) {
        handleError(e);
      }
    };

    getPopulatedHtmlForm();
  }, [
    accessToken,
    activeStep,
    dispatch,
    serviceAuthenticationLevel,
    serviceRequestId,
  ]);

  return { componentsData, htmlForm };
};

export default useServiceRequestFormData;
