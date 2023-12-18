import { NextRouter } from 'next/router';
import { CALLBACK_URL, LOGIN_KEY, ROUTES } from '../../constants';
import { AppDispatch } from '../../store';
import { loginWithEAuth } from '../../store/user';
import { handleError } from '../handlers/errorHandlers';

const startEAuthLoginProcess = async (
  router: NextRouter,
  isAuthenticated: boolean,
  dispatch: AppDispatch
) => {
  const loginKey = router.query?.[LOGIN_KEY] as string;
  if (loginKey && !isAuthenticated) {
    try {
      await dispatch(loginWithEAuth({ loginKey }));

      const callbackUrl = window.localStorage.getItem(CALLBACK_URL);

      window.localStorage.removeItem(CALLBACK_URL);

      router.replace(callbackUrl ? callbackUrl : ROUTES.USER.PROFILE);
    } catch (e) {
      handleError(e);
    }
  }
};

export default startEAuthLoginProcess;
