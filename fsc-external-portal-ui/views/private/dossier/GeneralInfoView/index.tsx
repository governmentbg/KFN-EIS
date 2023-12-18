import { useEffect, useState } from 'react';
import { useRouter } from 'next/router';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import { useTranslation } from 'react-i18next';
import PageContentHeader from '../../shared/PageContentHeader';
import { IUser, userSelector } from '../../../../store/user';
import { useAppSelector } from '../../../../app/hooks';
import { useFetchPnlGeneralInfoQuery } from '../../../../store/api/pnlGeneralInfoSlice';
import { IPersonStoreState, personSelector } from '../../../../store/person';
import { IPnlStoreState, pnlSelector } from '../../../../store/pnl';
import { IUserContext } from '../../../../contracts/interfaces/userContext';
import { UserContext_Types } from '../../../../contracts/enums/userContext';
import { ROUTES } from '../../../../constants';

const GeneralInfoView = () => {
  const { t } = useTranslation(['dossier']);
  const { userContexts } = useAppSelector<IUser>(userSelector);
  const router = useRouter();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const person = useAppSelector<IPersonStoreState>(personSelector);
  const pnl = useAppSelector<IPnlStoreState>(pnlSelector);
  const { data } = useFetchPnlGeneralInfoQuery(
    {
      accessToken,
      pnlId: pnl.id ? pnl.id : null,
    },
    { skip: pnl.id ? false : true }
  );

  const {
    name,
    headquartersAddress,
    correspondenceAddress,
    email,
    phone,
    website,
    fax,
    eikEgn,
  } = data || {
    name: '',
    headquartersAddress: '',
    correspondenceAddress: '',
    email: '',
    website: '',
    fax: '',
    phone: '',
    eikEgn: '',
  };

  const selectedContext = userContexts.find(
    (userContext: IUserContext) =>
      userContext.personId === person.id && userContext.pnlId === pnl.id
  );

  useEffect(() => {
    if (selectedContext?.userContextType === UserContext_Types.ME) {
      router.replace(ROUTES.USER.PROFILE);
    }
  }, [person.id, pnl.id, selectedContext, router]);

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
        {t('generalInfo.title.label')}
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
        <Stack flexDirection="row" sx={{ p: '1rem 0rem' }}>
          <Typography
            component="h2"
            variant="h3"
            color="primary.main"
            tabIndex={0}
            sx={{ alignSelf: 'center' }}
          >
            {t('generalInfo.subTitle.label')}
          </Typography>
        </Stack>
        <Box
          sx={{
            width: { xs: '100%', md: '90%', lg: '70%' },
            mt: '1rem',
          }}
        >
          <TextField
            label={t('generalInfo.name.label')}
            InputProps={{
              readOnly: true,
            }}
            aria-readonly={true}
            value={name ?? ''}
            variant="filled"
            size="small"
            fullWidth
            sx={{ mb: '2rem' }}
          />
          <TextField
            label={t('generalInfo.headquartersAddress.label')}
            InputProps={{
              readOnly: true,
            }}
            aria-readonly={true}
            value={headquartersAddress ?? ''}
            variant="filled"
            size="small"
            fullWidth
            sx={{ mb: '2rem' }}
          />
          <TextField
            label={t('generalInfo.correspondenceAddress.label')}
            InputProps={{
              readOnly: true,
            }}
            aria-readonly={true}
            value={correspondenceAddress ?? ''}
            variant="filled"
            size="small"
            fullWidth
            sx={{ mb: '2rem' }}
          />
          <Box
            sx={{
              display: 'flex',
              flexWrap: 'wrap',
              justifyContent: 'space-between',
            }}
          >
            <TextField
              label={t('generalInfo.email.label')}
              InputProps={{
                readOnly: true,
              }}
              aria-readonly={true}
              value={email ?? ''}
              variant="filled"
              size="small"
              sx={{ width: { xs: '100%', sm: '49%' }, mb: '2rem' }}
            />
            <TextField
              label={t('generalInfo.phone.label')}
              InputProps={{
                readOnly: true,
              }}
              aria-readonly={true}
              value={phone ?? ''}
              variant="filled"
              size="small"
              sx={{ width: { xs: '100%', sm: '49%' }, mb: '2rem' }}
            />
            <TextField
              label={t('generalInfo.fax.label')}
              InputProps={{
                readOnly: true,
              }}
              aria-readonly={true}
              value={fax ?? ''}
              variant="filled"
              size="small"
              sx={{ width: { xs: '100%', sm: '49%' }, mb: '2rem' }}
            />

            <TextField
              label={t('generalInfo.website.label')}
              InputProps={{
                readOnly: true,
              }}
              aria-readonly={true}
              value={website ?? ''}
              variant="filled"
              size="small"
              sx={{ width: { xs: '100%', sm: '49%' }, mb: '2rem' }}
            />
            <TextField
              label={
                selectedContext?.userContextType ===
                UserContext_Types.INDIVIDUAL
                  ? t('generalInfo.egn.label')
                  : t('generalInfo.eik.label')
              }
              InputProps={{
                readOnly: true,
              }}
              aria-readonly={true}
              value={eikEgn ?? ''}
              variant="filled"
              size="small"
              sx={{ width: { xs: '100%', sm: '49%' }, mb: '2rem' }}
            />
          </Box>
        </Box>
      </Paper>
    </Box>
  );
};
export default GeneralInfoView;
