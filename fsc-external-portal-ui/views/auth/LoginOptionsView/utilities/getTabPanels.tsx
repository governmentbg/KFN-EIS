import { StackProps } from '@mui/material/Stack';
import TabPanel from '../TabPanel';
import AuthOption from '../AuthOption';

export interface TabPanelProps {
  children?: React.ReactNode;
  containerProps?: StackProps;
  index: number;
  value: number;
}

const getTabPanels = (
  authOptionsKeyProcessEnvValue: {
    [key: string]: string | undefined;
  },
  tabPanelProps: Omit<TabPanelProps, 'index'>
) => {
  let index = 0;
  return Object.entries(authOptionsKeyProcessEnvValue).map(
    ([authVariableKey, authVariableValue]: [string, string | undefined]) =>
      authVariableValue === 'true' ? (
        <TabPanel index={index++} {...tabPanelProps} key={authVariableKey}>
          <AuthOption optionKey={authVariableKey} />
        </TabPanel>
      ) : null
  );
};

export default getTabPanels;
