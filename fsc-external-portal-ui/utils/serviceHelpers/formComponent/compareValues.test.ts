import compareValues from './compareValues';

describe('compareValues', () => {
  it('throws an error if any arguments are null or undefined', () => {
    expect(() => compareValues(null, 2, '>')).toThrow(
      'Invalid arguments are passed'
    );
    expect(() => compareValues(1, undefined, '>')).toThrow(
      'Invalid arguments are passed'
    );
  });

  it('throws an error if operator is invalid or not passed', () => {
    expect(() => compareValues(true, false, '>')).toThrow(
      'Boolean values cannot be compared with numeric comparison operator'
    );

    expect(() => compareValues(1, 2, 'notARealOperator')).toThrow(
      `Invalid operator notARealOperator is passed`
    );
    expect(() => compareValues(1, 2, undefined)).toThrow(
      `Invalid operator undefined is passed`
    );
  });

  it('throws an error if either value is not a number when using numerical operators', () => {
    expect(() => compareValues(1, 'notANumber', '>')).toThrow(
      'One of the values is not a number'
    );
    expect(() => compareValues('alsoNotANumber', 3, '<=')).toThrow(
      'One of the values is not a number'
    );
  });

  it('compares values correctly for each operator', () => {
    expect(compareValues(2, 2, '==')).toBe(true);
    expect(compareValues('3', 3, '==')).toBe(true);
    expect(compareValues(1, 2, '!=')).toBe(true);
    expect(compareValues('1', 1, '!=')).toBe(false);
    expect(compareValues(5, 2, '>')).toBe(true);
    expect(compareValues(1.5, 1.5, '>')).toBe(false);
    expect(compareValues(3, 3, '<=')).toBe(true);
    expect(compareValues('4', 6, '<=')).toBe(true);
    expect(compareValues('10', 6, '>=')).toBe(true);
    expect(compareValues(5, 7, '>=')).toBe(false);
    expect(compareValues(true, 'true', '==')).toBe(true);
    expect(compareValues(true, 'false', '==')).toBe(false);
    expect(compareValues(true, true, '==')).toBe(true);
    expect(compareValues(true, false, '==')).toBe(false);
    expect(compareValues('text', 'text', '==')).toBe(true);
    expect(compareValues('text', 'text1', '!=')).toBe(true);
  });
});
