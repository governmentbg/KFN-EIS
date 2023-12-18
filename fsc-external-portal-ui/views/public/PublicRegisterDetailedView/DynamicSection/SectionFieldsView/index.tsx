import Box from '@mui/material/Box';
import { useTranslation } from 'react-i18next';
import DataItem from '../../../../../components/DataItem';
import { SectionFieldsViewPropsType } from '../../../../../contracts/types/publicRegisterPnl/details/dataGrid';
import filterAndSortFields from '../../../../../utils/serviceHelpers/publicRegister/detailedView/filterAndSortFields';
import getColumnsStylesBySectionId from '../../../../../utils/serviceHelpers/publicRegister/detailedView/getColumnsStylesBySectionId';
import getComputedValueOfSectionField from '../../../../../utils/serviceHelpers/publicRegister/detailedView/getComputedValueOfSectionField';
import GridContainer from '../../commonComponents/GridContainer';

const SectionFieldsView = ({
  sectionId,
  fields,
  fieldDefinitions = [],
}: SectionFieldsViewPropsType) => {
  const { t } = useTranslation('publicRegisterDetailedView');

  return (
    <Box width="100%">
      <GridContainer>
        {Object.keys(filterAndSortFields(fields, fieldDefinitions)).map(
          (fieldName: string) => (
            <DataItem
              key={fieldName}
              title={t(
                `publicRegister.detailedView.section.${sectionId}.${fieldName}.label`
              )}
              value={getComputedValueOfSectionField(fields[fieldName])}
              rootProps={
                getColumnsStylesBySectionId(sectionId)?.find(
                  ({ columnName }) => columnName === fieldName
                )?.props ?? {}
              }
            />
          )
        )}
      </GridContainer>
    </Box>
  );
};

export default SectionFieldsView;
