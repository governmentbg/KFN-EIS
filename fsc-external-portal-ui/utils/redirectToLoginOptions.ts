import { NextRouter } from 'next/router';
import { CALLBACK_URL, ROUTES } from '../constants';

const redirectToLoginOptions = (router: NextRouter) => {
  window.localStorage.setItem(CALLBACK_URL, router.asPath);

  router.push(ROUTES.AUTH.LOGIN.OPTIONS);
};

export default redirectToLoginOptions;
