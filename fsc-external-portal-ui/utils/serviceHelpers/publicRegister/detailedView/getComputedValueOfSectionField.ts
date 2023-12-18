import { t } from '../../../../i18n';

const getComputedValueOfSectionField = (value?: string | number | null) => {
  switch (true) {
    case typeof value === 'string' && value.length > 0:
      return value;
    case typeof value === 'number':
      return value;

    default:
      return t('noData', { ns: 'common' });
  }
};

export default getComputedValueOfSectionField;
