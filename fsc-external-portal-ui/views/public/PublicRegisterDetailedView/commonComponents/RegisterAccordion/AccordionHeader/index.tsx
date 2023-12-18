import Typography from '@mui/material/Typography';
import React from 'react';

const AccordionHeader = ({ children }: { children: React.ReactNode }) => (
  <Typography
    component="h3"
    color="primary.main"
    tabIndex={0}
    sx={{
      width: '100%',
      backgroundColor: '#eff6fe',
    }}
  >
    {children}
  </Typography>
);

export default AccordionHeader;
