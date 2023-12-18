import Typography from '@mui/material/Typography';

const CellAmount = ({
  title,
  valueMain,
  currencyMain,
  valueSecond,
  currencySecond,
}: {
  title?: string;
  valueMain?: string;
  currencyMain?: string;
  valueSecond?: string;
  currencySecond?: string;
}) => {
  return (
    <>
      {title && (
        <Typography
          sx={{
            color: '#4c6280',
            fontStyle: 'italic',
            fontSize: '0.9rem',
            textDecoration: 'underline',
          }}
        >
          {title}
        </Typography>
      )}
      {valueMain && (
        <Typography sx={{ fontWeight: '600' }}>
          {valueMain} {currencyMain}
        </Typography>
      )}
      {valueSecond && (
        <Typography color="textSecondary" sx={{ fontSize: '0.875rem' }}>
          {valueSecond} {currencySecond}
        </Typography>
      )}
    </>
  );
};

export default CellAmount;
