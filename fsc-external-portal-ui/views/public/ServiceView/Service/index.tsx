import Link from '@mui/material/Link';
import Typography from '@mui/material/Typography';
import AppLink from '../../../../components/AppLink';
import { IService } from '../../../../contracts/interfaces/services';
import getServiceRoute from '../../../../utils/serviceHelpers/getServiceRoute';

const Service = ({ service }: { service: IService }) => {
  const serviceRoute: string = getServiceRoute(service);

  return (
    <>
      <AppLink href={serviceRoute}>
        <Link
          variant="h2"
          component="h2"
          color="primary"
          underline="none"
          sx={{ cursor: 'pointer' }}
        >
          {service.name}
        </Link>
      </AppLink>
      <Typography variant="body1" component="span" color="text.secondary">
        {service.descriptionShort}
      </Typography>
    </>
  );
};

export default Service;
