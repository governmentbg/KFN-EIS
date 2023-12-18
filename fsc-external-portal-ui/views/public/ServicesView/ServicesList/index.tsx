import Box from '@mui/material/Box';
import React, { useEffect, useState } from 'react';
import {
  IService,
  IServiceCard,
} from '../../../../contracts/interfaces/services';
import ServiceItem from './ServiceItem';
import { useTheme } from '@mui/material/styles';
import useMediaQuery from '@mui/material/useMediaQuery';
import getServiceChildrenIds from '../../../../utils/serviceHelpers/getServiceChildrenIds';
import { useFetchServicesQuery } from '../../../../store/api/servicesSlice';

const ServicesList = () => {
  const { data: services = [] } = useFetchServicesQuery(undefined);
  const [data, setData] = useState<Array<IServiceCard>>([]);
  const [gridTemplateRows, setGridTemplateRows] = useState<string>('');
  const theme = useTheme();
  const sm = useMediaQuery(theme.breakpoints.down('md'));

  useEffect(() => {
    if (services && services.length > 0) {
      setData(
        services
          .filter((dataItem: IService) => dataItem.catalogElement.level === 1)
          .map((dataItem: IService) => {
            const children: Array<number> = getServiceChildrenIds(dataItem);

            const content: Array<{ id: number; title: string }> = services
              .filter(
                (dataItem: IService) =>
                  dataItem.catalogElement.level === 2 &&
                  children.includes(dataItem.catalogElement.id)
              )
              .map((dataItem: IService) => ({
                id: dataItem.catalogElement.id,
                title: dataItem.name,
              }));

            return {
              header: dataItem.name,
              content: content,
              key: dataItem.catalogElement.id,
            };
          })
      );
    }
  }, [services]);

  const getTemplateRows = (dataLength: number): string => {
    let templateRows = '';
    for (let i = 0; i < dataLength; i++) {
      templateRows += ' 1fr';
    }
    return templateRows;
  };

  useEffect(() => {
    let templateRows = '';
    if (sm) {
      templateRows = getTemplateRows(data.length);
    } else {
      const rowsLength =
        data.length % 2 > 0 ? (data.length % 2) + 1 : data.length % 2;

      templateRows = getTemplateRows(rowsLength);
    }

    setGridTemplateRows(templateRows);
  }, [data, sm]);

  return (
    <Box
      sx={{
        minHeight: '100%',
        minWidth: '50%',
        maxWidth: { xs: '90%', sm: '80%', lg: '60%' },
        display: 'grid',
        gridTemplateColumns: { xs: '1fr', md: '1fr 1fr' },
        gridTemplateRows: gridTemplateRows,
      }}
    >
      {data.map((dataItem: IServiceCard) => (
        <React.Fragment key={dataItem.key}>
          <ServiceItem header={dataItem.header} content={dataItem.content} />
        </React.Fragment>
      ))}
    </Box>
  );
};
export default ServicesList;
