import Stack, { StackProps } from '@mui/material/Stack';

interface TabPanelProps {
  children?: React.ReactNode;
  containerProps?: StackProps;
  index: number;
  value: number;
}

const TabPanel = (props: TabPanelProps) => {
  const { children, value, index, containerProps } = props;

  return (
    <Stack
      role="tabpanel"
      display={value !== index ? 'none' : 'flex'}
      id={`vertical-tabpanel-${index}`}
      aria-labelledby={`vertical-tab-${index}`}
      width="fit-content"
      padding=".5rem"
      gap="1rem"
      {...containerProps}
    >
      {value === index && children}
    </Stack>
  );
};

export default TabPanel;
