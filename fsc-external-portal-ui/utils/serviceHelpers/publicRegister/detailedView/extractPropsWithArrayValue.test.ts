import extractPropsWithArrayValue from './extractPropsWithArrayValue';

describe('extractPropsWithArrayValue', () => {
  it('should return an object with only array properties', () => {
    const input = {
      prop1: ['A', 'B'],
      prop2: 1,
      prop3: ['C', 'D'],
      prop4: null,
    };

    const output = extractPropsWithArrayValue(input);

    expect(output.prop1).toEqual(input.prop1);
    expect(output.prop3).toEqual(input.prop3);
    expect(output.prop2).toBeUndefined();
    expect(output.prop4).toBeUndefined();
  });

  it('should return an empty object if the input does not have any array properties', () => {
    expect(extractPropsWithArrayValue({ prop1: 'A', prop2: 1 })).toEqual({});
  });
});
