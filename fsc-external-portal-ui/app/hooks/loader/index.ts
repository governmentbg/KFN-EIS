import { useEffect } from 'react';
import { useAppDispatch, useAppSelector } from '../redux';
import { ILoader, loaderSelector, setLoader } from '../../../store/loader';
import { useRouter } from 'next/router';

export const useLoaderOnRouteChange = () => {
  const router = useRouter();
  const dispatch = useAppDispatch();
  const { active: isLoaderActive } = useAppSelector<ILoader>(loaderSelector);

  useEffect(() => {
    const handleRouteChangeStart = () => dispatch(setLoader({ active: true }));

    const handleRouteChangeEnd = () => dispatch(setLoader({ active: false }));

    router.events.on('routeChangeStart', handleRouteChangeStart);

    router.events.on('routeChangeComplete', handleRouteChangeEnd);

    router.events.on('routeChangeError', handleRouteChangeEnd);

    return () => {
      router.events.off('routeChangeStart', handleRouteChangeStart);

      router.events.off('routeChangeComplete', handleRouteChangeEnd);

      router.events.off('routeChangeError', handleRouteChangeEnd);
    };
  }, [dispatch, router]);

  return { isLoaderActive };
};
