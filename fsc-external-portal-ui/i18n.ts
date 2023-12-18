import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';
//EN
import commonEN from './locales/en/common.json';
import notificationsEN from './locales/en/notifications.json';
import errorsEN from './locales/en/errors.json';
import dateFormatsEN from './locales/en/dateFormats.json';
import navigationEN from './locales/en/navigation.json';
import formFieldsEN from './locales/en/formFields.json';
import messagesEN from './locales/en/messages.json';
import servicesEN from './locales/en/services.json';
import userContextSelectEN from './locales/en/userContextSelect.json';
import dossierEN from './locales/en/dossier.json';
import userProfileEN from './locales/en/userProfile.json';
import publicRegisterDetailedViewEN from './locales/en/publicRegisterDetailedView.json';
import authEN from './locales/en/auth.json';

//BG
import commonBG from './locales/bg/common.json';
import notificationsBG from './locales/bg/notifications.json';
import errorsBG from './locales/bg/errors.json';
import dateFormatsBG from './locales/bg/dateFormats.json';
import navigationBG from './locales/bg/navigation.json';
import formFieldsBG from './locales/bg/formFields.json';
import messagesBG from './locales/bg/messages.json';
import servicesBG from './locales/bg/services.json';
import userContextSelectBG from './locales/bg/userContextSelect.json';
import dossierBG from './locales/bg/dossier.json';
import userProfileBG from './locales/bg/userProfile.json';
import publicRegisterDetailedViewBG from './locales/bg/publicRegisterDetailedView.json';
import authBG from './locales/bg/auth.json';

export const defaultNS = 'common';
export const resources = {
  en: {
    auth: authEN,
    common: commonEN,
    errors: errorsEN,
    notifications: notificationsEN,
    dateFormats: dateFormatsEN,
    navigation: navigationEN,
    formFields: formFieldsEN,
    messages: messagesEN,
    services: servicesEN,
    userContextSelect: userContextSelectEN,
    dossier: dossierEN,
    userProfile: userProfileEN,
    publicRegisterDetailedView: publicRegisterDetailedViewEN,
  },
  bg: {
    auth: authBG,
    common: commonBG,
    errors: errorsBG,
    notifications: notificationsBG,
    dateFormats: dateFormatsBG,
    navigation: navigationBG,
    formFields: formFieldsBG,
    messages: messagesBG,
    services: servicesBG,
    userContextSelect: userContextSelectBG,
    dossier: dossierBG,
    userProfile: userProfileBG,
    publicRegisterDetailedView: publicRegisterDetailedViewBG,
  },
} as const;

i18n
  .use(initReactI18next)
  .use(LanguageDetector)
  .init({
    lng: 'bg',
    ns: [
      'common',
      'errors',
      'notifications',
      'dateFormats',
      'navigation',
      'formFields',
      'messages',
      'services',
      'userContextSelect',
      'dossier',
      'auth',
    ],
    defaultNS,
    resources,
  });

export const t = i18n.t;

export default i18n;
