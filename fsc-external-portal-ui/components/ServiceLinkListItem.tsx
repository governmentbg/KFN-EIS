import { Theme } from '@mui/material/styles';
import ListItem from '@mui/material/ListItem';
import makeStyles from '@mui/styles/makeStyles';
import { IService } from '../contracts/interfaces/services';
import AppLink from './AppLink';
import getServiceNextLevelChildren from '../utils/serviceHelpers/getServiceNextLevelChildren';

const useStyles = makeStyles((theme: Theme) => ({
  dropdownLink: {
    color: theme.dropdown.textColor,
  },
  dropdownLinkActive: {
    fontWeight: theme.typography.fontWeightBold,
    color: theme.dropdown.textColorActive,
    textDecoration: 'underline',
    textUnderlineOffset: '.2rem',
  },
}));

const ServiceLinkListItem = ({
  href,
  services,
  currentService,
  active,
  setActive = () => {},
  setChildrenServices = () => {},
}: {
  href: string;
  services: Array<IService>;
  currentService: IService;
  active?: boolean;
  setActive?: Function;
  setChildrenServices?: Function;
}) => {
  const styles = useStyles();

  const onMouseOverHandler = (): void => {
    if (setChildrenServices)
      setChildrenServices(
        getServiceNextLevelChildren(services, currentService)
      );
    if (setActive) setActive(currentService.catalogElement.id);
  };

  return (
    <ListItem sx={{ width: 'auto' }}>
      <AppLink
        active={active}
        href={href}
        className={styles.dropdownLink}
        activeClassName={styles.dropdownLinkActive}
      >
        <a
          href={href}
          onMouseOver={onMouseOverHandler}
          onFocus={onMouseOverHandler}
        >
          {currentService.name}
        </a>
      </AppLink>
    </ListItem>
  );
};

export default ServiceLinkListItem;
