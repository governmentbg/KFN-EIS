import Box, { BoxProps } from '@mui/material/Box';
import Typography, { TypographyProps } from '@mui/material/Typography';
import { useTranslation } from 'react-i18next';

const NotificationsNoContentTextHeadlineTwo = ({
  rootProps,
  typographyProps,
}: {
  rootProps?: BoxProps;
  typographyProps?: TypographyProps;
}) => {
  const { t } = useTranslation(['common']);
  return (
    <Box {...rootProps}>
      <Typography paragraph={true} variant="h3" {...typographyProps}>
        {t('notifications.youHaveNoNotificationsYet') + '.'}
      </Typography>
    </Box>
  );
};

export default NotificationsNoContentTextHeadlineTwo;
