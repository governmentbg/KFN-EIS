import { useTranslation } from 'react-i18next';
import UserPassLoginForm from './UserPassLoginForm';

const UserPassLoginView = () => {
  const { t } = useTranslation(['messages']);
  return <UserPassLoginForm header={t('kfnWelcomeMessage') + '.'} />;
};
export default UserPassLoginView;
