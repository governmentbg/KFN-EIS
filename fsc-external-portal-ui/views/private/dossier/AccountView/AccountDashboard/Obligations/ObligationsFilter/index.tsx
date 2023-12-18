import { useEffect, useMemo, useState } from 'react';
import { useTranslation } from 'react-i18next';
import Button from '@mui/material/Button';
import Popover from '@mui/material/Popover';
import FilterListIcon from '@mui/icons-material/FilterList';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { useDateFnsLocale } from '../../../../../../../app/hooks/date';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import Autocomplete from '@mui/material/Autocomplete';
import { OBLIGATION_STATUSES } from '../../../../../../../contracts/enums/dossier/account/obligations';
import { IObligationsFilter } from '../../../../../../../contracts/interfaces/dossier/account/obligationsFilter';
import { UpdateObligationsFilterFunction } from '../../../../../../../contracts/types/obligations';
import transformDate from '../../../../../../../utils/transformers/transformDate';

const ObligationsFilter = ({
  filterParams,
  updateFilterParams,
}: {
  filterParams: IObligationsFilter;
  updateFilterParams: UpdateObligationsFilterFunction;
}) => {
  const { t } = useTranslation(['common']);
  const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);
  const [dateFnsLocale] = useDateFnsLocale();

  const [chargeId, setChargeId] = useState('');
  const [chargeType, setChargeType] = useState('');
  const [chargeDate, setChargeDate] = useState<Date | null | undefined>(null);
  const [paymentDueDate, setPaymentDueDate] = useState<Date | null | undefined>(
    null
  );
  const [status, setStatus] = useState<{ value: string; label: string }[] | []>(
    []
  );

  const [filterIndicator, setFilterIndicator] = useState('');
  const [filterButtonStyle, setFilterButtonStyle] = useState<
    'outlined' | 'text' | 'contained' | undefined
  >('outlined');

  const open = Boolean(anchorEl);
  const id = open ? 'obligations-filter' : undefined;

  const obligationsStatusSelectOptions = useMemo(
    () =>
      Object.entries(OBLIGATION_STATUSES).map(
        ([key, value]: [string, string]) => ({
          value: key,
          label: t(`account.obligations.status.${value}.label`, {
            ns: 'dossier',
          }),
        })
      ),
    [t]
  );

  const onChargeDateChangeHandler = (fieldValue: Date | null) =>
    setChargeDate(fieldValue ? transformDate(fieldValue) : fieldValue);

  const onPaymentDueDateChangeHandler = (fieldValue: Date | null) =>
    setPaymentDueDate(fieldValue ? transformDate(fieldValue) : fieldValue);

  const submitHandler = (event?: React.FormEvent<HTMLFormElement>) => {
    event?.preventDefault();
    const filterData: IObligationsFilter = {
      chargeId: chargeId ?? null,
      chargeType: chargeType ?? null,
      chargeDate: chargeDate ?? null,
      paymentDueDate: paymentDueDate ?? null,
      status: status ?? [],
    };

    updateFilterParams(filterData);

    if (
      !chargeId &&
      !chargeType &&
      !chargeDate &&
      !paymentDueDate &&
      !status &&
      filterButtonStyle !== 'outlined'
    ) {
      setFilterButtonStyle('outlined');
    }

    setAnchorEl(null);
  };

  const onClearFilterHandler = () => {
    setChargeId('');
    setChargeType('');
    setChargeDate(null);
    setPaymentDueDate(null);
    setStatus([]);

    const filterData = {
      chargeId: null,
      chargeType: null,
      chargeDate: null,
      paymentDueDate: null,
      status: [],
    };

    updateFilterParams(filterData);

    setFilterButtonStyle('outlined');

    setAnchorEl(null);
  };

  useEffect(() => {
    setChargeId(filterParams.chargeId ?? '');
    setChargeType(filterParams.chargeType ?? '');
    setChargeDate(filterParams.chargeDate);
    setPaymentDueDate(filterParams.paymentDueDate);
    setStatus(filterParams.status ?? []);

    let filteredParamsCount = 0;
    Object.values(filterParams).forEach((paramValue) => {
      if (
        paramValue &&
        (!Array.isArray(paramValue) ||
          (Array.isArray(paramValue) && paramValue.length > 0))
      ) {
        filteredParamsCount++;
      }
    });
    if (filteredParamsCount > 0) {
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
        {t('filter', { ns: 'common' }) + filterIndicator}
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
            <Autocomplete
              multiple
              disablePortal
              id="obligations-status-select-filter"
              options={obligationsStatusSelectOptions}
              value={status}
              isOptionEqualToValue={(option, value) =>
                option.value === value.value
              }
              onChange={(_, value) => {
                setStatus(value);
              }}
              size="small"
              fullWidth
              sx={{ mb: '0.5rem' }}
              renderInput={(params) => (
                <TextField
                  {...params}
                  label={t('account.obligations.filter.status.label', {
                    ns: 'dossier',
                  })}
                />
              )}
            />
          </div>
          <div>
            <TextField
              label={t('account.obligationsDataGrid.obligationId', {
                ns: 'dossier',
              })}
              fullWidth
              size="small"
              sx={{ mb: '0.5rem' }}
              value={chargeId}
              onChange={(event) => setChargeId(event.target.value)}
            />
          </div>
          <div>
            <TextField
              label={t('account.obligationsDataGrid.obligationType', {
                ns: 'dossier',
              })}
              fullWidth
              size="small"
              sx={{ mb: '0.5rem' }}
              value={chargeType}
              onChange={(event) => setChargeType(event.target.value)}
            />
          </div>
          <div>
            <LocalizationProvider
              dateAdapter={AdapterDateFns}
              adapterLocale={dateFnsLocale}
            >
              <DesktopDatePicker
                label={t('account.obligationsDataGrid.obligationCreateDate', {
                  ns: 'dossier',
                })}
                inputFormat="dd/MM/yyyy"
                value={chargeDate}
                onChange={onChargeDateChangeHandler}
                renderInput={(params) => (
                  <TextField
                    size="small"
                    sx={{ mb: '0.5rem', width: '100%' }}
                    {...params}
                  />
                )}
              />
            </LocalizationProvider>
          </div>
          <div>
            <LocalizationProvider
              dateAdapter={AdapterDateFns}
              adapterLocale={dateFnsLocale}
            >
              <DesktopDatePicker
                label={t('account.obligationsDataGrid.obligationDueDate', {
                  ns: 'dossier',
                })}
                inputFormat="dd/MM/yyyy"
                value={paymentDueDate}
                onChange={onPaymentDueDateChangeHandler}
                renderInput={(params) => (
                  <TextField
                    size="small"
                    sx={{ mb: '0.5rem', width: '100%' }}
                    {...params}
                  />
                )}
              />
            </LocalizationProvider>
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
              {t('requestedServices.filter.button.apply.label', {
                ns: 'dossier',
              })}
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
              {t('requestedServices.filter.button.clear.label', {
                ns: 'dossier',
              })}
            </Button>
          </Box>
        </Box>
      </Popover>
    </>
  );
};

export default ObligationsFilter;
