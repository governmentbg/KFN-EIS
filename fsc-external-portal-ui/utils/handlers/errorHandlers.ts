import Router from 'next/router';
import { store } from '../../store';
import { logout } from '../../store/user';
import { ROUTES } from '../../constants';
import redirectToServerErrorPage from './redirectToServerErrorPage';
import { AxiosError } from 'axios';

type ErrorDefinitionType = {
  errorDefinition: { message: string; systemCode: string };
};

export const handleError = (
  error: AxiosError<ErrorDefinitionType, any> | any,
) => {
  console.trace('ERROR STACK: ', error);

  if (error.hasOwnProperty('isAxiosError')) {
    handleAxiosError(error as AxiosError<ErrorDefinitionType, any>);
  } else {
    handleDefaultError();
  }
};

const handleDefaultError = () => redirectToServerErrorPage();

const handleAxiosError = (error: AxiosError<ErrorDefinitionType, any>) => {
  const { response } = error || {};

  const { data, status } = response || {};

  const { errorDefinition } = data || {};

  const { systemCode } = errorDefinition || {};

  switch (status) {
    case 401:
      store.dispatch(logout());
      break;
    case 403:
      Router.push(ROUTES.UNAUTHORIZED);
      break;
    default:
      redirectToServerErrorPage(systemCode);
      break;
  }
};
