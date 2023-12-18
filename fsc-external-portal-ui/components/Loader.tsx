import Backdrop from '@mui/material/Backdrop';
import CircularProgress from '@mui/material/CircularProgress';
import { useAppSelector } from '../app/hooks/redux';
import { ILoader, loaderSelector } from '../store/loader';

const Loader = () => {
  const { active } = useAppSelector<ILoader>(loaderSelector);

  return (
    <Backdrop
      sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.tooltip }}
      open={active}
    >
      <CircularProgress
        variant="indeterminate"
        disableShrink={true}
        sx={{
          position: 'absolute',
          top: '50%',
          left: '50%',
          transform: 'translate(-50%,-50%)',
        }}
      />
    </Backdrop>
  );
};

export default Loader;
