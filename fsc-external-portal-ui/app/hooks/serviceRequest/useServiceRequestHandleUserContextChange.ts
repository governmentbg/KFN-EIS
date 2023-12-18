import router from 'next/router';
import { useEffect } from 'react';
import { ROUTES } from '../../../constants';
import { IService } from '../../../contracts/interfaces/services';
import { fetchServicesPNL } from '../../../store/api/servicesSlice';
import { setLoader } from '../../../store/loader';
import { IPersonStoreState, personSelector } from '../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../store/pnl';
import { IUser, userSelector } from '../../../store/user';
import { handleError } from '../../../utils/handlers/errorHandlers';
import hideContextChangeMessage from '../../../utils/serviceHelpers/documentUploadModal/hideContextChangeMessage';
import showContextChangeMessage from '../../../utils/serviceHelpers/documentUploadModal/showContextChangeMessage';
import { useAppDispatch, useAppSelector } from '../redux';
import useHasUserContextChange from '../shared/useHasUserContextChange';

const TRANSITION_TIME = 3000;

const useServiceRequestHandleUserContextChange = (activeStep: number) => {
  const dispatch = useAppDispatch();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { id: personId } = useAppSelector<IPersonStoreState>(personSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);
  const { hasContextChange } = useHasUserContextChange();

  useEffect(() => {
    const handleContextChange = async () => {
      try {
        dispatch(setLoader({ active: true }));

        const servicesFilteredByContext: IService[] = await dispatch(
          fetchServicesPNL.initiate({
            accessToken,
            personId,
            pnlId: pnlId ?? null,
          })
        ).unwrap();

        const openedServiceIdDuringContextChange = router?.query?.id;

        if (
          openedServiceIdDuringContextChange === null ||
          openedServiceIdDuringContextChange === undefined
        ) {
          throw new Error('Missing query param "id"');
        }

        const isCurrentlySwitchedContextHasAccessToTheService =
          servicesFilteredByContext.findIndex(
            (service: IService) =>
              service.catalogElement.id?.toString() ===
              openedServiceIdDuringContextChange?.toString()
          ) !== -1;

        dispatch(setLoader({ active: false }));

        showContextChangeMessage();

        if (isCurrentlySwitchedContextHasAccessToTheService) {
          setTimeout(() => {
            hideContextChangeMessage();

            router.reload();
          }, TRANSITION_TIME);
        } else {
          setTimeout(() => {
            hideContextChangeMessage();

            router.push(ROUTES.USER.PROFILE);
          }, TRANSITION_TIME);
        }
      } catch (error) {
        handleError(error);
      }
    };

    if (hasContextChange && activeStep !== 1) {
      handleContextChange();
    }
  }, [accessToken, activeStep, dispatch, hasContextChange, personId, pnlId]);
};

export default useServiceRequestHandleUserContextChange;
