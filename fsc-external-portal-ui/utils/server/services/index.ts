import { IService } from '../../../contracts/interfaces/services';
import { AppStore } from '../../../store';
import { fetchServices, getRunningOperationPromises } from '../../../store/api/servicesSlice';

export async function getAllServices(store: AppStore): Promise<IService[]> {
  const response = await store.dispatch(fetchServices.initiate(undefined));

  await Promise.all(getRunningOperationPromises());

  if ('data' in response) {
    const { data } = response;
    return data && data.length > 0 ? data : [];
  }

  return [];
}
