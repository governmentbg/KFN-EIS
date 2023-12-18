import IconButton from '@mui/material/IconButton';
import Stack from '@mui/material/Stack';
import { useContext } from 'react';
import PageContentHeader from '../../../shared/PageContentHeader';
import ArrowBackIosNewOutlinedIcon from '@mui/icons-material/ArrowBackIosNewOutlined';
import AccountContext, { AccountContextProps } from '../AccountContext';
import PaymentMethods from './PaymentMethods';
import SelectedObligationsList from './SelectedObligationsList';

const Payment = ({ title }: { title: string }) => {
  const { setActiveProcessStep } =
    useContext<AccountContextProps>(AccountContext);
  return (
    <>
      <Stack
        display="inline-flex"
        flexDirection="row"
        alignItems="center"
        width="100%"
        sx={{ backgroundColor: (t) => t.palette.background.paper, p: '1rem' }}
      >
        <IconButton
          size="small"
          onClick={() => setActiveProcessStep(undefined)}
        >
          <ArrowBackIosNewOutlinedIcon />
        </IconButton>
        <PageContentHeader
          typographyProps={{
            component: 'h1',
            variant: 'h2',
            tabIndex: 0,
          }}
        >
          {title}
        </PageContentHeader>
      </Stack>

      <Stack
        sx={{
          display: 'flex',
          flexDirection: { xs: 'column', sm: 'column', md: 'row' },
          padding: '1rem',
        }}
      >
        <SelectedObligationsList />
        <PaymentMethods />
      </Stack>
    </>
  );
};

export default Payment;
