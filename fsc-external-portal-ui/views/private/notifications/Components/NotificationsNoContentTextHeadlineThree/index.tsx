import Box, { BoxProps } from '@mui/material/Box';
import Typography, { TypographyProps } from '@mui/material/Typography';
import { useTranslation } from 'react-i18next';

const NotificationsNoContentTextHeadlineThree = ({
  rootProps,
  typographyProps,
}: {
  rootProps?: BoxProps;
  typographyProps?: TypographyProps;
}) => {
  const { t } = useTranslation(['common']);
  return (
    <Box {...rootProps}>
      <Typography paragraph={true} variant="h4" {...typographyProps}>
        {t('notifications.youWillBeNotifiedIfAny') + '.'}
      </Typography>
    </Box>
  );
};

export default NotificationsNoContentTextHeadlineThree;
