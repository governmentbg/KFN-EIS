import AuthLayout from '../../../../layouts/AuthLayout';
import DefaultLayout from '../../../../layouts/DefaultLayout';
import UserPassLoginView from '../../../../views/auth/UserPassLoginView';

const Login = () => {
  return (
    <DefaultLayout>
      <AuthLayout>
        <UserPassLoginView />
      </AuthLayout>
    </DefaultLayout>
  );
};

export default Login;
