import Typography from '@mui/material/Typography';
import React from 'react';

const FieldHeader = ({ children }: { children: React.ReactNode }) => (
  <Typography
    component="h3"
    color="primary.main"
    tabIndex={0}
    sx={{
      mb: '.5rem',
      borderBottom: '1px solid #4c6280',
      backgroundColor: '#eff6fe',
      wordBreak:'break-word'
    }}
  >
    {children}
  </Typography>
);

export default FieldHeader;
