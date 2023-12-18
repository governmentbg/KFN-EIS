import { useTranslation } from 'react-i18next';
import ErrorPageView from '../views/public/ErrorPageView';

const PageNotFound = () => {
  const { t } = useTranslation(['errors']);
  return (
    <ErrorPageView
      errorTitle={`${t('status.404.title')}`}
      errorDescription={`${t('status.404.message')}`}
    />
  );
};

export default PageNotFound;
