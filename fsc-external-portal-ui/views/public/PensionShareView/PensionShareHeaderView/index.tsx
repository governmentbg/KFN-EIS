import Stack from '@mui/material/Stack';
import PensionShareHeader from './PensionShareHeader';
import PensionShareSubHeader from './PensionShareSubHeader';

type PensionShareHeaderViewPropsType = {
  headerTitle: string;
  subHeaderTitle: string;
};
const PensionShareHeaderView = ({
  headerTitle,
  subHeaderTitle,
}: PensionShareHeaderViewPropsType) => (
  <Stack
    sx={{
      p: '0.5rem 0rem',
      flexWrap: 'wrap',
      flex: 1,
      alignContent: 'flex-start',
    }}
  >
    <PensionShareHeader title={headerTitle} />
    <PensionShareSubHeader title={subHeaderTitle} />
  </Stack>
);

export default PensionShareHeaderView;
