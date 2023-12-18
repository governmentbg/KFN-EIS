import CustomLayout from '../../../layouts/CustomLayout';
import RequestedServicesView from '../../../views/private/dossier/RequestedServicesView';
import AuthGuard from '../../../components/AuthGuard';

const ReportsPage = () => (
  <AuthGuard>
    <CustomLayout>
      <RequestedServicesView isReports={true} />
    </CustomLayout>
  </AuthGuard>
);
export default ReportsPage;
