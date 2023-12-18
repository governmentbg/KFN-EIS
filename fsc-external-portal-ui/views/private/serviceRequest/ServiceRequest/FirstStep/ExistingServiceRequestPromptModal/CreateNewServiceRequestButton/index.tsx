import Button from '@mui/material/Button';

type CreateNewServiceRequestButtonProps = {
  handleClick: (event: React.MouseEvent<HTMLElement>) => void;
};

const CreateNewServiceRequestButton = ({
  handleClick,
}: CreateNewServiceRequestButtonProps) => (
  <Button
    variant="outlined"
    onClick={handleClick}
    sx={(theme) => ({
      '&.Mui-disabled': {
        color: theme.button.colorDisabled,
        backgroundColor: theme.button.backgroundColorDisabled,
      },
    })}
  >
    Създай нов
  </Button>
);

export default CreateNewServiceRequestButton;
