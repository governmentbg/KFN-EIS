import Image from 'next/image';
import { Theme } from '@mui/material/styles';
import makeStyles from '@mui/styles/makeStyles';
import LogoImage from '../public/homeImages/logo.svg';
import AppLink from './AppLink';
import { ROUTES } from '../constants';

const useStyles = makeStyles((theme: Theme) => ({
  link: {
    display: 'inline-flex',
    color: theme.link.main,
    fontWeight: theme.typography.fontWeightMedium,
    '&:hover': {
      color: theme.link.hover,
    },
    '&:active': {
      color: theme.link.active,
    },
    '&:visited': {
      color: theme.link.visited,
    },
  },
  activeLink: {
    display: 'inline-flex',
    color: theme.link.active,
    fontWeight: theme.typography.fontWeightBold,
    textDecoration: 'underline',
    textUnderlineOffset: '.1rem',
  },
}));

const Logo = () => {
  const styles = useStyles();
  return (
    <AppLink
      href={ROUTES.HOME}
      className={styles.link}
      activeClassName={styles.activeLink}
    >
      <a href={ROUTES.HOME}>
        <Image
          src={LogoImage}
          alt="KFN Logo link"
          aria-label="Go to home page"
        />
      </a>
    </AppLink>
  );
};

export default Logo;
