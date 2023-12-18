const getFormFieldNameValuePairs = (
  autofill: { [key: string]: string },
  data: { [key: string]: string },
  nomenclatureId: number | string
): { [key: string]: string } => {
  const formFieldNameValuePairs: { [key: string]: string } = {};

  Object.entries(autofill)?.forEach(([fieldName, formFieldName]) => {
    formFieldNameValuePairs[formFieldName] =
      data?.id?.toString() === nomenclatureId?.toString() && data?.[fieldName]
        ? data[fieldName]
        : '';
  });

  return formFieldNameValuePairs;
};

export default getFormFieldNameValuePairs;
