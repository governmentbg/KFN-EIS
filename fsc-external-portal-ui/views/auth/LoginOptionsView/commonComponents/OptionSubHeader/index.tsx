import Typography from '@mui/material/Typography';

const OptionSubHeader = ({ title }: { title: string }) => (
  <Typography component="span" variant="h4">
    {title}
  </Typography>
);

export default OptionSubHeader;
