import { useTranslation } from 'react-i18next';
import OptionHeader from '../../commonComponents/OptionHeader';
import OptionLoginEntryLink from '../../commonComponents/OptionLoginEntryLink';
import OptionSubHeader from '../../commonComponents/OptionSubHeader';

const EAuthOption = () => {
  const { t } = useTranslation('auth');
  return (
    <>
      <OptionHeader title={t('auth.E_AUTH.option.header.label')} />
      <OptionSubHeader title={t('auth.E_AUTH.option.subHeader.label') + '.'} />

      <OptionLoginEntryLink
        href={`${process.env.NEXT_PUBLIC_AUTHORIZATION_BASE_URL}/getKey`}
      />
    </>
  );
};

export default EAuthOption;
