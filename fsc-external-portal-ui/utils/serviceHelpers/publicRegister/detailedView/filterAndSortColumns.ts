import { PublicRegisterDetailsSectionColumnDefinition } from '../../../../contracts/types/publicRegisterPnl/details/dataGrid';
import filterColumnsDefinitionByTableName from './filterColumnsDefinitionByTableName';

const filterAndSortColumns = (
  rows: Record<string, string | number>[],
  columnsDefinition: PublicRegisterDetailsSectionColumnDefinition[],
  tableName?: string
) => {
  const columnsDefinitionFilteredByTableName =
    filterColumnsDefinitionByTableName(columnsDefinition, tableName);

  const sortedColumnsDefinitions = columnsDefinitionFilteredByTableName.sort(
    (a, b) => (a.order > b.order ? 1 : -1)
  );

  const filteredData = rows.map((row) => {
    const filteredRow = {} as Record<string, string | number>;

    sortedColumnsDefinitions.forEach(
      ({ label }) =>
        row[label] !== undefined && (filteredRow[label] = row[label])
    );

    return filteredRow;
  });

  return filteredData;
};

export default filterAndSortColumns;
