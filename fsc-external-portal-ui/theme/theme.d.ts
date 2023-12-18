import { string } from 'yup';

declare module '@mui/material/styles' {
  interface Theme {
    link: {
      main: string;
      visited: string;
      hover: string;
      active: string;
    };
    button: {
      backgroundColor: string;
      color: string;
      backgroundColorDisabled: string;
      colorDisabled: string;
      hover:string;
    };
    dropdown: {
      bgColor: string;
      textColor: string;
      textColorActive: string;
    };
    divider: {
      light: string;
      dark: string;
    };
    sidebar: {
      level: {
        any: {
          backgroundColor: string;
          textColor: string;
          backgroundColorSelected: string;
          backgroundColorHovered: string;
          textColorSelected: string;
          borderColor: string;
        };
        1: {
          backgroundColor: string;
          textColor: string;
          backgroundColorSelected: string;
          textColorSelected: string;
        };
        2: {
          backgroundColor: string;
          textColor: string;
          backgroundColorSelected: string;
          textColorSelected: string;
        };
        3: {
          backgroundColor: string;
          textColor: string;
          backgroundColorSelected: string;
          textColorSelected: string;
        };
        4: {
          backgroundColor: string;
          textColor: string;
          backgroundColorSelected: string;
          textColorSelected: string;
        };
        5: {
          backgroundColor: string;
          textColor: string;
          backgroundColorSelected: string;
          textColorSelected: string;
        };
      };
    };
  }
  // allow configuration using `createTheme`
  interface ThemeOptions {
    link?: {
      main?: string;
      visited?: string;
      hover?: string;
      active?: string;
    };
    button?: {
      backgroundColor?: string;
      color?: string;
      backgroundColorDisabled?: string;
      colorDisabled?: string;
      hover?:string;
    };
    dropdown?: {
      bgColor?: string;
      textColor?: string;
      textColorActive?: string;
    };
    divider?: {
      light?: string;
      dark?: string;
    };
    sidebar?: {
      level?: {
        any?: {
          backgroundColor?: string;
          textColor?: string;
          backgroundColorSelected?: string;
          backgroundColorHovered?: string;
          textColorSelected?: string;
          borderColor: string;
        };
        1?: {
          backgroundColor?: string;
          textColor?: string;
          backgroundColorSelected?: string;
          textColorSelected?: string;
        };
        2?: {
          backgroundColor?: string;
          textColor?: string;
          backgroundColorSelected?: string;
          textColorSelected?: string;
        };
        3?: {
          backgroundColor?: string;
          textColor?: string;
          backgroundColorSelected?: string;
          textColorSelected?: string;
        };
        4?: {
          backgroundColor?: string;
          textColor?: string;
          backgroundColorSelected?: string;
          textColorSelected?: string;
        };
        5?: {
          backgroundColor?: string;
          textColor?: string;
          backgroundColorSelected?: string;
          textColorSelected?: string;
        };
      };
    };
  }
  export function createTheme(options?: ThemeOptions): Theme;
}
