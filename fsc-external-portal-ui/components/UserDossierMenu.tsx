import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import Collapse from '@mui/material/Collapse';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import Typography from '@mui/material/Typography';
import { Box } from '@mui/system';
import { Theme } from '@mui/material/styles';
import makeStyles from '@mui/styles/makeStyles';
import clsx from 'clsx';
import { useRouter } from 'next/router';
import { Fragment, useCallback, useEffect, useMemo, useState } from 'react';
import { useTranslation } from 'react-i18next';
import { ROUTES } from '../constants';
import { IDossierMenuItem } from '../contracts/interfaces/dossier/dossierMenuItem';
import { IUser, userSelector } from '../store/user';
import { useAppSelector } from '../app/hooks';
import { IPersonStoreState, personSelector } from '../store/person';
import { IPnlStoreState, pnlSelector } from '../store/pnl';
import { IUserContext } from '../contracts/interfaces/userContext';
import { UserContext_Types } from '../contracts/enums/userContext';

const useStyles = makeStyles((theme: Theme) => ({
  '&.Mui-selected': {
    backgroundColor: `${theme.sidebar.level.any.backgroundColorSelected} !important`,
    color: theme.sidebar.level.any.textColorSelected,
    '& .MuiListItemText-primary': { color: 'white' },
    '& .MuiListItemText-secondary span': { color: 'white' },
  },
  1: {
    backgroundColor: theme.sidebar.level[1].backgroundColor,
    color: theme.sidebar.level[1].textColor,
    borderBottom: '0.1px solid ' + theme.sidebar.level.any.borderColor,
    paddingRight: '0',
    '&:hover': {
      backgroundColor: theme.sidebar.level.any.backgroundColorHovered,
    },
  },
  2: {
    backgroundColor: theme.sidebar.level[2].backgroundColor,
    color: theme.sidebar.level[2].textColor,
    paddingRight: '0',
    '&:hover': {
      backgroundColor: theme.sidebar.level.any.backgroundColorHovered,
    },
  },
  3: {
    backgroundColor: theme.sidebar.level[3].backgroundColor,
    color: theme.sidebar.level[3].textColor,
    paddingRight: '0',
    '&:hover': {
      backgroundColor: theme.sidebar.level.any.backgroundColorHovered,
    },
  },
}));

