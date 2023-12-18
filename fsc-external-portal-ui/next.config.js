/* eslint-disable linebreak-style */
const nextConfig = {
  reactStrictMode: true,
  tsconfigPath: './tsconfig.json',
  i18n: {
    localeDetection: true,
    // These are all the locales you want to support in
    // your application
    locales: ['bg-BG'],
    // This is the default locale you want to be used when visiting
    // a non-locale prefixed path e.g. `/hello`
    defaultLocale: 'bg-BG',
    // This is a list of locale domains and the default locale they
    // should handle (these are only required when setting up domain routing)
    // Note: subdomains must be included in the domain value to be matched e.g. "fr.example.com".
    // domains: [
    //   {
    //     domain: 'localhost:3000',
    //     defaultLocale: 'en-US',
    //     http: true,
    //   },
    //   {
    //     domain: 'localhost:3000',
    //     defaultLocale: 'bg-BG',
    //     // an optional http field can also be used to test
    //     // locale domains locally with http instead of https
    //     http: true,
    //   },
    // ],
  },
  trailingSlash: true,
};

module.exports = nextConfig;
