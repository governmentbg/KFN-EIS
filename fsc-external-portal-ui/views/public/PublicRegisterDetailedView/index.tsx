import { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import { useRouter } from 'next/router';
import { useTranslation } from 'react-i18next';
import {
  useFetchPublicRegisterPnlQuery,
  useFetchPublicRegisterSectionsQuery,
} from '../../../store/api/publicRegisterPnlSlice';
import PageContentHeader from '../../private/shared/PageContentHeader';
import RegisterAccordion from './commonComponents/RegisterAccordion';
import { PublicRegisterSectionType } from '../../../contracts/types/publicRegisterPnl';
import { useAppDispatch } from '../../../app/hooks';
import { setLoader } from '../../../store/loader';
import DynamicSection from './DynamicSection';

const PublicRegisterDetailedView = () => {
  const dispatch = useAppDispatch();
  const [skip, setSkip] = useState(true);
  const router = useRouter();
  const pnlId = router?.query?.pnlId?.toString() ?? '';
  const { data: pnlData, isLoading: isPnlDataStillLoad } =
    useFetchPublicRegisterPnlQuery(
      {
        pnlId,
      },
      { skip }
    );
  const { data, isLoading: isSectionsDataStillLoad } =
    useFetchPublicRegisterSectionsQuery(
      {
        serviceId: router?.query?.id?.toString() ?? '',
        pnlId,
      },
      { skip }
    );

  const { result: sections } = data?.pageResponse || {};

  useEffect(() => {
    isPnlDataStillLoad || isSectionsDataStillLoad
      ? dispatch(setLoader({ active: true }))
      : dispatch(setLoader({ active: false }));
  }, [dispatch, isPnlDataStillLoad, isSectionsDataStillLoad]);

  useEffect(() => {
    if (
      typeof router?.query?.pnlId !== 'undefined' ||
      typeof router?.query?.id !== 'undefined'
    ) {
      setSkip(false);
    }
  }, [router?.query?.id, router?.query?.pnlId]);

  return (
    <>
      {pnlData?.pnlName && (
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
          {pnlData?.pnlName}
        </PageContentHeader>
      )}
      <Box
        sx={{
          display: 'flex',
          flexDirection: 'column',
          position: 'relative',
          height: { xs: 'auto', md: 'calc(100% - 3.75rem)' },
          width: '100%',
          overflowY: 'auto',
        }}
      >
        <Paper
          elevation={2}
          sx={{
            display: 'flex',
            flexDirection: 'column',
            height: 'auto',
            p: { xs: '0.5rem', sm: '1rem' },
            m: { xs: '0', sm: '0.1rem', md: '0.5rem 1rem' },
          }}
        >
          {sections?.map(
            ({ id, name, defaultExpanded }: PublicRegisterSectionType) => (
              <RegisterAccordion
                key={id}
                accordionTitle={name}
                accordionContent={<DynamicSection sectionId={id} />}
                accordionProps={{ defaultExpanded }}
              />
            )
          )}
        </Paper>
      </Box>
    </>
  );
};

export default PublicRegisterDetailedView;
