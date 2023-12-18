import CustomLayout from '../../layouts/CustomLayout';
import UserProfileView from '../../views/private/UserProfileView';
import AuthGuard from '../../components/AuthGuard';

const Profile = () => {
  return (
    <AuthGuard>
      <CustomLayout>
        <UserProfileView />
      </CustomLayout>
    </AuthGuard>
  );
};

export default Profile;
