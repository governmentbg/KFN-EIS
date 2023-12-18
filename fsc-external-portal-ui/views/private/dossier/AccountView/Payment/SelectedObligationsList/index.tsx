import Paper from '@mui/material/Paper';
import Divider from '@mui/material/Divider';
import ObligationItem from './ObligationItem';

const SelectedObligationsList = () => {
  const arr = [1, 2];
  return (
    <Paper elevation={2} sx={{ width: '100%', p: '1rem' }}>
      {arr.map((_: any, i: number) => (
        <>
          <ObligationItem key={i} />
          {arr.length !== i + 1 && (
            <Divider sx={{ margin: '1rem 0rem 1rem 0rem' }} />
          )}
        </>
      ))}
    </Paper>
  );
};

export default SelectedObligationsList;
