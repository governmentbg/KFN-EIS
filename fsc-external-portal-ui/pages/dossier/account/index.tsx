import CustomLayout from '../../../layouts/CustomLayout';
import AccountView from '../../../views/private/dossier/AccountView';
import AuthGuard from '../../../components/AuthGuard';

const AccountPage = () => {
  return (
    <AuthGuard>
      <CustomLayout>
        <AccountView />
      </CustomLayout>
    </AuthGuard>
  );
};
export default AccountPage;
