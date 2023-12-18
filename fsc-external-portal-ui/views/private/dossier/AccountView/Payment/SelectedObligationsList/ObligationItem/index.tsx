import Box from '@mui/material/Box';
import Checkbox from '@mui/material/Checkbox';
import Typography from '@mui/material/Typography';

const ObligationItem = () => {
  return (
    <Box
      sx={{
        display: 'inline-flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        width: '100%',
      }}
    >
      <Box sx={{ display: 'flex', alignItems: 'center' }}>
        <Checkbox
          aria-label="Checkbox checked"
          defaultChecked
          sx={{ p: 0, mr: '1rem' }}
        />
        <Typography>Basis of obligation</Typography>
      </Box>
      <Box>Value</Box>
    </Box>
  );
};

export default ObligationItem;
