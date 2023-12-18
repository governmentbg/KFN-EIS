import AuthLayout from '../../../../layouts/AuthLayout';
import DefaultLayout from '../../../../layouts/DefaultLayout';
import LoginOptionsView from '../../../../views/auth/LoginOptionsView';

const AuthOptionsPage = () => {
  return (
    <DefaultLayout>
      <AuthLayout>
        <LoginOptionsView />
      </AuthLayout>
    </DefaultLayout>
  );
};

export default AuthOptionsPage;
