import Skeleton from '@mui/material/Skeleton';
import Typography from '@mui/material/Typography';

type PensionShareHeaderPropsType = {
  title: string;
};

const PensionShareHeader = ({ title }: PensionShareHeaderPropsType) => (
  <Typography
    component="h1"
    variant="h3"
    color="primary.main"
    tabIndex={0}
    sx={{
      alignSelf: 'center',
      paddingBottom: '0.5rem',
      paddingRight: '0.5rem',
    }}
  >
    {title ?? (
      <Skeleton
        variant="text"
        sx={{ width: { xs: 250, lg: 300 }, height: { xs: 30, lg: 40 } }}
      />
    )}
  </Typography>
);

export default PensionShareHeader;
