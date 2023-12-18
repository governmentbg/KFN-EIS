import CustomLayout from '../../../../layouts/CustomLayout';
import RequestedServicesDetailedView from '../../../../views/private/dossier/RequestedServicesDetailedView';
import AuthGuard from '../../../../components/AuthGuard';

const RequestedServicesDetailedPage = () => (
  <AuthGuard>
    <CustomLayout>
      <RequestedServicesDetailedView isReport={false} />
    </CustomLayout>
  </AuthGuard>
);
export default RequestedServicesDetailedPage;
