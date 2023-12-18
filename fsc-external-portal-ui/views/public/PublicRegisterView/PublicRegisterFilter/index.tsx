import { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import Button from '@mui/material/Button';
import Popover from '@mui/material/Popover';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import FilterListIcon from '@mui/icons-material/FilterList';
import { IPublicRegisterFilter } from '../../../../contracts/interfaces/publicRegisterFilter';

const PublicRegisterFilter = ({
  filterParams,
  updateFilterParams,
}: {
  filterParams: IPublicRegisterFilter;
  updateFilterParams: any;
}) => {
  const { t } = useTranslation(['common']);
  const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);

  const [name, setName] = useState('');
  const [eikBulstat, setEikBulstat] = useState('');
  const [leiCode, setLeiCode] = useState('');
  const [filterIndicator, setFilterIndicator] = useState('');
  const [filterButtonStyle, setFilterButtonStyle] = useState<
    'outlined' | 'text' | 'contained' | undefined
  >('outlined');

  const open = Boolean(anchorEl);
  const id = open ? 'publicRegister-filter' : undefined;

  const submitHandler = (event?: React.FormEvent<HTMLFormElement>) => {
    event?.preventDefault();
    const filterData = {
      name: name ?? null,
      eikBulstat: eikBulstat ?? null,
      leiCode: leiCode ?? null,
      alphabetLetter: null,
    };

    updateFilterParams(filterData);

    if (!name && !eikBulstat && !leiCode && filterButtonStyle !== 'outlined') {
      setFilterButtonStyle('outlined');
    }

    setAnchorEl(null);
  };

  const onClearFilterHandler = () => {
    setName('');
    setEikBulstat('');
    setLeiCode('');

    const filterData = {
      name: null,
      eikBulstat: null,
      leiCode: null,
      alphabetLetter: null,
    };

    updateFilterParams(filterData);

    setFilterButtonStyle('outlined');

    setAnchorEl(null);
  };

  useEffect(() => {
    setName(filterParams.name ?? '');
    setEikBulstat(filterParams.eikBulstat ?? '');
    setLeiCode(filterParams.leiCode ?? '');

    let filteredParamsCount = 0;
    Object.values(filterParams).forEach((paramValue) => {
      if (paramValue) {
        filteredParamsCount++;
      }
    });
    if (filteredParamsCount > 0 && !filterParams.alphabetLetter) {
      setFilterIndicator(` (${filteredParamsCount})`);
      setFilterButtonStyle('contained');
    } else {
      setFilterIndicator('');
      setFilterButtonStyle('outlined');
    }
  }, [filterParams]);

  return (
    <>
      <Button
        aria-describedby={id}
        variant={filterButtonStyle}
        size="small"
        startIcon={<FilterListIcon />}
        onClick={(e) => setAnchorEl(e.currentTarget)}
        sx={{
          alignSelf: 'center',
          mb: { xs: '1rem', sm: '0rem' },
          ml: 'auto',
          textTransform: 'capitalize',
        }}
      >
        {t('filter') + filterIndicator}
      </Button>
      <Popover
        id={id}
        open={open}
        anchorEl={anchorEl}
        onClose={() => {
          setAnchorEl(null);
          submitHandler();
        }}
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'left',
        }}
      >
        <Box
          component="form"
          sx={{ p: 2 }}
          noValidate
          autoComplete="off"
          onSubmit={submitHandler}
        >
          <div>
            <TextField
              label={t(
                'services.publicRegister.dataGrid.column.pnlName.label',
                {
                  ns: 'services',
                }
              )}
              fullWidth
              size="small"
              sx={{ mb: '0.5rem' }}
              value={name}
              onChange={(event) => setName(event.target.value)}
            />
          </div>
          <div>
            <TextField
              label={t('services.publicRegister.dataGrid.column.eikEgn.label', {
                ns: 'services',
              })}
              fullWidth
              size="small"
              sx={{ mb: '0.5rem' }}
              value={eikBulstat}
              onChange={(event) => setEikBulstat(event.target.value)}
            />
          </div>
          <div>
            <TextField
              label={t(
                'services.publicRegister.dataGrid.column.leiCode.label',
                {
                  ns: 'services',
                }
              )}
              fullWidth
              size="small"
              sx={{ mb: '0.5rem' }}
              value={leiCode}
              onChange={(event) => setLeiCode(event.target.value)}
            />
          </div>

          <Box sx={{ display: 'flex', ml: 'auto' }}>
            <Button
              color="primary"
              variant="contained"
              size="small"
              type="submit"
              sx={{
                marginLeft: { xs: '0rem', sm: 'auto' },
                marginBottom: { xs: '1rem', sm: '0rem' },
                textTransform: 'capitalize',
              }}
            >
              {t('apply')}
            </Button>
            <Button
              color="primary"
              variant="outlined"
              size="small"
              onClick={onClearFilterHandler}
              sx={{
                display: 'inline-block',
                marginLeft: '0.5rem',
                marginBottom: { xs: '1rem', sm: '0rem' },
                textTransform: 'capitalize',
              }}
            >
              {t('clear')}
            </Button>
          </Box>
        </Box>
      </Popover>
    </>
  );
};

export default PublicRegisterFilter;
