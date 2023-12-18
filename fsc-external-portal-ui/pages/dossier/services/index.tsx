import CustomLayout from '../../../layouts/CustomLayout';
import RequestedServicesView from '../../../views/private/dossier/RequestedServicesView';
import AuthGuard from '../../../components/AuthGuard';

const RequestedServicesPage = () => (
  <AuthGuard>
    <CustomLayout>
      <RequestedServicesView isReports={false} />
    </CustomLayout>
  </AuthGuard>
);
export default RequestedServicesPage;
