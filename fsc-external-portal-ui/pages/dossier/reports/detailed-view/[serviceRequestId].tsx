import CustomLayout from '../../../../layouts/CustomLayout';
import RequestedServicesDetailedView from '../../../../views/private/dossier/RequestedServicesDetailedView';
import AuthGuard from '../../../../components/AuthGuard';

const ReportsDetailedPage = () => (
  <AuthGuard>
    <CustomLayout>
      <RequestedServicesDetailedView isReport={true} />
    </CustomLayout>
  </AuthGuard>
);
export default ReportsDetailedPage;
