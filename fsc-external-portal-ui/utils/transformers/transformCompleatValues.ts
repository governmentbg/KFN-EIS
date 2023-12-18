import { CompleatValue } from '../../contracts/types/serviceRequest/formComponents/fields';

const transformCompleatValues = (values?: CompleatValue[]) =>
  values?.map(({ key, value }: CompleatValue) => ({
    id: key.toString(),
    label: value.toString(),
  })) ?? [];

export default transformCompleatValues;
