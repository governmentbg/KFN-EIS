import Box from '@mui/material/Box';
import CircularProgress from '@mui/material/CircularProgress';

const SectionLoader = () => (
  <Box
    sx={{
      width: '100%',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
    }}
  >
    <CircularProgress variant="indeterminate" disableShrink={true} />
  </Box>
);

export default SectionLoader;
