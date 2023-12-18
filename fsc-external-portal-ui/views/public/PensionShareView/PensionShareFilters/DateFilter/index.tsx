import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import TextField from '@mui/material/TextField';
import { DesktopDatePicker } from '@mui/x-date-pickers/DesktopDatePicker';
import format from 'date-fns/format';
import { Dispatch, SetStateAction, useCallback } from 'react';
import { useDateFnsLocale } from '../../../../../app/hooks';
import { useTranslation } from 'react-i18next';
import { handleError } from '../../../../../utils/handlers/errorHandlers';

type DateFilterPropsType = {
  date: string;
  setDate: Dispatch<SetStateAction<string>>;
  inputFormat: string;
};
const DateFilter = ({
  date,
  setDate,
  inputFormat,
}: DateFilterPropsType) => {
  const { t } = useTranslation();
  const [dateFnsLocale] = useDateFnsLocale();

  const handleChange = (fieldValue: string | null) => {
    try {
      const newValue = fieldValue
        ? format(new Date(fieldValue), inputFormat, {
            locale: dateFnsLocale,
          })
        : fieldValue;

      if (!newValue) return;

      setDate(newValue);
    } catch (e) {
      handleError(e);
    }
  };

  const getDatePickerValue = useCallback((value: string): Date | null => {
    let datePartsByDotSeparator: string[] = value?.split('.');
    let datePartsByDashSeparator: string[] = value?.split('-');

    if (datePartsByDotSeparator?.length > 1) {
      return new Date(
        Number(datePartsByDotSeparator[2]),
        Number(datePartsByDotSeparator[1]) - 1,
        +datePartsByDotSeparator[0]
      );
    }

    if (datePartsByDashSeparator?.length > 1) {
      return new Date(
        +datePartsByDashSeparator[0],
        Number(datePartsByDashSeparator[1]) - 1,
        Number(datePartsByDashSeparator[2])
      );
    }

    return null;
  }, []);

  return (
    <LocalizationProvider
      dateAdapter={AdapterDateFns}
      adapterLocale={dateFnsLocale}
    >
      <DesktopDatePicker
        label={`${t('date', { ns: 'common' })} ${t('filter', {
          ns: 'common',
        }).toLowerCase()}`}
        inputFormat={inputFormat}
        value={getDatePickerValue(date?.toString())}
        onChange={handleChange}
        renderInput={(params: any) => (
          <TextField
            {...params}
            inputProps={{ ...params.inputProps, readOnly: true }}
            disabled={true}
            size="small"
          />
        )}
      />
    </LocalizationProvider>
  );
};

export default DateFilter;
