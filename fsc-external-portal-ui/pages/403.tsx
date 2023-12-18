import { useTranslation } from 'react-i18next';
import ErrorPageView from '../views/public/ErrorPageView';

const PageUnauthorized = () => {
  const { t } = useTranslation(['errors']);
  return (
      <ErrorPageView
        errorTitle={`${t('status.403.title')}`}
        errorDescription={`${t('status.403.message')}`}
      />
  );
};

export default PageUnauthorized;
