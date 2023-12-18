import { useRouter } from 'next/router';
import { useEffect } from 'react';
import useIsAuthenticated from '../app/hooks/auth/useIsAuthenticated';
import { IService } from '../contracts/interfaces/services';
import { SERVICE_AUTHENTICATION_LEVELS } from '../contracts/enums/services';
import redirectToLoginOptions from '../utils/redirectToLoginOptions';
import isNullOrUndefined from '../utils/isNullOrUndefined';
import { ERRORS } from '../constants/errors';

type ServiceAuthGuardPropsType = {
  service: IService | undefined;
  children: JSX.Element;
};

const ServiceAuthGuard = ({ service, children }: ServiceAuthGuardPropsType) => {
  const isAuthenticated = useIsAuthenticated();
  const router = useRouter();
  const { serviceAuthenticationLevel } = service?.catalogElement || {};

  useEffect(() => {
    const runAuthCheck = () => {
      if (
        serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PRIVATE &&
        !isAuthenticated
      ) {
        redirectToLoginOptions(router);
      }
    };

    runAuthCheck();
  }, [isAuthenticated, router, serviceAuthenticationLevel]);

  if (!service) return null;

  if (service && isNullOrUndefined(serviceAuthenticationLevel))
    throw new Error(
      ERRORS.SERVICES.SERVICE_AUTHENTICATION_LEVEL_IS_NOT_PROVIDED
    );

  if (isAuthenticated) return children;

  if (
    serviceAuthenticationLevel === SERVICE_AUTHENTICATION_LEVELS.PRIVATE &&
    isAuthenticated
  )
    return children;

  if (
    serviceAuthenticationLevel !== SERVICE_AUTHENTICATION_LEVELS.PRIVATE &&
    !isAuthenticated
  )
    return children;

  return null;
};

export default ServiceAuthGuard;
