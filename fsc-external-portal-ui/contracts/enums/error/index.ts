export enum ERROR_SEVERITY {
  /**
   * Error severity is used when something is happened but the application still can continue working
   */
  ERROR = 'ERROR',
  /**
   * Success severity is used to tell the user that any running operation has completed successfully
   */
  SUCCESS = 'SUCCESS',
  /**
   * Warning severity is used to tell the user that should be aware of something
   */
  WARNING = 'WARNING',
  /**
   * Info severity is used to inform the user for something
   */
  INFO = 'INFO',
}
