const compareValues = (
  value1: boolean | string | number | undefined | null,
  value2: boolean | string | number | undefined | null,
  operator: string | undefined | null
) => {
  if (
    value1 === null ||
    value1 === undefined ||
    value2 === null ||
    value2 === undefined
  )
    throw new Error('Invalid arguments are passed');

  if (operator === null || operator === undefined) {
    throw new Error(`Invalid operator ${operator} is passed`);
  }

  if (['>', '>=', '<', '<='].includes(operator)) {
    if (typeof value1 === 'boolean' || typeof value2 === 'boolean') {
      throw new Error(
        'Boolean values cannot be compared with numeric comparison operator'
      );
    }

    const numValue1 = Number(value1);
    const numValue2 = Number(value2);
    if (isNaN(numValue1) || isNaN(numValue2)) {
      throw new Error('One of the values is not a number');
    }

    value1 = numValue1;
    value2 = numValue2;
  }

  switch (operator) {
    case '==':
      return value1?.toString() == value2?.toString();
    case '!=':
      return value1?.toString() != value2?.toString();
    case '>':
      return value1 > value2;
    case '>=':
      return value1 >= value2;
    case '<':
      return value1 < value2;
    case '<=':
      return value1 <= value2;
    default:
      throw new Error(`Invalid operator ${operator} is passed`);
  }
};

export default compareValues;
