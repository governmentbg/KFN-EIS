export const ROUTES = {
  AUTH: {
    INDEX: '/auth',
    LOGIN: {
      INDEX: '/login',
      OPTIONS: '/auth/login/options',
      USER_PASS: '/auth/login/username-password',
      KEP: '/auth/login/kep',
      E_AUTH: '/auth/login/e-auth',
    },
  },
  UNAUTHORIZED: '/403',
  HOME: '/',
  NOT_FOUND: '/404',
  SERVER_ERROR: '/500',
  NOTIFICATIONS: '/notifications',
  SERVICES: {
    FORM: '/service',
    PUBLIC_REGISTER: '/public-register',
    PENSION_SHARE: '/pension-share',
  },
  USER: {
    PROFILE: '/profile',
    DOSSIER: '/dossier',
    GENERAL_INFO: '/dossier/general-info',
    SERVICES: '/dossier/services',
    SERVICES_DETAILED_INFO: '/dossier/services/detailed-view/',
    REPORTS: '/dossier/reports',
    REPORTS_DETAILED_INFO: '/dossier/reports/detailed-view/',
    PENAL_DECREES: '/dossier/penal-decrees',
    ACCOUNT: '/dossier/account',
    ACCOUNT_DETAILED_INFO: '/dossier/account/detailed-view/',
    EMPLOYEES: '/dossier/employees',
  },
  OBLIGATIONS: {
    PAYMENT: '/charges/payment',
  },
};
