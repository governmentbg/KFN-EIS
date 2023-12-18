import { Application, applicationSelector } from '../../../store/application';
import { useAppSelector } from '../redux';
import * as dateFnsLocales from 'date-fns/locale';
import { useEffect, useState } from 'react';
import { Locale } from 'date-fns';
import { Locales } from '../../../contracts/enums/locales';

export const useDateFnsLocale = (): [
  dateFnsLocale: Locale,
  locale: Locales
] => {
  const { locale } = useAppSelector<Application>(applicationSelector);
  const [dateFnsLocale, updateLocaleDateFormat] = useState<Locale>(
    dateFnsLocales[locale]
  );
  useEffect(() => {
    updateLocaleDateFormat(dateFnsLocales[locale]);
  }, [locale]);
  return [dateFnsLocale, locale];
};
