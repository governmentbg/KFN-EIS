import EAuthOption from './EAuthOption';
import KEPAuthOption from './KEPAuthOption';
import UserPassAuthOption from './UserPassAuthOption';

const AuthOption = ({ optionKey }: { optionKey: string }) => {
  switch (optionKey) {
    case 'E_AUTH':
      return <EAuthOption />;
    case 'KEP_AUTH':
      return <KEPAuthOption />;
    case 'USER_PASS_AUTH':
      return <UserPassAuthOption />;
    default:
      return null;
  }
};

export default AuthOption;
