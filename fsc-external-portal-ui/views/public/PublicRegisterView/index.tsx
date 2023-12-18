import { useState } from 'react';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import { useRouter } from 'next/router';
import { useTranslation } from 'react-i18next';
import { useFetchServiceQuery } from '../../../store/api/servicesSlice';
import PublicRegisterDataGrid from './PublicRegisterDataGrid';
import PublicRegisterFilter from './PublicRegisterFilter';
import { IPublicRegisterFilter } from '../../../contracts/interfaces/publicRegisterFilter';

const alphabet = [
  {
    title: 'А',
    value: 'А',
  },
  {
    title: 'Б',
    value: 'Б',
  },
  {
    title: 'В',
    value: 'В',
  },
  {
    title: 'Г',
    value: 'Г',
  },
  {
    title: 'Д',
    value: 'Д',
  },
  {
    title: 'Е',
    value: 'Е',
  },
  {
    title: 'Ж',
    value: 'Ж',
  },
  {
    title: 'З',
    value: 'З',
  },
  {
    title: 'И',
    value: 'И',
  },
  {
    title: 'Й',
    value: 'Й',
  },
  {
    title: 'К',
    value: 'К',
  },
  {
    title: 'Л',
    value: 'Л',
  },
  {
    title: 'М',
    value: 'М',
  },
  {
    title: 'Н',
    value: 'Н',
  },
  {
    title: 'О',
    value: 'О',
  },
  {
    title: 'П',
    value: 'П',
  },
  {
    title: 'Р',
    value: 'Р',
  },
  {
    title: 'С',
    value: 'С',
  },
  {
    title: 'Т',
    value: 'Т',
  },
  {
    title: 'У',
    value: 'У',
  },
  {
    title: 'Ф',
    value: 'Ф',
  },
  {
    title: 'Х',
    value: 'Х',
  },
  {
    title: 'Ц',
    value: 'Ц',
  },
  {
    title: 'Ч',
    value: 'Ч',
  },
  {
    title: 'Ш',
    value: 'Ш',
  },
  {
    title: 'Щ',
    value: 'Щ',
  },
  {
    title: 'Ъ',
    value: 'Ъ',
  },
  {
    title: 'ь',
    value: 'ь',
  },
  {
    title: 'Ю',
    value: 'Ю',
  },
  {
    title: 'Я',
    value: 'Я',
  },
  {
    title: 'Всички',
    value: null,
  },
];

const PublicRegisterView = () => {
  const { t } = useTranslation();
  const router = useRouter();
  const { data: service } = useFetchServiceQuery(
    { serviceId: router?.query?.id?.toString() ?? '' },
    { skip: !router?.query?.id }
  );
  const [selectedAlphabet, setSelectedAlphabet] = useState<string | null>(
    alphabet[alphabet.length - 1].value
  );
  const [filterParams, setFilterParams] = useState<IPublicRegisterFilter>({
    name: null,
    eikBulstat: null,
    leiCode: null,
    alphabetLetter: null,
  });

  const handleAlphabetFilterChange = (letterValue: string | null) => {
    const newFilterParams = {
      name: null,
      eikBulstat: null,
      leiCode: null,
      alphabetLetter: letterValue,
    };

    updateFilterParams(newFilterParams);
  };

  const updateFilterParams = (newParams: IPublicRegisterFilter) => {
    setFilterParams((prevState) => {
      return { ...prevState, ...newParams };
    });

    const letterValue = newParams.alphabetLetter;

    setSelectedAlphabet(letterValue);
  };

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        position: 'relative',
        height: { xs: 'auto', md: '100%' },
        width: '100%',
        overflowY: 'auto',
      }}
    >
      <Paper
        elevation={2}
        sx={{
          display: 'flex',
          flexDirection: 'column',
          height: 'auto',
          p: '1rem',
          m: { xs: '0.1rem', md: '0.1rem 1rem' },
        }}
      >
        <Stack
          flexDirection="row"
          sx={{
            p: '0.5rem 0rem',
            flexWrap: 'wrap',
          }}
        >
          <Typography
            component="h2"
            variant="h3"
            color="primary.main"
            tabIndex={0}
            sx={{
              alignSelf: 'center',
              paddingBottom: '0.5rem',
              paddingRight: '0.5rem',
            }}
          >
            {service?.name?.toUpperCase() ?? ''}
          </Typography>

          <PublicRegisterFilter
            filterParams={filterParams}
            updateFilterParams={updateFilterParams}
          />
        </Stack>

        <Stack
          flexDirection="row"
          sx={{
            p: '0.5rem 0rem',
            flexWrap: 'wrap',
            justifyContent: { xs: 'center', sm: 'normal' },
          }}
        >
          {alphabet.map((letter) => (
            <Button
              aria-describedby={letter.title}
              size="small"
              onClick={() => handleAlphabetFilterChange(letter.value)}
              sx={{
                textTransform: 'capitalize',
                marginRight: '0.2rem',
                marginBottom: '0.2rem',
              }}
              key={letter.title}
              {...(letter.value === selectedAlphabet && {
                variant: 'contained',
              })}
            >
              {letter.title}
            </Button>
          ))}
        </Stack>

        <PublicRegisterDataGrid filterParams={filterParams} />
      </Paper>
    </Box>
  );
};

export default PublicRegisterView;
