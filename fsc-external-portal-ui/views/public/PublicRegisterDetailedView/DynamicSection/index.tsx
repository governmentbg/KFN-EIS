import { Fragment, useEffect } from 'react';
import { useFetchPublicRegisterSectionInfoQuery } from '../../../../store/api/publicRegisterPnlSlice';
import SectionFieldsView from './SectionFieldsView';
import SectionDataGridView from './SectionDataGridView';
import {
  SectionDataGridViewPropsType,
  SectionFieldsViewPropsType,
} from '../../../../contracts/types/publicRegisterPnl/details/dataGrid';
import extractPropsWithStringOrNumberValue from '../../../../utils/serviceHelpers/publicRegister/detailedView/extractPropsWithStringOrNumberValue';
import extractPropsWithArrayValue from '../../../../utils/serviceHelpers/publicRegister/detailedView/extractPropsWithArrayValue';
import SectionLoader from '../commonComponents/SectionLoader';
import getColumnDefinitionsBySectionId from '../../../../utils/serviceHelpers/publicRegister/detailedView/getColumnDefinitionsBySectionId';
import useGetSectionUrlBySectionId from '../../../../utils/serviceHelpers/publicRegister/detailedView/getSectionUrlBySectionId';
import DataGridHeader from './DataGridHeader';
import { useTranslation } from 'react-i18next';

type DynamicSectionPropsType = { sectionId: number };

const DynamicSection = ({ sectionId }: DynamicSectionPropsType) => {
  const { t } = useTranslation();
  const getSectionUrlBySectionId = useGetSectionUrlBySectionId();
  const sectionUrlName = getSectionUrlBySectionId(sectionId) ?? '';

  const { data, isLoading, refetch, isError } =
    useFetchPublicRegisterSectionInfoQuery(
      {
        sectionUrlName,
      },
      { skip: !!!sectionUrlName }
    );

  useEffect(() => {
    refetch();
  }, [refetch]);

  if (isLoading) return <SectionLoader />;

  if (isError) return <>{t('errorDuringDataFetching', { ns: 'common' })}</>;

  const sectionDataGridViewProps: Omit<
    SectionDataGridViewPropsType,
    'rows' | 'tableName'
  > = {
    sectionId,
    columnDefinitions: getColumnDefinitionsBySectionId(sectionId),
  };

  const sectionFieldsViewProps: Omit<SectionFieldsViewPropsType, 'fields'> = {
    sectionId,
    fieldDefinitions: getColumnDefinitionsBySectionId(sectionId),
  };

  const hasAnyData = () => {
    if (!data) return false;

    if (Array.isArray(data) && data.length > 0) return true;

    const stringProps = extractPropsWithStringOrNumberValue(data);

    if (stringProps && Object.keys(stringProps)?.length > 0) return true;

    if (
      Object.entries(extractPropsWithArrayValue(data)).some(
        ([_, arrayData]) => arrayData?.length > 0
      )
    )
      return true;

    return false;
  };

  if (!data || !hasAnyData()) return <>{t('noData', { ns: 'common' })}</>;

  return (
    <>
      {data && !Array.isArray(data) && (
        <SectionFieldsView
          fields={extractPropsWithStringOrNumberValue(data)}
          {...sectionFieldsViewProps}
        />
      )}

      {Array.isArray(data) && (
        <SectionDataGridView rows={data} {...sectionDataGridViewProps} />
      )}

      {!Array.isArray(data) &&
        Object.entries(extractPropsWithArrayValue(data)).map(
          ([tableName, arrayData]: [string, Array<any>]) => {
            return (
              <Fragment key={tableName}>
                {Array.isArray(arrayData) && arrayData.length > 0 && (
                  <DataGridHeader sectionId={sectionId} tableName={tableName} />
                )}

                <SectionDataGridView
                  key={tableName}
                  rows={arrayData}
                  tableName={tableName}
                  {...sectionDataGridViewProps}
                />
              </Fragment>
            );
          }
        )}
    </>
  );
};

export default DynamicSection;
