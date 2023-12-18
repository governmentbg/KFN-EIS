import { PublicRegisterDetailsSectionColumnDefinition } from '../../../../contracts/types/publicRegisterPnl/details/dataGrid';

const filterColumnsDefinitionByTableName = (
  columnsDefinition: PublicRegisterDetailsSectionColumnDefinition[],
  tableName?: string
) =>
  tableName
    ? columnsDefinition.filter(
        (column) => {

          return column.tableName === tableName || !column.tableName
        }
      )
    : columnsDefinition;

export default filterColumnsDefinitionByTableName;
