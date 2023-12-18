import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { Dispatch, SetStateAction, useCallback } from 'react';
import { useTranslation } from 'react-i18next';

type ConfirmationDialogPropsType = {
  open: boolean;
  setOpen: Dispatch<SetStateAction<boolean>>;
  title?: string;
  contentText: string;
  confirmButtonLabel?: string;
  cancelButtonLabel?: string;
  onConfirm: () => void;
  onCancel?: () => void;
};
const ConfirmationDialog = (props: ConfirmationDialogPropsType) => {
  const { t } = useTranslation(['services']);
  const {
    open,
    setOpen,
    title,
    contentText,
    onConfirm,
    onCancel,
    confirmButtonLabel,
    cancelButtonLabel,
  } = props;

  const closeDialog = useCallback(() => {
    setOpen(false);
  }, [setOpen]);
  return (
    <Dialog open={open}>
      <DialogTitle>{title}</DialogTitle>
      <DialogContent>
        <DialogContentText color="text.main">
          {contentText}
        </DialogContentText>
      </DialogContent>

      <DialogActions>
        <Button variant="contained" sx={{ mr: '.25rem' }} onClick={onConfirm}>
          {confirmButtonLabel ?? t('confirm', { ns: 'common' })}
        </Button>
        <Button variant="outlined" onClick={onCancel ?? closeDialog}>
          {cancelButtonLabel ?? t('cancel', { ns: 'common' })}
        </Button>
      </DialogActions>
    </Dialog>
  );
};

export default ConfirmationDialog;
