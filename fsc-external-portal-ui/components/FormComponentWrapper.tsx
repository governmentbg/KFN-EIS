import Stack, { StackProps } from '@mui/material/Stack';
import { ServiceRequestFormFieldComponentProps } from '../contracts/types/serviceRequest/formComponents/fields';

type FormComponentWrapperPropsType = {
  children: JSX.Element;
  componentProps: ServiceRequestFormFieldComponentProps;
  rootProps?: StackProps;
};

const FormComponentWrapper = ({
  children,
  componentProps: { direction, width },
  rootProps,
}: FormComponentWrapperPropsType) => (
  <Stack
    display="inline-flex"
    flexDirection={direction}
    m=".25rem .625rem .5rem .25rem"
    className={width ?? ''}
    {...rootProps}
  >
    {children}
  </Stack>
);

export default FormComponentWrapper;
