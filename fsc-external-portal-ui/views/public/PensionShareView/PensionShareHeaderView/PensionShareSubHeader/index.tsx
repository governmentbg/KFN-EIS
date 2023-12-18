import Skeleton from '@mui/material/Skeleton';
import Typography from '@mui/material/Typography';

type PensionShareSubHeaderPropsType = {
  title: string;
};

const PensionShareSubHeader = ({ title }: PensionShareSubHeaderPropsType) => (
  <Typography variant="body1" component="span">
    {title ?? (
      <Skeleton
        variant="text"
        sx={{
          width: { xs: 200, lg: 250 },
          height: { xs: 20, lg: 30 },
        }}
      />
    )}
  </Typography>
);

export default PensionShareSubHeader;
