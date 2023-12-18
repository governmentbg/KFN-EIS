import Box, { BoxProps } from '@mui/material/Box';
import { Theme } from '@mui/material/styles';
import { SystemStyleObject } from '@mui/system/styleFunctionSx';

const GridContainer = <C extends React.ElementType>(
  props: BoxProps<C, { component?: C }>
) => {
  const { children } = props;

  const sxDefaultProps = {
    display: 'grid',
    gridTemplateColumns: {
      xs: '1fr',
      sm: '1fr 1fr',
      md: '1fr 1fr',
      lg: '1fr 1fr 1fr 1fr',
    },
    rowGap: '2rem',
    columnGap: '.5rem',
  } as SystemStyleObject<Theme>;

  const spreadProps =
    props?.sx &&
    typeof props.sx === 'object' &&
    Object.keys(props.sx).length > 0
      ? props.sx
      : {};

  return (
    <Box {...props} sx={{ ...sxDefaultProps, ...spreadProps }}>
      {children}
    </Box>
  );
};

export default GridContainer;
