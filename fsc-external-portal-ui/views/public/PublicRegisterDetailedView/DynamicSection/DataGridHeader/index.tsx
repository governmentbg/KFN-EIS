import Typography from '@mui/material/Typography';
import { useMemo } from 'react';
import { useTranslation } from 'react-i18next';

type DataGridHeaderPropsType = {
  sectionId: number;
  tableName?: string;
};

const DataGridHeader = ({ sectionId, tableName }: DataGridHeaderPropsType) => {
  const { t } = useTranslation('publicRegisterDetailedView');
  const header = useMemo(
    () =>
      t(
        `publicRegister.detailedView.section.${sectionId}.${tableName}.table.header.label`
      ),
    [sectionId, t, tableName]
  );

  if (
    header !==
    `publicRegister.detailedView.section.${sectionId}.${tableName}.table.header.label`
  ) {
    return (
      <Typography variant="h3" component="span" mt={4}>
        {header}
      </Typography>
    );
  } else {
    throw new Error(`Translation: "${header}" is not defined`);
  }
};

export default DataGridHeader;
