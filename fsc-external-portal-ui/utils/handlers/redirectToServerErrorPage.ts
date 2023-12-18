import Router from 'next/router';
import { ROUTES } from '../../constants';

const redirectToServerErrorPage = (errorCode: string | number = '') =>
  Router.push(
    `${ROUTES.SERVER_ERROR}${
      errorCode ? `?errorCode=${errorCode.toString()}` : ''
    }`
  );

export default redirectToServerErrorPage;
