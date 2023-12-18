const transformErrors = (errors: string | string[] | undefined) => {
  if (!errors) return null;

  return Array.isArray(errors) ? errors.join('\n') : errors;
};

export default transformErrors;
