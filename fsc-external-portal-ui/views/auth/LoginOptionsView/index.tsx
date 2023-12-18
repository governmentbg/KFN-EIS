import * as React from 'react';
import { useTheme } from '@mui/material/styles';
import useMediaQuery from '@mui/material/useMediaQuery';
import Tabs from '@mui/material/Tabs';
import Box from '@mui/material/Box';
import getTabs from './utilities/getTabs';
import getTabPanels from './utilities/getTabPanels';

const LoginOptionsView = () => {
  const theme = useTheme();
  const smDown = useMediaQuery(theme.breakpoints.down('sm'));
  const [value, setValue] = React.useState(0);

  const handleChange = (_: React.SyntheticEvent, newValue: number) => {
    setValue(newValue);
  };

  const authOptionsKeyProcessEnvValue = {
    E_AUTH: process.env.NEXT_PUBLIC_ALLOW_E_AUTH,
    KEP_AUTH: process.env.NEXT_PUBLIC_ALLOW_KEP_AUTH,
    USER_PASS_AUTH: process.env.NEXT_PUBLIC_ALLOW_USER_PASS_AUTH,
  };

  return (
    <Box
      sx={{
        flexGrow: 1,
        display: 'flex',
        flexDirection: smDown ? 'column' : 'row',
        height: '100%',
        maxWidth: '100%',
      }}
    >
      <Tabs
        orientation={smDown ? 'horizontal' : 'vertical'}
        variant="scrollable"
        scrollButtons="auto"
        allowScrollButtonsMobile
        value={value}
        onChange={handleChange}
        aria-label={smDown ? 'Horizontal' : 'Vertical'}
        sx={{
          borderRight: 1,
          borderColor: 'divider',
          minWidth: smDown ? 'undefined' : 'min-content',
        }}
      >
        {getTabs(authOptionsKeyProcessEnvValue, smDown, {
          sx: {
            width: smDown ? 'fit-content' : 'fit-content',
            whiteSpace: smDown ? 'inherit' : 'normal',
            alignItems: smDown ? undefined : 'start',
            textAlign: smDown ? 'center' : 'start'
          },
        })}
      </Tabs>

      {getTabPanels(authOptionsKeyProcessEnvValue, { value })}
    </Box>
  );
};

export default LoginOptionsView;
