import Typography, { TypographyProps } from '@mui/material/Typography';

const DialogContentTextTypography = <C extends React.ElementType>(
  props: TypographyProps<C, { component?: C }>
) => (
  <Typography variant="h4" component="span" color="text.main" {...props}>
    {props.children}
  </Typography>
);

export default DialogContentTextTypography;
