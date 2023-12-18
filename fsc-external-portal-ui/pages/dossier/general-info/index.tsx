import CustomLayout from '../../../layouts/CustomLayout';
import GeneralInfoView from '../../../views/private/dossier/GeneralInfoView';
import AuthGuard from '../../../components/AuthGuard';

const GeneralInfoPage = () => (
  <AuthGuard>
    <CustomLayout>
      <GeneralInfoView />
    </CustomLayout>
  </AuthGuard>
);
export default GeneralInfoPage;
