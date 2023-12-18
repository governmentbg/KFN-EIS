import { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import Button from '@mui/material/Button';
import Popover from '@mui/material/Popover';
import Box from '@mui/material/Box';
import FilterListIcon from '@mui/icons-material/FilterList';
import TextField from '@mui/material/TextField';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { useDateFnsLocale } from '../../../../../app/hooks/date';
import { IRequestedServicesFilter } from '../../../../../contracts/interfaces/dossier/requestedServicesFilter';
import Autocomplete from '@mui/material/Autocomplete';
import { SERVICE_STATUSES } from '../../../../../contracts/enums/dossier/services';
import transformDate from '../../../../../utils/transformers/transformDate';

const STATUS_FILTER = {
  SERVICE_OPTIONS: ['REQUESTED', 'ACCEPTED', 'AWAITS_PROCESSING'],
  REPORT_OPTIONS: ['ACCEPTED', 'AWAITS_PROCESSING', 'CORRECTION', 'CORRECTED'],
};

const RequestedServicesFilter = ({
  isReports,
  filterParams,
  updateFilterParams,
}: {
  isReports: boolean;
  filterParams: IRequestedServicesFilter;
  updateFilterParams: any;
}) => {
  const { t } = useTranslation();
  const [anchorEl, setAnchorEl] = useState<HTMLButtonElement | null>(null);
  const [dateFnsLocale] = useDateFnsLocale();

  const [serviceName, setServiceName] = useState('');
  const [incomingNumber, setIncomingNumber] = useState('');
  const [submissionDate, setSubmissionDate] = useState<
    string | Date | null | undefined
  >(null);
  const [reportDateFrom, setReportDateFrom] = useState<
    string | Date | null | undefined
  >(null);
  const [reportDateTo, setReportDateTo] = useState<
    string | Date | null | undefined
  >(null);
  const [status, setStatus] = useState<{ value: string; label: string }[] | []>(
    []
  );
  const [filterIndicator, setFilterIndicator] = useState('');
  const [filterButtonStyle, setFilterButtonStyle] = useState<
    'outlined' | 'text' | 'contained' | undefined
  >('outlined');

  const open = Boolean(anchorEl);
  const id = open ? 'publicRegister-filter' : undefined;

  const serviceStatusSelectOptions = Object.entries(SERVICE_STATUSES)
    .filter(([key, value]: [string, string]) => {
      return isReports
        ? STATUS_FILTER.REPORT_OPTIONS.includes(key)
        : STATUS_FILTER.SERVICE_OPTIONS.includes(key);
    })
    .map(([key, value]: [string, string]) => ({
      value: key,
      label: t(`services.status.${value}.label`, {
        ns: 'dossier',
      }),
    }));

  const onSubmissionDateChangeHandler = (fieldValue: Date | null) => {
    setSubmissionDate(fieldValue ? transformDate(fieldValue) : fieldValue);
  };

  const onReportDateFromChangeHandler = (fieldValue: Date | null) => {
    setReportDateFrom(fieldValue ? transformDate(fieldValue) : fieldValue);
  };

  const onReportDateToChangeHandler = (fieldValue: Date | null) => {
    setReportDateTo(fieldValue ? transformDate(fieldValue) : fieldValue);
  };

  const submitHandler = (event?: React.FormEvent<HTMLFormElement>) => {
    event?.preventDefault();
    const filterData = {
      serviceName: serviceName ?? null,
      incomingNumber: incomingNumber ?? null,
      submissionDate: submissionDate ?? null,
      reportDateFrom: reportDateFrom ?? null,
      reportDateTo: reportDateTo ?? null,
      status: status ?? [],
    };

    updateFilterParams(filterData);

    if (
      !serviceName &&
      !incomingNumber &&
      !submissionDate &&
      !status &&
      !reportDateFrom &&
      !reportDateTo &&
      filterButtonStyle !== 'outlined'
    ) {
      setFilterButtonStyle('outlined');
    }

    setAnchorEl(null);
  };

  const onClearFilterHandler = () => {
    setServiceName('');
    setIncomingNumber('');
    setSubmissionDate(null);
    setReportDateFrom(null);
    setReportDateTo(null);
    setStatus([]);

    const filterData = {
      serviceName: null,
      incomingNumber: null,
      submissionDate: null,
      reportDateFrom: null,
      reportDateTo: null,
      submissionMethod: null,
      complainantName: null,
      status: [],
    };

    updateFilterParams(filterData);

    setFilterButtonStyle('outlined');

    setAnchorEl(null);
  };

  useEffect(() => {
    setServiceName(filterParams.serviceName ?? '');
    setIncomingNumber(filterParams.incomingNumber ?? '');
    setSubmissionDate(filterParams.submissionDate);
    setReportDateFrom(filterParams.reportDateFrom);
    setReportDateTo(filterParams.reportDateTo);
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
              id="status-select-filter"
              options={serviceStatusSelectOptions}
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
                  label={t('requestedServices.dataGrid.column.status.label', {
                    ns: 'dossier',
                  })}
                />
              )}
            />
          </div>
          <div>
            <TextField
              label={t(
                isReports
                  ? 'requestedServices.dataGrid.column.reportType.label'
                  : 'requestedServices.dataGrid.column.serviceName.label',
                {
                  ns: 'dossier',
                }
              )}
              fullWidth
              size="small"
              sx={{ mb: '0.5rem' }}
              value={serviceName}
              onChange={(event) => setServiceName(event.target.value)}
            />
          </div>
          <div>
            <TextField
              label={t(
                'requestedServices.dataGrid.column.incomingNumber.label',
                {
                  ns: 'dossier',
                }
              )}
              fullWidth
              size="small"
              sx={{ mb: '0.5rem' }}
              value={incomingNumber}
              onChange={(event) => setIncomingNumber(event.target.value)}
            />
          </div>
          <div>
            <LocalizationProvider
              dateAdapter={AdapterDateFns}
              adapterLocale={dateFnsLocale}
            >
              <DesktopDatePicker
                label={t(
                  'requestedServices.dataGrid.column.submissionDate.label',
                  {
                    ns: 'dossier',
                  }
                )}
                inputFormat="dd/MM/yyyy"
                value={submissionDate}
                onChange={onSubmissionDateChangeHandler}
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
          {isReports && (
            <>
              <div>
                <LocalizationProvider
                  dateAdapter={AdapterDateFns}
                  adapterLocale={dateFnsLocale}
                >
                  <DesktopDatePicker
                    label={t(
                      'requestedServices.dataGrid.column.reportDateFrom.label',
                      {
                        ns: 'dossier',
                      }
                    )}
                    inputFormat="dd/MM/yyyy"
                    value={reportDateFrom}
                    onChange={onReportDateFromChangeHandler}
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
                    label={t(
                      'requestedServices.dataGrid.column.reportDateTo.label',
                      {
                        ns: 'dossier',
                      }
                    )}
                    inputFormat="dd/MM/yyyy"
                    value={reportDateTo}
                    onChange={onReportDateToChangeHandler}
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
            </>
          )}

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

export default RequestedServicesFilter;
