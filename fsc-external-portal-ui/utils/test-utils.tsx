import { FC, ReactElement } from 'react';
import { Provider } from 'react-redux';
import { render, RenderOptions } from '@testing-library/react';
import { store } from '../store';
import { ThemeProvider } from '@mui/material/styles';
import { buildTheme } from '../theme';

const AllTheProviders: FC = ({ children }) => (
  <Provider store={store}>{children}</Provider>
);

const customRender = (
  ui: ReactElement,
  options?: Omit<RenderOptions, 'wrapper'>
) => render(ui, { wrapper: AllTheProviders, ...options });

export * from '@testing-library/react';
export { customRender as render };

export const mountComponent = (children: any, path: string) =>
  customRender(<ThemeProvider theme={buildTheme()}>{children}</ThemeProvider>);
