import filterAndSortFields from './filterAndSortFields';

describe('filterAndSortFields', () => {
  it('should return an empty object when given an empty object', () => {
    expect(filterAndSortFields({}, [])).toEqual({});
  });

  it('should return an empty object when given fields that do not match included fields', () => {
    const fields = { field1: 'fieldValue1', field2: 30 };
    const includedFields = [{ label: 'field3', order: 1 }];

    expect(filterAndSortFields(fields, includedFields)).toEqual({});
  });

  it('should only include fields that match the included fields', () => {
    const fields = { field1: 'fieldValue1', field2: 30, field3: 'fieldValue2' };
    const includedFields = [{ label: 'field3', order: 1 }];

    expect(filterAndSortFields(fields, includedFields)).toEqual({
      field3: 'fieldValue2',
    });
  });

  it('should include fields in the same order as the included fields', () => {
    const fields = {
      field1: 'fieldValue1',
      field2: 30,
      field3: 'fieldValue2',
      field4: '1234567890',
    };
    const includedFields = [
      { label: 'field3', order: 2 },
      { label: 'field4', order: 1 },
    ];

    expect(filterAndSortFields(fields, includedFields)).toEqual({
      field4: '1234567890',
      field3: 'fieldValue2',
    });
  });

  it('should exclude undefined fields', () => {
    const fields = { field1: 'fieldValue1', field2: undefined as any };
    const includedFields = [
      { label: 'field1', order: 1 },
      { label: 'field2', order: 2 },
    ];

    expect(filterAndSortFields(fields, includedFields)).toEqual({
      field1: 'fieldValue1',
    });
  });
});
