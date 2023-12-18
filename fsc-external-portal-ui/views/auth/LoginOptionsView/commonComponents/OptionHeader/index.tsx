import Typography from '@mui/material/Typography';

const OptionHeader = ({ title }: { title: string }) => (
  <Typography component="h1" variant="h2">
    {title}
  </Typography>
);

export default OptionHeader;
