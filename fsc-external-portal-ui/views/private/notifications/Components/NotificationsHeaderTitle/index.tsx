import { useTranslation } from 'react-i18next';
import Box, { BoxProps } from '@mui/material/Box';
import Typography, { TypographyProps } from '@mui/material/Typography';

const NotificationsHeaderTitle = <C extends React.ElementType>({
  rootProps,
  typographyProps,
}: {
  rootProps?: BoxProps;
  typographyProps?: TypographyProps<C, { component?: C }>;
}) => {
  const { t } = useTranslation(['common']);
  return (
    <Box {...rootProps}>
      <Typography component="h1" variant="h2" {...typographyProps}>
        {t('notifications')}
      </Typography>
    </Box>
  );
};

export default NotificationsHeaderTitle;
