import { useTranslation } from 'react-i18next';
import ErrorPageView from '../views/public/ErrorPageView';

const PageSomethingWentWrong = () => {
  const { t } = useTranslation(['errors']);
  return (
    <ErrorPageView
      errorTitle={`${t('status.500.title')}`}
      errorDescription={`${t('status.500.message')}`}
    />
  );
};

export default PageSomethingWentWrong;
