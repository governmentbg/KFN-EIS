import { useState } from 'react';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import { useTranslation } from 'react-i18next';
import PageContentHeader from '../shared/PageContentHeader';
import { IUser, userSelector } from '../../../store/user';
import { useAppSelector } from '../../../app/hooks';
import { useFetchUserProfileInfoQuery } from '../../../store/api/userProfileInfoSlice';
import { IUserContext } from '../../../contracts/interfaces/userContext';
import { UserContext_Types } from '../../../contracts/enums/userContext';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';

const UserProfileView = () => {
  const { t } = useTranslation(['userProfile']);
  const { userContexts } = useAppSelector<IUser>(userSelector);
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const profileUser =
    userContexts.find(
      (userContext: IUserContext) =>
        userContext.userContextType === UserContext_Types.ME
    ) ?? null;
  const { data } = useFetchUserProfileInfoQuery({
    accessToken,
  });

  const {
    personFirstName,
    personMiddleName,
    personLastName,
    egn,
    addressResponse,
    phoneResponse,
    emailResponse,
  } = data || {
    personFirstName: '',
    personMiddleName: '',
    personLastName: '',
    egn: '',
    addressResponse: [],
    phoneResponse: [],
    emailResponse: [],
  };

  const loadGroupData = (groupData: { id: number; value: string }[]) => {
    let groupTitle = '';
    let groupStyle = {};
    let textFieldLabel = '';
    switch (groupData) {
      case phoneResponse:
        groupTitle = t('basicData.phoneGroup.label');
        groupStyle = { width: { xs: '100%', sm: '50%' } };
        textFieldLabel = t('basicData.phoneNumber.label');
        break;
      case emailResponse:
        groupTitle = t('basicData.emailGroup.label');
        groupStyle = { width: { xs: '100%', sm: '50%' } };
        textFieldLabel = t('basicData.email.label');
        break;
      case addressResponse:
        groupTitle = t('basicData.addressGroup.label');
        groupStyle = { width: '100%' };
        textFieldLabel = t('basicData.address.label');
        break;
      default:
        groupTitle = '';
        groupStyle = {};
        textFieldLabel = '';
    }

    return (
      <Box sx={groupStyle}>
        <Typography component="h3" color="primary.main" tabIndex={0}>
          {groupTitle}
        </Typography>
        <List>
          {groupData.length > 0 &&
            groupData.map((listItem) => (
              <ListItem key={listItem.id} sx={{ paddingRight: 0 }}>
                <TextField
                  label={textFieldLabel}
                  InputProps={{
                    readOnly: true,
                  }}
                  aria-readonly={true}
                  value={listItem.value ?? ''}
                  variant="filled"
                  size="small"
                  fullWidth
                />
              </ListItem>
            ))}
          {groupData.length <= 0 && (
            <ListItem>
              <ListItemText
                primary={t('basicData.noData.label')}
                primaryTypographyProps={{
                  style: { color: 'rgba(136, 136, 136, 0.7)' },
                }}
              />
            </ListItem>
          )}
        </List>
      </Box>
    );
  };

  return (
    <Box
      sx={{
        display: 'flex',
        flexDirection: 'column',
        position: 'relative',
        height: '100%',
        width: '100%',
      }}
    >
      <PageContentHeader
        rootProps={{
          sx: {
            backgroundColor: (t) => t.palette.background.paper,
            p: '1rem',
          },
        }}
        typographyProps={{
          component: 'h1',
          variant: 'h2',
          tabIndex: 0,
        }}
      >
        {`${t('title.label')} - ${profileUser?.name}`}
      </PageContentHeader>

      <Paper
        elevation={2}
        sx={{
          display: 'flex',
          flexDirection: 'column',
          p: '1rem',
          m: '1rem',
        }}
      >
        <Stack flexDirection="row" sx={{ p: '0.5rem 0rem', flexWrap: 'wrap' }}>
          <Typography
            component="h2"
            variant="h3"
            color="primary.main"
            tabIndex={0}
            sx={{ alignSelf: 'center' }}
          >
            {t('basicData.title.label')}
          </Typography>
        </Stack>
        <Box
          sx={{
            display: 'flex',
            flexWrap: 'wrap',
            justifyContent: 'space-between',
            width: { xs: '100%', md: '90%', lg: '80%' },
            mt: '1rem',
          }}
        >
          <TextField
            label={t('basicData.personFirstName.label')}
            InputProps={{
              readOnly: true,
            }}
            aria-readonly={true}
            value={personFirstName ?? ''}
            variant="filled"
            size="small"
            fullWidth
            sx={{ width: { xs: '100%', sm: '49%', md: '31%' }, mb: '2rem' }}
          />
          <TextField
            label={t('basicData.personMiddleName.label')}
            InputProps={{
              readOnly: true,
            }}
            aria-readonly={true}
            value={personMiddleName ?? ''}
            variant="filled"
            size="small"
            fullWidth
            sx={{ width: { xs: '100%', sm: '49%', md: '31%' }, mb: '2rem' }}
          />
          <TextField
            label={t('basicData.personLastName.label')}
            InputProps={{
              readOnly: true,
            }}
            aria-readonly={true}
            value={personLastName ?? ''}
            variant="filled"
            size="small"
            fullWidth
            sx={{ width: { xs: '100%', sm: '49%', md: '31%' }, mb: '2rem' }}
          />
          <TextField
            label={t('basicData.egn.label')}
            InputProps={{
              readOnly: true,
            }}
            aria-readonly={true}
            value={egn ?? ''}
            variant="filled"
            size="small"
            sx={{ width: { xs: '100%', sm: '49%', md: '31%' }, mb: '2rem' }}
          />
          <Box sx={{ display: 'flex', flexWrap: 'wrap', width: '100%' }}>
            {loadGroupData(phoneResponse)}
            {loadGroupData(emailResponse)}
          </Box>
          {loadGroupData(addressResponse)}
        </Box>
      </Paper>
    </Box>
  );
};
export default UserProfileView;
