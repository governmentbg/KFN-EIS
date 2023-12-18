import Image, { ImageProps } from 'next/image';
import { useTranslation } from 'react-i18next';
import Box, { BoxProps } from '@mui/material/Box';
import noNotificationsImage from '../../../../../public/notificationsImages/noNotificationsImage.jpg';
import useIsMounted from '../../../../../app/hooks/shared/useIsMounted';

const NotificationsImage = ({
  rootProps,
  imageProps,
}: {
  rootProps?: BoxProps;
  imageProps?: ImageProps;
}) => {
  const { t } = useTranslation(['common']);
  const { isMounted } = useIsMounted();

  if (!isMounted) return null;
  return (
    <Box {...rootProps}>
      <Image
        src={noNotificationsImage}
        alt={`${
          t('notifications.youHaveNoNotificationsYet') + '. ' + t('image')
        }`}
        aria-label={`${
          t('notifications.youHaveNoNotificationsYet') + '. ' + t('image')
        }`}
        layout="responsive"
        width="100%"
        height="100%"
        quality={100}
        {...imageProps}
      />
    </Box>
  );
};

export default NotificationsImage;
