import { BoxProps } from '@mui/material/Box';

export type GetColumnsStylesBySectionIdResponseType =
  | { columnName: string; props: BoxProps }[]
  | undefined;

const getColumnsStylesBySectionId = (
  sectionId: number
): GetColumnsStylesBySectionIdResponseType => {
  switch (sectionId) {
    case 10: {
      const commonProps = {
        sx: {
          gridRowStart: 2,
        },
      };

      return [
        {
          columnName: 'phone',
          props: commonProps,
        },
        {
          columnName: 'fax',
          props: commonProps,
        },
        {
          columnName: 'website',
          props: commonProps,
        },
        {
          columnName: 'email',
          props: commonProps,
        },
      ];
    }

    default:
      return;
  }
};

export default getColumnsStylesBySectionId;
