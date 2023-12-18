import CustomLayout from '../../layouts/CustomLayout';
import NotificationsPageView from '../../views/private/notifications/NotificationsPageView';
import AuthGuard from '../../components/AuthGuard';

const Notifications = () => (
  <AuthGuard>
    <CustomLayout>
      <NotificationsPageView />
    </CustomLayout>
  </AuthGuard>
);

export default Notifications;
