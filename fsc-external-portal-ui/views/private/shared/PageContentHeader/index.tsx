import Box, { BoxProps } from '@mui/material/Box';
import Typography, { TypographyProps } from '@mui/material/Typography';
import { useTranslation } from 'react-i18next';

const PageContentHeader = <C extends React.ElementType>({
  rootProps,
  typographyProps,
  children,
}: {
  rootProps?: BoxProps;
  typographyProps?: TypographyProps<C, { component?: C }>;
  children: string;
}) => {
  const { t } = useTranslation();
  return (
    <Box {...rootProps}>
      <Typography {...typographyProps}>{children}</Typography>
    </Box>
  );
};

export default PageContentHeader;
