import Close from '@mui/icons-material/Close';
import Box from '@mui/material/Box';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import IconButton from '@mui/material/IconButton';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useRouter } from 'next/router';
import { useCallback, useContext, useEffect, useMemo, useState } from 'react';
import { useTranslation } from 'react-i18next';
import {
  useAppDispatch,
  useAppSelector,
} from '../../../../../../app/hooks/redux';
import useServiceAuthenticationLevel from '../../../../../../app/hooks/service/useServiceAuthenticationLevel';
import { SERVICE_AUTHENTICATION_LEVELS } from '../../../../../../contracts/enums/services';
import { IServiceRequest } from '../../../../../../contracts/interfaces/serviceRequest';
import { useFetchServiceQuery } from '../../../../../../store/api/servicesSlice';
import {
  IPersonStoreState,
  personSelector,
} from '../../../../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../../../../store/pnl';
import {
  deleteServiceRequest,
  getDraftServiceRequest,
} from '../../../../../../store/serviceRequest';
import { IUser, userSelector } from '../../../../../../store/user';
import { handleError } from '../../../../../../utils/handlers/errorHandlers';
import isNullOrUndefined from '../../../../../../utils/isNullOrUndefined';
import ServiceRequestContext, {
  IServiceRequestContext,
} from '../../ServiceRequestContext';
import ContinueSameServiceRequestButton from './ContinueSameServiceRequestButton';
import CreateNewServiceRequestButton from './CreateNewServiceRequestButton';
import DialogContentTextTypography from './DialogContentTextTypography';
import ModalSkeleton from './ModalSkeleton';

const ExistingServiceRequestPromptModal = () => {
  const router = useRouter();
  const { t } = useTranslation(['services']);
  const dispatch = useAppDispatch();
  const { serviceAuthenticationLevel } = useServiceAuthenticationLevel();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { id: personId } = useAppSelector<IPersonStoreState>(personSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);
  const { data: service } = useFetchServiceQuery(
    { serviceId: router?.query?.id?.toString() ?? '' },
    { skip: !router?.query?.id }
  );
  const {
    firstTimeOpenedService,
    setFirstTimeOpenedService,
    setServiceRequestId,
  } = useContext<IServiceRequestContext>(ServiceRequestContext);
  const [availableDraftServiceRequest, setAvailableDraftServiceRequest] =
    useState<IServiceRequest | undefined>(undefined);

  const serviceName = useMemo(() => service?.name, [service]);

  const handleClose = useCallback(() => {
    setFirstTimeOpenedService(false);
  }, [setFirstTimeOpenedService]);

  const handleCreateNew = useCallback(async () => {
    try {
      if (!availableDraftServiceRequest?.id) return;

      await dispatch(
        deleteServiceRequest({
          accessToken,
          serviceRequestId: availableDraftServiceRequest.id,
        })
      );

      handleClose();
    } catch (error: any) {
      handleError(error);
    }
  }, [accessToken, availableDraftServiceRequest?.id, dispatch, handleClose]);

  const handleContinue = useCallback(async () => {
    if (!availableDraftServiceRequest?.id) return;

    setServiceRequestId(availableDraftServiceRequest?.id);

    handleClose();
  }, [availableDraftServiceRequest?.id, handleClose, setServiceRequestId]);

  useEffect(() => {
    const checkForDraftServiceRequest = async () => {
      if (isNullOrUndefined(serviceAuthenticationLevel)) return;

      const serviceId = Number(router.query?.id);

      let draftServiceRequestId = null;

      if (
        serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PRIVATE &&
        personId &&
        serviceId
      ) {
        draftServiceRequestId = await dispatch(
          getDraftServiceRequest({
            accessToken,
            personId,
            pnlId,
            serviceId,
          })
        ).unwrap();
      }

      if (firstTimeOpenedService && !draftServiceRequestId)
        return handleClose();

      if (firstTimeOpenedService && draftServiceRequestId)
        return setAvailableDraftServiceRequest({ id: draftServiceRequestId });
    };

    try {
      checkForDraftServiceRequest();
    } catch (error) {
      handleError(error);
    }
  }, [
    accessToken,
    dispatch,
    firstTimeOpenedService,
    handleClose,
    personId,
    pnlId,
    router.query.id,
    serviceAuthenticationLevel,
  ]);

  return (
    <Stack>
      <Dialog
        onClose={(_, reason: 'backdropClick' | 'escapeKeyDown') => {
          if (reason === ('backdropClick' || 'escapeKeyDown'))
            return handleContinue();

          handleClose();
        }}
        open={Boolean(firstTimeOpenedService && availableDraftServiceRequest)}
        aria-labelledby="existing-service-request-dialog-title"
        aria-describedby="existing-service-request-dialog-title"
        maxWidth="md"
        sx={{
          '.MuiDialog-paperWidthMd': {
            minWidth: '50%',
            backgroundColor: 'common.white',
          },
          zIndex: (theme) => theme.zIndex.modal,
        }}
      >
        {!serviceName ? (
          <ModalSkeleton />
        ) : (
          <Box sx={{ p: '1rem' }}>
            <Stack flexDirection={'row'}>
              <DialogTitle
                id="existing-service-request-dialog-title"
                sx={{ flexGrow: 1 }}
              >
                <Typography variant="h2" component="span" color="text.main">
                  {t(
                    'services.serviceRequest.firstStep.existingServiceRequestPromptModal.existingProcess.label'
                  )}
                </Typography>
              </DialogTitle>

              <IconButton
                aria-label="Close"
                color="primary"
                onClick={handleContinue}
              >
                <Close />
              </IconButton>
            </Stack>

            <DialogContent>
              <DialogContentText>
                <DialogContentTextTypography>
                  {t(
                    'services.serviceRequest.firstStep.existingServiceRequestPromptModal.youHaveAnUnfinishedProcessOn.label'
                  )}{' '}
                </DialogContentTextTypography>

                {serviceName ? (
                  <DialogContentTextTypography
                    sx={{ fontWeight: (t) => t.typography.fontWeightMedium }}
                  >
                    {serviceName}

                    {'. '}
                  </DialogContentTextTypography>
                ) : (
                  <DialogContentTextTypography
                    sx={{ textTransform: 'lowercase' }}
                  >
                    {t(
                      'services.serviceRequest.firstStep.existingServiceRequestPromptModal.currentService.label'
                    )}

                    {'. '}
                  </DialogContentTextTypography>
                )}

                <DialogContentTextTypography>
                  {t(
                    'services.serviceRequest.firstStep.existingServiceRequestPromptModal.doYouWantToContinueItOrCreateNewOne.label'
                  )}

                  {'?'}
                </DialogContentTextTypography>
              </DialogContentText>
            </DialogContent>

            <DialogActions>
              <CreateNewServiceRequestButton handleClick={handleCreateNew} />

              <ContinueSameServiceRequestButton handleClick={handleContinue} />
            </DialogActions>
          </Box>
        )}
      </Dialog>
    </Stack>
  );
};

export default ExistingServiceRequestPromptModal;
