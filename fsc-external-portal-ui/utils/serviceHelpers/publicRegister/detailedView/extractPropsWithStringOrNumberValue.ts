type ExtractPropsWithStringValueReturnType = Record<string, string | number>;

const extractPropsWithStringOrNumberValue = (
  data: Record<string, any>
): ExtractPropsWithStringValueReturnType => {
  let stringProps = {} as ExtractPropsWithStringValueReturnType;

  for (const [key, value] of Object.entries(data)) {
    if (
      typeof value === 'string' ||
      typeof value === 'number' ||
      value === null
    ) {
      stringProps[key] = value ?? '';
    }
  }

  return stringProps;
};

export default extractPropsWithStringOrNumberValue;
