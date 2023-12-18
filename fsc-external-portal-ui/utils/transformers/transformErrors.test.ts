import transformErrors from './transformErrors';

describe('transformErrors function', () => {
  const stringArgument = 'fake string';
  const arrayOfStringsArgument = [stringArgument, stringArgument];

  it('should returns null if the argument is undefined', () => {
    expect(transformErrors(undefined)).toEqual(null);
  });

  it('should returns string', () => {
    expect(transformErrors(stringArgument)).toEqual(stringArgument);
  });

  it('should returns string', () => {
    expect(transformErrors(arrayOfStringsArgument)).toEqual(
      arrayOfStringsArgument.join('\n')
    );
  });
});
