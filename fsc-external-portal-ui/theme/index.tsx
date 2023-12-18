import { blueGrey, common } from '@mui/material/colors';
import { createTheme, responsiveFontSizes } from '@mui/material/styles';
import { bgBG } from '@mui/x-data-grid';

import { THEMES } from '../constants';
import typography from './typography';

const baseConfig = {
  typography,
};

const themeConfigs = [
  {
    name: THEMES.DEFAULT,
    components: {
      MuiPaper: {
        styleOverrides: {
          root: {
            backgroundColor: common.white,
          },
        },
      },
      MuiTypography: {
        styleOverrides: {
          root: {
            wordBreak: 'break-word' as const,
          },
        },
      },
      MuiDataGrid: {
        styleOverrides: {
          checkboxInput: {
            '.MuiSvgIcon-root': { width: typography.h2, height: typography.h2 },
          },
        },
      },
      MuiInput: {
        styleOverrides: {
          root: {
            '& input::-webkit-outer-spin-button, & input::-webkit-inner-spin-button':
              {
                display: 'none',
              },
            '& input[type=number]': {
              MozAppearance: 'textfield',
            },
          },
        },
      },
      MuiTextField: {
        styleOverrides: {
          root: {
            '& .MuiFormHelperText-root': {
              whiteSpace: 'pre-wrap',
              wordBreak: 'break-word',
            },
            '& .MuiInputLabel-root.Mui-disabled': {
              color: '#000000',
            },
            '& .MuiInputBase-input.Mui-disabled': {
              WebkitTextFillColor: '#9c9a9a',
            },
          },
        },
      },
      MuiTabs: {
        styleOverrides: {
          scrollButtons: {
            '&.Mui-disabled': { opacity: 0.3 },
          },
        },
      },
    },
    palette: {
      type: 'light',
      action: {
        active: blueGrey[600],
      },
      background: {
        default: common.white,
        light: '#4c6280',
        dark: '#f4f6f8',
        paper: '#eff6fe',
      },
      primary: {
        main: '#4c6280',
      },
      secondary: {
        main: '#eff6fe',
      },
      text: {
        primary: '#3f3f3f',
        secondary: '#888888',
      },
    },
    link: {
      main: '#3f3f3f',
      visited: '#707070',
      hover: '#707070',
      active: '#4c6280',
    },
    button: {
      backgroundColor: '#4c6280',
      color: '#d8dee4',
      backgroundColorDisabled: '#888888',
      colorDisabled: common.white,
      hover: '#dbe5ef',
    },
    dropdown: {
      bgColor: '#3f3f3f',
      textColor: '#ffffff',
      textColorActive: '#ffffff',
    },
    divider: {
      light: '#ffffff',
      dark: '#4c6280',
    },
    sidebar: {
      level: {
        any: {
          backgroundColor: '#ffffff',
          textColor: '#3f3f3f',
          backgroundColorSelected: '#4c6280',
          backgroundColorHovered: '#dbe5ef',
          textColorSelected: '#ffffff',
          borderColor: '#f0f0f0',
        },
        1: {
          backgroundColor: '#ffffff',
          textColor: '#3f3f3f',
          backgroundColorSelected: '#4c6280',
          textColorSelected: '#ffffff',
        },
        2: {
          backgroundColor: '#f8fbff',
          textColor: '#3f3f3f',
          backgroundColorSelected: '#4c6280',
          textColorSelected: '#ffffff',
        },
        3: {
          backgroundColor: '#eff6fe',
          textColor: '#4d4e4f',
          backgroundColorSelected: '#4c6280',
          textColorSelected: '#ffffff',
        },
        4: {
          backgroundColor: '#dae7f7',
          textColor: '#3f3f3f',
          backgroundColorSelected: '#4c6280',
          textColorSelected: '#ffffff',
        },
        5: {
          backgroundColor: '#ffffff',
          textColor: '#4c6280',
          backgroundColorSelected: '#4c6280',
          textColorSelected: '#ffffff',
        },
      },
    },
  },
];

export const buildTheme = (settings = { theme: 'DEFAULT' }) => {
  let themeConfig = themeConfigs.find((theme) => theme.name === settings.theme);

  if (!themeConfig) {
    console.warn(new Error(`The theme ${settings.theme} is not valid!`));
    [themeConfig] = themeConfigs;
  }

  let theme = createTheme({ ...baseConfig, ...themeConfig }, bgBG);
  theme = responsiveFontSizes(theme);

  return theme;
};
