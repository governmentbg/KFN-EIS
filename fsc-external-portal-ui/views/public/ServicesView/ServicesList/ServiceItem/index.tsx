import Box from '@mui/material/Box';
import Link from '@mui/material/Link';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import AppLink from '../../../../../components/AppLink';
import {
  IServiceCard,
  IServiceCardContent,
} from '../../../../../contracts/interfaces/services';

const ServiceItem = (props: IServiceCard) => {
  const { header, content } = props;
  return (
    <Box
      sx={{
        m: { xs: '1rem 0', md: '1rem' },
      }}
    >
      <Paper
        sx={(theme) => ({
          position: 'relative',
          maxWidth: '100%',
          minHeight: '100%',
          padding: { xs: '1rem', sm: '2rem' },
          backgroundColor: 'rgba(239, 246, 254, 0.9)',
        })}
      >
        <Typography
          variant="h2"
          component="h2"
          sx={{
            minHeight: '3rem',
          }}
        >
          {header}
        </Typography>

        {content.length > 0
          ? content.map((contentItem: IServiceCardContent) => (
              <Box
                sx={{
                  padding: '.5rem 1rem .5rem 0',
                }}
                key={contentItem.id}
              >
                <AppLink href={`/services/${contentItem.id}`}>
                  <Link
                    variant="h4"
                    color="inherit"
                    underline="none"
                    component={'a'}
                    sx={{ cursor: 'pointer' }}
                  >
                    &gt; {contentItem.title}
                  </Link>
                </AppLink>
              </Box>
            ))
          : null}
      </Paper>
    </Box>
  );
};

export default ServiceItem;
