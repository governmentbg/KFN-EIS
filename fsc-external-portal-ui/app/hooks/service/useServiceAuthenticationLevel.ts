import { useRouter } from 'next/router';
import { useEffect, useState } from 'react';
import { ERRORS } from '../../../constants/errors';
import { SERVICE_AUTHENTICATION_LEVELS } from '../../../contracts/enums/services';
import { useFetchServiceQuery } from '../../../store/api/servicesSlice';
import { handleError } from '../../../utils/handlers/errorHandlers';

const useServiceAuthenticationLevel = () => {
  const router = useRouter();
  const { data: service } = useFetchServiceQuery(
    { serviceId: router?.query?.id?.toString() ?? '' },
    { skip: !router?.query?.id }
  );
  const [serviceAuthenticationLevel, setServiceAuthenticationLevel] = useState<
    SERVICE_AUTHENTICATION_LEVELS | undefined
  >(undefined);
  const [isPrivateService, setIsPrivateService] = useState<boolean | undefined>(
    undefined
  );

  useEffect(() => {
    try {
      if (!service) return;
      const currentServiceAuthenticationLevel =
        service.catalogElement?.serviceAuthenticationLevel;

      if (
        (service && currentServiceAuthenticationLevel === null) ||
        currentServiceAuthenticationLevel === undefined
      )
        throw new Error(
          ERRORS.SERVICES.SERVICE_AUTHENTICATION_LEVEL_IS_NOT_PROVIDED
        );

      setServiceAuthenticationLevel(currentServiceAuthenticationLevel);
      setIsPrivateService(
        Boolean(
          currentServiceAuthenticationLevel ===
            SERVICE_AUTHENTICATION_LEVELS.PRIVATE
        )
      );
    } catch (error) {
      handleError(error);
    }
  }, [service]);

  return { serviceAuthenticationLevel, isPrivateService };
};

export default useServiceAuthenticationLevel;
