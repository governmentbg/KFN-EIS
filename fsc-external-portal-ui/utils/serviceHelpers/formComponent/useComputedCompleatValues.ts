import { FieldMetaProps } from 'formik';
import { useEffect, useState } from 'react';
import { useAppDispatch, useAppSelector } from '../../../app/hooks';
import {
  CompleatValue,
  ServiceRequestFormFieldComponentProps,
} from '../../../contracts/types/serviceRequest/formComponents/fields';
import { getNomenclatureByNomenclatureId } from '../../../store/nomenclature';
import { IUser, userSelector } from '../../../store/user';

const useComputedCompleatValues = ({
  componentProps,
  getFieldMeta,
}: {
  componentProps: ServiceRequestFormFieldComponentProps;
  getFieldMeta: <Value>(name: string) => FieldMetaProps<Value>;
}) => {
  const dispatch = useAppDispatch();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { compleatValuesDependency, compleatValues: defaultCompleatValues } =
    componentProps || {};

  const [compleatValues, setCompleatValues] = useState<
    CompleatValue[] | undefined
  >(defaultCompleatValues ?? []);

  useEffect(() => {
    const hasCompleatValuesDependency =
      componentProps.hasOwnProperty('compleatValues') &&
      componentProps.hasOwnProperty('compleatValuesDependency') &&
      compleatValuesDependency &&
      Object.keys(compleatValuesDependency).length > 0;

    if (!hasCompleatValuesDependency) return;

    const initCompleatValues = async () => {
      const fieldDependencyValue = getFieldMeta(
        compleatValuesDependency?.fieldName
      ).value as string;

      if (!fieldDependencyValue) return;

      const newCompleatValues = await dispatch(
        getNomenclatureByNomenclatureId({
          accessToken,
          nomenclature: compleatValuesDependency?.nomenclature,
          nomenclatureId: fieldDependencyValue,
        })
      ).unwrap();

      setCompleatValues(
        newCompleatValues?.map(({ id, label }) => ({
          key: Number(id),
          value: label,
        })) ?? defaultCompleatValues
      );
    };

    initCompleatValues();
  }, [
    accessToken,
    compleatValuesDependency,
    componentProps,
    defaultCompleatValues,
    dispatch,
    getFieldMeta,
  ]);
  return { compleatValues };
};

export default useComputedCompleatValues;
