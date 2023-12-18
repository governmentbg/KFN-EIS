type ExtractPropsWithArrayValueReturnType = Record<
  string,
  Record<string, string | number>[]
>;

const extractPropsWithArrayValue = (
  data: Record<string, any>
): ExtractPropsWithArrayValueReturnType => {
  let arrayProps = {} as ExtractPropsWithArrayValueReturnType;

  for (const [key, value] of Object.entries(data)) {
    if (Array.isArray(value)) {
      arrayProps[key] = value;
    }
  }

  return arrayProps;
};

export default extractPropsWithArrayValue;
