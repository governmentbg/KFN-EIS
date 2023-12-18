import ExpandLess from '@mui/icons-material/ExpandLess';
import ExpandMore from '@mui/icons-material/ExpandMore';
import Collapse from '@mui/material/Collapse';
import List from '@mui/material/List';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import { Box } from '@mui/system';
import { Theme } from '@mui/material/styles';
import makeStyles from '@mui/styles/makeStyles';
import { Fragment, useEffect, useState } from 'react';
import { IService } from '../contracts/interfaces/services';
import clsx from 'clsx';
import { useRouter } from 'next/router';
import { NextSeo } from 'next-seo';
import getServiceParents from '../utils/serviceHelpers/getServiceParents';
import getServiceChildrenIds from '../utils/serviceHelpers/getServiceChildrenIds';
import getServiceRoute from '../utils/serviceHelpers/getServiceRoute';

const useStyles = makeStyles((theme: Theme) => ({
  '&.Mui-selected': {
    backgroundColor: `${theme.sidebar.level.any.backgroundColorSelected} !important`,
    color: theme.sidebar.level.any.textColorSelected,
    '& .MuiListItemText-primary': { color: 'white' },
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
  4: {
    backgroundColor: theme.sidebar.level[4].backgroundColor,
    color: theme.sidebar.level[4].textColor,
    paddingRight: '0',
    '&:hover': {
      backgroundColor: theme.sidebar.level.any.backgroundColorHovered,
    },
  },
  5: {
    backgroundColor: theme.sidebar.level[5].backgroundColor,
    color: theme.sidebar.level[5].textColor,
    paddingRight: '0',
    '&:hover': {
      backgroundColor: theme.sidebar.level.any.backgroundColorHovered,
    },
  },
}));

const Sidebar = ({
  levels,
  topLevelService,
  services,
}: {
  levels: number[];
  topLevelService?: IService;
  services: IService[] | [];
}) => {
  const styles = useStyles();
  const router = useRouter();
  const [expandedItems, setExpandedItems] = useState<Array<number>>([]);
  const [selected, setSelected] = useState<number | null>(null);
  const [currentServiceParents, setCurrentServiceParents] = useState<
    Array<number>
  >([]);

  const handleClick = (event: React.MouseEvent<HTMLElement>, id: number) => {
    event.preventDefault();
    if (!expandedItems.includes(id)) {
      setExpandedItems([...expandedItems, id]);
    } else {
      setExpandedItems(
        expandedItems.filter((element: number) => element !== id)
      );
    }
  };

  const getServicesList = (
    levels: number[],
    service?: IService,
    iterationNumber: number = 1
  ) => {
    const remainingLevels = [...levels].slice(1);

    const nextServiceLevel = service
      ? service.catalogElement.level + 1
      : levels[0];

    const serviceChildrenIds = service ? getServiceChildrenIds(service) : [];
    const filteredServices = services
      ? service
        ? services.filter(
            (serviceItem: IService) =>
              serviceItem.catalogElement.level === nextServiceLevel &&
              serviceChildrenIds.includes(serviceItem.catalogElement.id)
          )
        : services.filter(
            (serviceItem: IService) =>
              serviceItem.catalogElement.level === levels[0]
          )
      : [];

    const expandArrow = (serviceItem: IService) =>
      remainingLevels.length > 0 &&
      serviceItem.catalogElement.children.length > 0 ? (
        expandedItems.includes(serviceItem.catalogElement.id) ? (
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
        {filteredServices.map((serviceItem: IService) => {
          const serviceRoute: string = getServiceRoute(serviceItem);

          return (
            <Fragment key={serviceItem.catalogElement.id}>
              {
                <>
                  <ListItemButton
                    href={serviceRoute}
                    className={clsx(
                      styles[iterationNumber ? iterationNumber : 1],
                      (selected === serviceItem.catalogElement.id ||
                        (currentServiceParents.includes(
                          serviceItem.catalogElement.id
                        ) &&
                          !expandedItems.includes(
                            serviceItem.catalogElement.id
                          ))) &&
                        styles['&.Mui-selected']
                    )}
                  >
                    <ListItemText
                      primary={serviceItem.name}
                      sx={{ ml: `${iterationNumber - 1}rem` }}
                    />

                    <Box
                      onClick={(event: React.MouseEvent<HTMLElement>) =>
                        handleClick(event, serviceItem.catalogElement.id)
                      }
                      sx={{
                        display: 'flex',
                        alignItems: 'center',
                        justifyContent: 'center',
                        padding: '1rem',
                      }}
                    >
                      {expandArrow(serviceItem)}
                    </Box>
                  </ListItemButton>

                  {remainingLevels.length > 0 ? (
                    <Collapse
                      in={
                        expandedItems.includes(serviceItem.catalogElement.id)
                          ? true
                          : false
                      }
                      timeout="auto"
                      unmountOnExit
                    >
                      {getServicesList(
                        remainingLevels,
                        serviceItem,
                        iterationNumber + 1
                      )}
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
    if (router.query.id) {
      const currentServiceId = Number(router.query.id);

      const serviceParentsIds = getServiceParents(
        services,
        services.find(
          (service: IService) => service.catalogElement.id === currentServiceId
        ) as IService
      ).map((serviceItem: IService) => serviceItem.catalogElement.id);

      setCurrentServiceParents(serviceParentsIds);
      setExpandedItems([...serviceParentsIds, currentServiceId]);
      setSelected(currentServiceId);
    }
  }, [services, router.query.id]);

  return (
    <>
      {services && (
        <NextSeo
          title={
            services.find(
              (serviceItem: IService) =>
                serviceItem.catalogElement.id === Number(router.query?.id)
            )?.name
          }
        />
      )}
      {getServicesList(
        levels && levels.length > 0 ? levels : [1, 2, 3, 4, 5],
        topLevelService ? topLevelService : undefined
      )}
    </>
  );
};

export default Sidebar;
