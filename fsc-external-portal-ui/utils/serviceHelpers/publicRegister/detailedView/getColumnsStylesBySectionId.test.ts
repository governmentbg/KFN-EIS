import getColumnsStylesBySectionId from './getColumnsStylesBySectionId';

describe('getColumnsStylesBySectionId', () => {
  it('should return columns with correct props for sectionId of 10', () => {
    const result = getColumnsStylesBySectionId(10);

    if (result !== undefined) {
      expect(Array.isArray(result)).toEqual(true);

      expect(result[0].hasOwnProperty('columnName')).toEqual(true);

      expect(result[0].hasOwnProperty('props')).toEqual(true);
    }
  });

  it('should return undefined for sectionId other than 10', () => {
    expect(getColumnsStylesBySectionId(9)).toBeUndefined();

    expect(getColumnsStylesBySectionId(11)).toBeUndefined();
  });
});
