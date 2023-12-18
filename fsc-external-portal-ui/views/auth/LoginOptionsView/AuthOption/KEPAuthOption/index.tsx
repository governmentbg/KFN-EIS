import { useTranslation } from 'react-i18next';
import { ROUTES } from '../../../../../constants';
import OptionHeader from '../../commonComponents/OptionHeader';
import OptionLoginEntryLink from '../../commonComponents/OptionLoginEntryLink';
import OptionSubHeader from '../../commonComponents/OptionSubHeader';

const KEPAuthOption = () => {
  const { t } = useTranslation('auth');
  return (
    <>
      <OptionHeader title={t('auth.KEP_AUTH.option.header.label')} />
      <OptionSubHeader
        title={t('auth.KEP_AUTH.option.subHeader.label') + '.'}
      />

      <OptionLoginEntryLink href={ROUTES.AUTH.LOGIN.KEP} />
    </>
  );
};

export default KEPAuthOption;
