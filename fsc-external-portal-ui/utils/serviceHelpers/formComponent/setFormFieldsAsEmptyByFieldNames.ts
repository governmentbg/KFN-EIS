const setFormFieldsAsEmptyByFieldNames = (
  fieldNames: string[],
  setFieldValue: (field: string, value: any, shouldValidate?: boolean | undefined) => void
) => {
  fieldNames?.forEach(
    (fieldName?: string) => fieldName && setFieldValue(fieldName, '')
  );
};

export default setFormFieldsAsEmptyByFieldNames;
