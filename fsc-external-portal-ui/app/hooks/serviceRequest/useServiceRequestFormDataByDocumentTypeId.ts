import { useEffect, useState } from 'react';
import { IPersonStoreState, personSelector } from '../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../store/pnl';
import { getServiceRequestFormDataByDocumentTypeId } from '../../../store/serviceRequest';
import { IUser, userSelector } from '../../../store/user';
import { handleError } from '../../../utils/handlers/errorHandlers';
import { useAppDispatch, useAppSelector } from '../redux';

type useServiceRequestFormDataByDocumentTypeIdParams = {
  documentTypeId: string;
};
type useServiceRequestFormDataByDocumentTypeIdOptions = {
  skipFetching?: boolean;
};

const useServiceRequestFormDataByDocumentTypeId = (
  { documentTypeId }: useServiceRequestFormDataByDocumentTypeIdParams,
  { skipFetching = false }: useServiceRequestFormDataByDocumentTypeIdOptions
) => {
  const dispatch = useAppDispatch();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const { id: personId } = useAppSelector<IPersonStoreState>(personSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);
  const [componentsData, setComponentsData] = useState<any>(undefined);
  const [htmlForm, setHtmlForm] = useState<any>(undefined);

  useEffect(() => {
    if (skipFetching) return;

    const fetchServiceRequestFormData = async () => {
      try {
        const { jsonForm: componentsData, htmlForm } = await dispatch(
          getServiceRequestFormDataByDocumentTypeId({
            accessToken,
            personId: personId ?? null,
            pnlId: pnlId ?? null,
            documentTypeId,
          })
        ).unwrap();

        setComponentsData(JSON.parse(componentsData));
        setHtmlForm(htmlForm);
      } catch (e) {
        handleError(e);
      }
    };

    fetchServiceRequestFormData();
  }, [accessToken, dispatch, documentTypeId, personId, pnlId, skipFetching]);

  return [componentsData, htmlForm];
};

export default useServiceRequestFormDataByDocumentTypeId;
