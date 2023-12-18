import getColumnDefinitionsBySectionId from './getColumnDefinitionsBySectionId';

describe('getColumnDefinitionsBySectionId function', () => {
  it('should returns array of strings when match all numbers from 10 to 320 no remainder from integer division by 10', () => {
    for (let i = 10; i <= 320; i = i + 10) {
      const result = getColumnDefinitionsBySectionId(i);
      expect(Array.isArray(result)).toBeTruthy();
    }
  });

  it('should returns an empty array if there is no match', () => {
    const result = getColumnDefinitionsBySectionId(999999999);
    expect(Array.isArray(result)).toBeTruthy();
    expect(result.length).toEqual(0);
  });
});
