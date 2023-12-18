import * as React from 'react';
import '../styles/globals.scss';
import '../i18n';
import { AppProps } from 'next/app';
import { Provider } from 'react-redux';
import { DefaultSeo } from 'next-seo';
import { persistor, store, wrapper } from '../store';
import { PersistGate } from 'redux-persist/integration/react';
import SEO from '../next-seo.config';
import Head from 'next/head';
import CssBaseline from '@mui/material/CssBaseline';
import { CacheProvider, EmotionCache } from '@emotion/react';
import createEmotionCache from '../theme/createEmotionCache';
import { FC, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import { ThemeProvider } from '@mui/material/styles';
import { useAppDispatch, useAppSelector } from '../app/hooks/redux';
import Loader from '../components/Loader';
import ToastNotification from '../components/ToastNotification';
import { languageSelector } from '../store/application';
import { buildTheme } from '../theme';
import { ErrorBoundary } from 'react-error-boundary';
import { useLoaderOnRouteChange } from '../app/hooks/loader';
import ErrorPageView from '../views/public/ErrorPageView';
import { useRouter } from 'next/router';
import { ROUTES } from '../constants';
import startEAuthLoginProcess from '../utils/auth/startEAuthLoginProcess';
import useIsAuthenticated from '../app/hooks/auth/useIsAuthenticated';

// Client-side cache shared for the whole session
// of the user in the browser.

const clientSideEmotionCache = createEmotionCache();

interface IAppProps extends AppProps {
  emotionCache?: EmotionCache;
}

const MyApp: FC<IAppProps> = ({
  Component,
  pageProps,
  emotionCache = clientSideEmotionCache,
}) => {
  const router = useRouter();
  const language = useAppSelector(languageSelector);
  const dispatch = useAppDispatch();
  const { i18n } = useTranslation(['errors']);
  const isAuthenticated = useIsAuthenticated();
  useLoaderOnRouteChange();

  useEffect(() => {
    startEAuthLoginProcess(router, isAuthenticated, dispatch);
  }, [dispatch, isAuthenticated, router]);

  useEffect(() => {
    i18n?.changeLanguage(language?.id || 'en');
  }, [language, i18n]);

  return (
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <DefaultSeo {...SEO} />
        <CacheProvider value={emotionCache}>
          <Head>
            <meta
              name="viewport"
              content="initial-scale=1, width=device-width"
            />
          </Head>

          <ThemeProvider theme={buildTheme()}>
            <CssBaseline />
            <ErrorBoundary
              FallbackComponent={ErrorPageView}
              onError={(error, info) => {
                //TODO: Log the error
                //Aims to change the route to be equal to the ErrorPageView component route
                // router.push(ROUTES.SERVER_ERROR); //Will stay commented until it is needed
              }}
            >
              <ToastNotification />
              <Loader />
              <Component {...pageProps} />
            </ErrorBoundary>
          </ThemeProvider>
        </CacheProvider>
      </PersistGate>
    </Provider>
  );
};

export default wrapper.withRedux(MyApp);
