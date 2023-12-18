import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import { format } from 'date-fns';
import { useRouter } from 'next/router';
import { useMemo, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { useDateFnsLocale } from '../../../app/hooks';
import { PensionShareApiResponseType } from '../../../contracts/types/pensionShare';
import { useFetchPensionShareQuery } from '../../../store/api/pensionShareSlice';
import { useFetchServiceQuery } from '../../../store/api/servicesSlice';
import PensionShareDataGrid from './PensionShareDataGrid';
import DateFilter from './PensionShareFilters/DateFilter';
import PensionShareHeaderView from './PensionShareHeaderView';

const PensionShareView = () => {
  const { t } = useTranslation();
  const router = useRouter();
  const dateFilterInputFormat = useMemo(() => 'yyyy-MM-dd', []);
  const [dateFnsLocale] = useDateFnsLocale();
  const [date, setDate] = useState<string>(
    format(new Date(), dateFilterInputFormat, {
      locale: dateFnsLocale,
    })
  );
  const { data: service } = useFetchServiceQuery(
    { serviceId: router?.query?.id?.toString() ?? '' },
    { skip: !router?.query?.id }
  );
  const { data: pensionShare = [] } = useFetchPensionShareQuery({
    filterParams: { date },
  });

  const { name: serviceName } = service || {};

  return (
    <Paper
      elevation={4}
      sx={{
        display: 'flex',
        flexDirection: 'column',
        height: '100%',
        maxHeight: '100%',
        overflow: 'auto',
        p: '1rem',
        m: { xs: '0.1rem', md: '0.1rem 1rem' },
      }}
    >
      <Stack
        sx={{
          flexDirection: { sm: 'row' },
          justifyContent: { sm: 'space-between' },
          alignItems: { sm: 'center' },
          p: '0rem 0rem .5rem 0rem',
        }}
      >
        <PensionShareHeaderView
          headerTitle={serviceName ?? ''}
          subHeaderTitle={
            `${t('services.pensionShare.subHeaderLabel.label', {
              ns: 'services',
            })} ${date}` ?? ''
          }
        />

        <DateFilter
          date={date}
          setDate={setDate}
          inputFormat={dateFilterInputFormat}
        />
      </Stack>

      <PensionShareDataGrid
        pensionShare={
          pensionShare?.map(
            ({
              personName,
              dpf,
              ppf,
              upf,
              dpfps,
            }: PensionShareApiResponseType) => {
              return { personName, dpf, ppf, upf, dpfps };
            }
          ) ?? []
        }
      />
    </Paper>
  );
};

export default PensionShareView;
