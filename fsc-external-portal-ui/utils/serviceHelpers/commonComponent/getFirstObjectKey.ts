const getFirstObjectKey = (
  object: { [key: string]: any },
  keys: string[] = []
): string => {
  const firstErrorKey = Object.keys(object ?? {})?.[0];
  if (
    object &&
    object.hasOwnProperty(firstErrorKey) &&
    object[firstErrorKey] &&
    typeof object?.[firstErrorKey] === 'object' &&
    !Array.isArray(object?.[firstErrorKey])
  ) {
    return getFirstObjectKey(object?.[firstErrorKey], [...keys, firstErrorKey]);
  }

  return [...keys, firstErrorKey].join('.');
};

export default getFirstObjectKey;
