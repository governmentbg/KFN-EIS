import Box, { BoxProps } from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import DataItemHeader from './DataItemHeader';

const DataItem = ({
  title,
  value,
  children,
  rootProps,
}: {
  title: string;
  value?: string | number | null | undefined;
  children?: JSX.Element;
  rootProps?: BoxProps;
}) => {
  return (
    <Box {...rootProps} sx={{ mb: '.625rem', ...(rootProps?.sx ?? {}) }}>
      <DataItemHeader>{title}</DataItemHeader>
      <Typography component="span" variant="body1">
        {value}
        {children}
      </Typography>
    </Box>
  );
};

export default DataItem;
