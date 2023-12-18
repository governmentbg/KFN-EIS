import filterColumnsDefinitionByTableName from './filterColumnsDefinitionByTableName';

describe('filterColumnsDefinitionByTableName', () => {
  it('should filter columns by tableName when provided', () => {
    const columnsDefinition = [
      { tableName: 'Table1', label: 'Column 1', order: 1 },
      { tableName: 'Table2', label: 'Column 2', order: 2 },
      { tableName: 'Table1', label: 'Column 3', order: 3 },
      { tableName: 'Table3', label: 'Column 4', order: 4 },
      { tableName: undefined, label: 'Column 5', order: 5 },
    ];

    const tableName = 'Table1';
    const filteredColumns = filterColumnsDefinitionByTableName(
      columnsDefinition,
      tableName
    );

    // Assert that only columns with matching tableName or undefined tableName are returned
    expect(filteredColumns).toEqual([
      { tableName: 'Table1', label: 'Column 1', order: 1 },
      { tableName: 'Table1', label: 'Column 3', order: 3 },
      { tableName: undefined, label: 'Column 5', order: 5 },
    ]);
  });

  it('should return all columns when tableName is not provided', () => {
    const columnsDefinition = [
      { tableName: 'Table1', label: 'Column 1', order: 1 },
      { tableName: 'Table2', label: 'Column 2', order: 2 },
      { tableName: 'Table1', label: 'Column 3', order: 3 },
      { tableName: 'Table3', label: 'Column 4', order: 4 },
      { tableName: undefined, label: 'Column 5', order: 5 },
    ];

    const filteredColumns =
      filterColumnsDefinitionByTableName(columnsDefinition);

    // Assert that all columns are returned when tableName is not provided
    expect(filteredColumns).toEqual(columnsDefinition);
  });
});
