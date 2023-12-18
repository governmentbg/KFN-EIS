import filterAndSortColumns from './filterAndSortColumns';
import filterColumnsDefinitionByTableName from './filterColumnsDefinitionByTableName';

describe('filterAndSortColumns', () => {
  it('should filter and sort rows based on columns definition and table name', () => {
    // Mock data
    const rows = [
      { id: 1, name: 'user1', age: 30 },
      { id: 2, name: 'user2', age: 25 },
      { id: 3, name: 'user3', age: 35 },
    ];

    const columnsDefinition = [
      { tableName: 'users', label: 'id', order: 1 },
      { tableName: 'users', label: 'name', order: 2 },
      { tableName: 'users', label: 'age', order: 3 },
    ];

    const tableName = 'users';

    // Mock the filtered columns definition for the table name
    const filteredColumnsDefinition = [
      { tableName: 'users', label: 'id', order: 1 },
      { tableName: 'users', label: 'name', order: 2 },
      { tableName: 'users', label: 'age', order: 3 },
    ];

    // Mock the filterColumnsDefinitionByTableName function
    jest.mock('./filterColumnsDefinitionByTableName', () => ({
      __esModule: true,
      default: jest.fn(() => filteredColumnsDefinition),
    }));

    const mockedFilterColumnsDefinitionByTableName =
      filterColumnsDefinitionByTableName as jest.MockedFunction<
        typeof filterColumnsDefinitionByTableName
      >;

    // Call the function to be tested
    const result = filterAndSortColumns(rows, columnsDefinition, tableName);

    // Expected filtered and sorted rows based on the filtered columns definition
    const expected = [
      { id: 1, name: 'user1', age: 30 },
      { id: 2, name: 'user2', age: 25 },
      { id: 3, name: 'user3', age: 35 },
    ];

    expect(result).toEqual(expected);
  });
});
