import extractPropsWithStringOrNumberValue from './extractPropsWithStringOrNumberValue';

describe('extractPropsWithStringOrNumberValue', () => {
  it('should return an object with only string and number properties', () => {
    const input = {
      stringProp: 'A prop1',
      numberProp: 42,
      booleanProp: true,
      anotherStringProp: 'foo',
      undefinedProp: undefined,
      emptyProp: null,
    };

    const output = extractPropsWithStringOrNumberValue(input);

    expect(output.stringProp).toEqual(input.stringProp);
    expect(output.numberProp).toEqual(input.numberProp);
    expect(output.booleanProp).toBe(undefined);
    expect(output.anotherStringProp).toEqual(input.anotherStringProp);
    expect(output.undefinedProp).toBe(undefined);
    expect(output.emptyProp).toBe('');
  });

  it('should return an empty object if the input is empty', () => {
    expect(extractPropsWithStringOrNumberValue({})).toEqual({});
  });
});
