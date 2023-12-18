import Button from '@mui/material/Button';
import { useTranslation } from 'react-i18next';

type OptionLoginEntryLinkPropsType = {
  title?: string;
  href?: string;
};

const OptionLoginEntryLink = ({
  title,
  href,
}: OptionLoginEntryLinkPropsType) => {
  const { t } = useTranslation();
  return (
    <Button
      {...(href && { href })}
      variant="contained"
      size="small"
      sx={{ width: 'fit-content' }}
    >
      {title ?? t('login', { ns: 'common' })}
    </Button>
  );
};

export default OptionLoginEntryLink;
