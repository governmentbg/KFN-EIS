import { useTranslation } from 'react-i18next';
import ErrorPageView from '../../../../views/public/ErrorPageView';

const PageEAuthenticationError = () => {
  const { t } = useTranslation(['auth']);
  return (
    <ErrorPageView
      errorTitle={`${t('auth.E_AUTH.error.title.label')}`}
      errorDescription={`${t('auth.E_AUTH.error.info.label')}`}
      showGoBackButton={false}
    />
  );
};

export default PageEAuthenticationError;
