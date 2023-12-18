import { useEffect } from 'react';
import { IUser, logout, userSelector } from '../../../store/user';
import { useAppDispatch, useAppSelector } from '../redux';

const useIsAuthenticated = (): boolean => {
  const dispatch = useAppDispatch();
  const { isAuthenticated, expireDate, accessToken } =
    useAppSelector<IUser>(userSelector);

  useEffect(() => {
    if (
      isAuthenticated &&
      accessToken &&
      expireDate &&
      expireDate - Date.now() < 0
    ) {
      dispatch(logout());
    }
  }, [accessToken, dispatch, expireDate, isAuthenticated]);

  return Boolean(
    expireDate && expireDate - Date.now() > 0 && isAuthenticated && accessToken
  );
};

export default useIsAuthenticated;
