import React, { useState } from 'react';
import { useTranslation } from 'react-i18next';
import clsx from 'clsx';
import { Theme } from '@mui/material/styles';
import makeStyles from '@mui/styles/makeStyles';
import { IUserContext } from '../../../../contracts/interfaces/userContext';
import { UserContext_Types } from '../../../../contracts/enums/userContext';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';
import CheckIcon from '@mui/icons-material/Check';
import KeyboardArrowDownIcon from '@mui/icons-material/KeyboardArrowDown';
import ListSubheader from '@mui/material/ListSubheader';
import Box from '@mui/material/Box';
import { useAppDispatch, useAppSelector } from '../../../../app/hooks';
import {
  IPersonStoreState,
  personSelector,
  setPerson,
} from '../../../../store/person';
import { IPnlStoreState, pnlSelector, setPnl } from '../../../../store/pnl';
import { IUser, userSelector } from '../../../../store/user';
import Typography from '@mui/material/Typography';
import { handleError } from '../../../../utils/handlers/errorHandlers';

const useStyles = makeStyles((theme: Theme) => ({
  root: {
    display: 'flex',
    '&:hover': {
      backgroundColor: 'rgb(84 110 122 / 4%)',
      borderRadius: '1rem',
      boxShadow: '0 0 0 .2rem rgb(84 110 122 / 4%)',
    },
  },
  formControl: {
    minWidth: '10rem',
    maxWidth: '20rem',
    justifyContent: 'center',
    '& .MuiInput-root': {
      margin: 0,
    },
  },
  inputLabel: { visibility: 'hidden' },
  select: {
    paddingLeft: '.25rem',
    backgroundColor: theme.palette.background.paper,
    minWidth: '10rem',
    '& #check-icon': {
      display: 'none',
    },
    borderRadius: '.5rem',
  },
  listSubheader: {
    backgroundColor: '#F7F7F7',
  },
  menuItem: {
    '&:hover': {
      backgroundColor: theme.button.hover,
    },
  },
  menuItemSelected: {
    '&.Mui-selected': {
      backgroundColor: theme.palette.background.paper,
      fontWeight: theme.typography.fontWeightBold,
    },
  },
  checkIcon: {
    marginLeft: 'auto',
  },
  hidden: {
    visibility: 'hidden',
  },
}));

const UserContextSelect = () => {
  const styles = useStyles();
  const { t } = useTranslation(['userContextSelect']);
  const dispatch = useAppDispatch();
  const { userContexts } = useAppSelector<IUser>(userSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);
  const [selectedUserContext, setSelectedUserContext] =
    useState<IUserContext | null>(
      userContexts.find(
        (userContext: IUserContext) =>
          userContext.personId == person.id && userContext.pnlId === pnl.id
      ) ?? null
    );

  const handleChange = ({ target: { value } }: SelectChangeEvent) => {
    if (typeof value !== 'string') {
      const valueObject: IUserContext = value;

      const { personId, pnlId } =
        userContexts.find(
          (userContext: IUserContext) =>
            userContext.name === valueObject.name &&
            userContext.pnlId === valueObject.pnlId
        ) || {};

      dispatch(setPerson({ id: personId }));
      dispatch(setPnl({ id: pnlId }));
      setSelectedUserContext(valueObject);
    }
  };

  const listSubHeader = (userContextType: string) => (
    <ListSubheader
      aria-label={`${t('category')} ${t(`${userContextType}`)}`}
      className={styles.listSubheader}
    >
      {t(`${userContextType}`)}
    </ListSubheader>
  );

  const checkIcon = (isUserContextSelected: boolean) => (
    <CheckIcon
      id="check-icon"
      className={clsx(
        styles.checkIcon,
        !isUserContextSelected && styles.hidden
      )}
    />
  );

  const menuItem = (
    isUserContextSelected: boolean,
    userContext: IUserContext
  ) => (
    <MenuItem
      role="option"
      aria-selected={isUserContextSelected}
      key={
        userContext.personId +
        userContext.name +
        userContext.pnlId +
        userContext.userContextType
      }
      value={userContext as any}
      className={clsx(
        styles.menuItem,
        isUserContextSelected && styles.menuItemSelected
      )}
    >
      <Typography
        component="span"
        sx={{
          mr: '.5rem',
        }}
      >
        {userContext.name}
      </Typography>
      {userContext.pnlType && (
        <Typography
          variant="body2"
          sx={{
            color: '#888888',
            fontStyle: 'italic',
            fontSize: '.8rem',
          }}
        >
          {` ( ${userContext.pnlType} )`}
        </Typography>
      )}
      {checkIcon(isUserContextSelected)}
    </MenuItem>
  );

  return (
    <Box className={styles.root}>
      <FormControl className={styles.formControl} size="small">
        <InputLabel
          id="user-context-select"
          className={styles.inputLabel}
        >{`${t('selectUserContextToOperateWith')}. ${
          selectedUserContext?.name
        } ${t('isSelected')}`}</InputLabel>

        <Select
          labelId="user-context-select"
          value={selectedUserContext as any}
          onChange={handleChange}
          disableUnderline={true}
          IconComponent={KeyboardArrowDownIcon}
          variant="standard"
          className={styles.select}
          MenuProps={{
            anchorOrigin: {
              vertical: 'bottom',
              horizontal: 'right',
            },
            transformOrigin: {
              vertical: 'top',
              horizontal: 'right',
            },
            sx: {
              '.MuiMenu-list': {
                minWidth: 300,
                maxWidth: '100%',
                p: 0,
              },
            },
          }}
        >
          {listSubHeader(UserContext_Types.ME)}

          {userContexts.map((userContext: IUserContext) => {
            const isUserContextSelected =
              userContext.personId === selectedUserContext?.personId &&
              userContext.pnlId === selectedUserContext?.pnlId;
            if (userContext.userContextType === UserContext_Types.ME) {
              return menuItem(isUserContextSelected, userContext);
            }
          })}

          {listSubHeader(UserContext_Types.INDIVIDUAL)}

          {userContexts.map((userContext: IUserContext) => {
            const isUserContextSelected =
              userContext.personId === selectedUserContext?.personId &&
              userContext.pnlId === selectedUserContext?.pnlId;
            if (userContext.userContextType === UserContext_Types.INDIVIDUAL) {
              return menuItem(isUserContextSelected, userContext);
            }
          })}

          {listSubHeader(UserContext_Types.LEGAL_ENTITY)}

          {userContexts.map((userContext: IUserContext) => {
            const isUserContextSelected =
              userContext.personId === selectedUserContext?.personId &&
              userContext.pnlId === selectedUserContext?.pnlId;
            if (
              userContext.userContextType === UserContext_Types.LEGAL_ENTITY
            ) {
              return menuItem(isUserContextSelected, userContext);
            }
          })}
        </Select>
      </FormControl>
    </Box>
  );
};
export default UserContextSelect;
