import Box from '@mui/material/Box';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import Skeleton from '@mui/material/Skeleton';
import Stack from '@mui/material/Stack';

const ModalSkeleton = () => (
  <Box sx={{ p: '1rem' }}>
    <Stack flexDirection={'row'} alignItems="baseline">
      <DialogTitle sx={{ flexGrow: 1 }}>
        <Skeleton width={200} />
      </DialogTitle>

      <Skeleton variant="rectangular" width={20} height={20} />
    </Stack>

    <DialogContent>
      <DialogContentText>
        <Skeleton />
        <Skeleton />
      </DialogContentText>
    </DialogContent>

    <DialogActions>
      <Skeleton variant="rectangular" width={110} height={30} />
      <Skeleton variant="rectangular" width={110} height={30} />
    </DialogActions>
  </Box>
);

export default ModalSkeleton;
