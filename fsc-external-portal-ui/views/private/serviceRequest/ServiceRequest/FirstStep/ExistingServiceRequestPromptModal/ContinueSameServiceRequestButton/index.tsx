import Button from '@mui/material/Button';

type ContinueSameServiceRequestButtonProps = {
  handleClick: (event: React.MouseEvent<HTMLElement>) => void;
};

const ContinueSameServiceRequestButton = ({
  handleClick,
}: ContinueSameServiceRequestButtonProps) => (
  <Button
    variant="contained"
    onClick={handleClick}
    sx={(theme) => ({
      '&.Mui-disabled': {
        color: theme.button.colorDisabled,
        backgroundColor: theme.button.backgroundColorDisabled,
      },
    })}
  >
    Продължи
  </Button>
);

export default ContinueSameServiceRequestButton;
