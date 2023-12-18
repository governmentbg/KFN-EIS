import { ROUTES } from '../../../../../constants';
import OptionLoginEntryLink from '../../commonComponents/OptionLoginEntryLink';
import OptionHeader from '../../commonComponents/OptionHeader';
import OptionSubHeader from '../../commonComponents/OptionSubHeader';
import { useTranslation } from 'react-i18next';

const UserPassAuthOption = () => {
  const { t } = useTranslation(['auth']);
  return (
    <>
      <OptionHeader title={t('auth.USER_PASS_AUTH.option.header.label')} />
      <OptionSubHeader
        title={t('auth.USER_PASS_AUTH.option.subHeader.label') + '.'}
      />

      <OptionLoginEntryLink href={ROUTES.AUTH.LOGIN.USER_PASS} />
    </>
  );
};

export default UserPassAuthOption;
