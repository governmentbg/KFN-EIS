const checkFieldValueType = (
  fieldName: string,
  fieldValue: any,
  expectedFieldValueType: string
) => {
  if (typeof fieldValue !== expectedFieldValueType) {
    throw new Error(
      `The field "${fieldName}" has invalid value type.\
       The value must be of type ${expectedFieldValueType},\
        but "${typeof fieldValue}" given instead`
    );
  }
};

export default checkFieldValueType;
