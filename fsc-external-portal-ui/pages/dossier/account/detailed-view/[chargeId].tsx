import CustomLayout from '../../../../layouts/CustomLayout';
import AuthGuard from '../../../../components/AuthGuard';
import ObligationDetailedView from '../../../../views/private/dossier/AccountView/AccountDashboard/ObligationDetailedView';

const ObligationDetailedPage = () => (
  <AuthGuard>
    <CustomLayout>
      <ObligationDetailedView />
    </CustomLayout>
  </AuthGuard>
);
export default ObligationDetailedPage;
