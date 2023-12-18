import Tab, { TabProps } from '@mui/material/Tab';
import { t } from '../../../../i18n';

const getTabs = (
  authOptionsKeyProcessEnvValue: {
    [key: string]: string | undefined;
  },
  smDown: boolean,
  tabProps: Omit<TabProps, 'index'>
) => {
  let index = 0;

  const orientation = smDown ? 'horizontal' : 'vertical';

  return Object.entries(authOptionsKeyProcessEnvValue).map(
    ([authVariableKey, authVariableValue]: [string, string | undefined]) => {
      if (authVariableValue === 'true') {
        const label = t(`auth.${authVariableKey}.option.label`, { ns: 'auth' });

        return (
          <Tab
            key={label}
            label={label}
            id={`${orientation}-tab-${index}`}
            aria-controls={`${orientation}-tabpanel-${index}`}
            {...tabProps}
          />
        );
      }
    }
  );
};

export default getTabs;
