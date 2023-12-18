import Box from '@mui/material/Box';
import { useState } from 'react';
import ObligationsDataGrid from './ObligationsDataGrid';
import ObligationsFilter from './ObligationsFilter';
import { IObligationsFilter } from '../../../../../../contracts/interfaces/dossier/account/obligationsFilter';
import { UpdateObligationsFilterFunction } from '../../../../../../contracts/types/obligations';

const Obligations = () => {
  const [obligationsFilterParams, setObligationsFilterParams] =
    useState<IObligationsFilter>({
      chargeId: null,
      chargeType: null,
      chargeDate: null,
      paymentDueDate: null,
      status: [],
    });

  const updateFilterParams: UpdateObligationsFilterFunction = (
    newParams: IObligationsFilter
  ) => {
    setObligationsFilterParams((prevState) => {
      return { ...prevState, ...newParams };
    });
  };

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        height: '100%',
      }}
    >
      <Box
        sx={{
          display: 'flex',
          flexDirection: { xs: 'column-reverse', sm: 'row' },
          flexWrap: 'wrap',
          alignContent: 'center',
          mb: '1rem',
        }}
      >
        <ObligationsFilter
          filterParams={obligationsFilterParams}
          updateFilterParams={updateFilterParams}
        />
      </Box>

      <Box sx={{ height: '100%', width: '100%' }}>
        <ObligationsDataGrid filterParams={obligationsFilterParams} />
      </Box>
    </Box>
  );
};

export default Obligations;
