import { useCallback, useEffect, useState } from 'react';
import { IPersonStoreState, personSelector } from '../../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../../store/pnl';
import { useAppSelector } from '../../redux';
import usePreviousAndCurrent from '../usePreviousAndCurrent';

const useHasUserContextChange = () => {
  const { id: personId } = useAppSelector<IPersonStoreState>(personSelector);
  const { id: pnlId } = useAppSelector<IPnlStoreState>(pnlSelector);

  const [previousPnlId, currentPnlId] = usePreviousAndCurrent<
    number | undefined
  >(pnlId);

  const [previousPersonId, currentPersonId] = usePreviousAndCurrent<
    number | undefined
  >(personId);

  const [hasContextChange, setHasContextChange] = useState<boolean>(false);

  const checkForContextChange = useCallback(() => {
    if (
      (typeof previousPnlId === 'number' && typeof currentPnlId === 'number') ||
      (typeof previousPersonId === 'number' &&
        typeof currentPersonId === 'number')
    ) {
      if (
        currentPnlId !== previousPnlId ||
        currentPersonId !== previousPersonId
      ) {
        return true;
      }
    }

    return false;
  }, [currentPersonId, currentPnlId, previousPersonId, previousPnlId]);

  useEffect(() => {
    if (checkForContextChange()) setHasContextChange(true);
  }, [checkForContextChange]);

  return { hasContextChange };
};

export default useHasUserContextChange;
