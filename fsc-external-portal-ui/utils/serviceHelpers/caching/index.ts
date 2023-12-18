import { SERVICES } from '../../../constants/services';
import { IService } from '../../../contracts/interfaces/services';
import { cache } from '../../server/cache';

export const setCachedServices = async (services: IService[]) =>
  await cache.set(services, SERVICES.CACHED_SERVICES_FILENAME);

export const getCachedServices = async () => {
  const services = await cache.get(SERVICES.CACHED_SERVICES_FILENAME);
  if (!services || services.length === 0) return [];
  return services;
};