const UserDossierMenu = () => {
  const [expandedItems, setExpandedItems] = useState<string[]>([]);
  const [selected, setSelected] = useState('');
  const [selectedParents, setSelectedParents] = useState<string[]>([]);
  const pnlScreens = ['generalInfo', 'reports'];
  const { t } = useTranslation(['dossier']);
  const styles = useStyles();
  const router = useRouter();
  const { userContexts } = useAppSelector<IUser>(userSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);

  const handleClick = (event: React.MouseEvent<HTMLElement>, name: string) => {
    event.preventDefault();
    if (!expandedItems.includes(name)) {
      setExpandedItems([...expandedItems, name]);
    } else {
      setExpandedItems(
        expandedItems.filter((element: string) => element !== name)
      );
    }
  };

  // TODO account, penalDecrees and employees are temporary not availabale
  // When logic for this screens are implemented comments will be removed
  const dossierMenu: IDossierMenuItem[] = useMemo(
    () => [
      {
        name: 'profile',
        title: t('menu.profile.title'),
        path: ROUTES.USER.PROFILE,
        level: 1,
        children: [],
        parents: [],
      },
      {
        name: 'dossier',
        title: t('menu.dossier.title'),
        path: null,
        level: 1,
        children: [
          {
            name: 'generalInfo',
            title: t('menu.generalInfo.title'),
            path: ROUTES.USER.GENERAL_INFO,
            level: 2,
            children: [],
            parents: ['dossier'],
          },
          {
            name: 'services',
            title: t('menu.services.title'),
            path: ROUTES.USER.SERVICES,
            level: 2,
            children: [],
            parents: ['dossier'],
          },
          {
            name: 'reports',
            title: t('menu.reports.title'),
            path: ROUTES.USER.REPORTS,
            level: 2,
            children: [],
            parents: ['dossier'],
          },
          // {
          //   name: 'penalDecrees',
          //   title: t('menu.penalDecrees.title'),
          //   path: ROUTES.USER.PENAL_DECREES,
          //   level: 2,
          //   children: [],
          //   parents: ['dossier'],
          // },
          {
            name: 'account',
            title: t('menu.account.title'),
            path: null,
            level: 2,
            children: [
              {
                name: 'obligations',
                title: t('menu.obligations.title'),
                path: ROUTES.USER.ACCOUNT,
                level: 3,
                children: [],
                parents: ['dossier', 'account'],
              },
            ],
            parents: ['dossier'],
          },
          // {
          //   name: 'employees',
          //   title: t('menu.employees.title'),
          //   path: ROUTES.USER.EMPLOYEES,
          //   level: 2,
          //   children: [],
          //   parents: ['dossier'],
          // },
        ],
        parents: [],
      },
    ],
    [t]
  );

  const setInitialUserMenuState = useCallback(
    (dossierList: IDossierMenuItem[]) => {
      if (
        selected.startsWith(ROUTES.USER.SERVICES_DETAILED_INFO, 0) ||
        selected.startsWith(ROUTES.USER.REPORTS_DETAILED_INFO, 0)
      ) {
        setExpandedItems(['dossier']);
        return;
      }

      if (selected.startsWith(ROUTES.USER.ACCOUNT_DETAILED_INFO, 0)) {
        setExpandedItems(['dossier', 'account']);
        return;
      }

      for (let menuItem of dossierList) {
        if (menuItem.path === selected && menuItem.parents.length > 0) {
          setSelectedParents(menuItem.parents);
          setExpandedItems(menuItem.parents);
          return;
        } else if (menuItem.path !== selected && menuItem.children.length > 0) {
          setInitialUserMenuState(menuItem.children);
        }
      }
    },
    [selected]
  );

  const dossierContextElement = () => {
    const dossierContext =
      userContexts.find(
        (userContext: IUserContext) =>
          userContext.personId === person.id && userContext.pnlId === pnl.id
      ) ?? null;

    return (
      <>
        {dossierContext?.userContextType !== UserContext_Types.ME && (
          <Typography
            component="span"
            sx={{ display: 'block', color: '#888', fontSize: '0.8rem' }}
          >
            {t(`menu.dossier.context.${dossierContext?.userContextType}`)}
          </Typography>
        )}
        <Typography
          component="span"
          sx={{
            display: 'block',
            color: '#888',
            fontSize: '0.8rem',
            fontWeight: '600',
          }}
        >
          {dossierContext?.name}
        </Typography>
        {dossierContext?.pnlType && (
          <Typography
            component="span"
            variant="body2"
            sx={{
              color: '#888888',
              fontStyle: 'italic',
              fontSize: '0.8rem',
            }}
          >
            {`( ${dossierContext.pnlType} )`}
          </Typography>
        )}
      </>
    );
  };

  const userProfile = () => {
    const userName =
      userContexts.find(
        (userContext: IUserContext) =>
          userContext.userContextType == UserContext_Types.ME
      )?.name ?? '';

    return (
      <>
        <Typography
          component="span"
          sx={{
            display: 'block',
            color: '#888',
            fontSize: '0.8rem',
            fontWeight: '600',
          }}
        >
          {userName}
        </Typography>
      </>
    );
  };

  const getDossierMenuItem = (dossierList: IDossierMenuItem[]) => {
    const expandArrow = (dossierItem: IDossierMenuItem) =>
      dossierItem.children.length > 0 ? (
        expandedItems.includes(dossierItem.name) ? (
          <ExpandLess />
        ) : (
          <ExpandMore />
        )
      ) : null;

    return (
      <List
        sx={{
          padding: '0rem',
          width: '100%',
        }}
      >
        {dossierList.map((dossierItem: IDossierMenuItem) => {
          if (!pnl.id && pnlScreens.includes(dossierItem.name)) {
            return;
          }
          let isSelected = false;
          if (dossierItem.path === selected) {
            isSelected = true;
          } else if (
            selectedParents.includes(dossierItem.name) &&
            !expandedItems.includes(dossierItem.name)
          ) {
            isSelected = true;
          }
          return (
            <Fragment key={dossierItem.name}>
              {
                <>
                  <ListItemButton
                    {...(dossierItem.path && { href: dossierItem.path })}
                    className={clsx(
                      styles[dossierItem.level],
                      isSelected && styles['&.Mui-selected']
                    )}
                  >
                    <ListItemText
                      primary={dossierItem.title}
                      {...(dossierItem.name === 'dossier' && {
                        secondary: dossierContextElement(),
                      })}
                      {...(dossierItem.name === 'profile' && {
                        secondary: userProfile(),
                      })}
                      sx={{ ml: `${dossierItem.level - 1}rem` }}
                    />
                    {dossierItem.children.length > 0 && (
                      <Box
                        onClick={(event: React.MouseEvent<HTMLElement>) =>
                          handleClick(event, dossierItem.name)
                        }
                        sx={{
                          display: 'flex',
                          alignItems: 'center',
                          justifyContent: 'center',
                          padding: '0 1rem',
                        }}
                      >
                        {expandArrow(dossierItem)}
                      </Box>
                    )}
                  </ListItemButton>

                  {dossierItem.children.length > 0 ? (
                    <Collapse
                      in={expandedItems.includes(dossierItem.name)}
                      timeout="auto"
                      unmountOnExit
                    >
                      {getDossierMenuItem(dossierItem.children)}
                    </Collapse>
                  ) : null}
                </>
              }
            </Fragment>
          );
        })}
      </List>
    );
  };

  useEffect(() => {
    if (router.pathname) {
      setSelected(router.pathname);
    }
  }, [router.pathname]);

  useEffect(() => {
    if (selected) {
      setInitialUserMenuState(dossierMenu);
    }
  }, [selected, dossierMenu, setInitialUserMenuState]);

  return getDossierMenuItem(dossierMenu);
};

export default UserDossierMenu;
