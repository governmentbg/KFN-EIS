import { useRouter } from 'next/router';
import React, { useEffect } from 'react';
import useIsAuthenticated from '../app/hooks/auth/useIsAuthenticated';
import redirectToLoginOptions from '../utils/redirectToLoginOptions';

const AuthGuard = ({ children }: { children: JSX.Element }) => {
  const isAuthenticated = useIsAuthenticated();
  const router = useRouter();

  useEffect(() => {
    const runAuthCheck = () => {
      if (!isAuthenticated) {
        redirectToLoginOptions(router);
      }
    };

    runAuthCheck();
  }, [isAuthenticated, router]);

  return <>{isAuthenticated ? children : null}</>;
};

export default AuthGuard;
