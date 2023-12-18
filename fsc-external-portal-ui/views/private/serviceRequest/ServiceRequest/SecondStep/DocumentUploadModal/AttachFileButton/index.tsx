import AddIcon from '@mui/icons-material/Add';
import Button from '@mui/material/Button';
import { IAttachment } from '../../../../../../../contracts/interfaces/attachment';
import { handleError } from '../../../../../../../utils/handlers/errorHandlers';
import { useFormikContext } from 'formik';
import { DocumentUploadModalFormikContextType } from '..';
import { styled } from '@mui/material/styles';
import Stack from '@mui/material/Stack';
import { useTranslation } from 'react-i18next';
import { useCallback, useContext } from 'react';
import DocumentUploadModalContext, {
  IDocumentUploadModalContext,
} from '../DocumentUploadModalContext';
import { useAppDispatch } from '../../../../../../../app/hooks';
import { setLoader } from '../../../../../../../store/loader';

const Input = styled('input')({
  display: 'none',
});

const fieldName = 'attachment';

const AttachFileButton = () => {
  const { t } = useTranslation(['services']);
  const dispatch = useAppDispatch();
  const { fileFormikValues, setFileFormikValues } =
    useContext<IDocumentUploadModalContext>(DocumentUploadModalContext);

  const { setFieldValue } =
    useFormikContext<DocumentUploadModalFormikContextType>();

  const handleInput = useCallback(
    async (e) => {
      const target = e.target as HTMLInputElement;

      if (!target.files || target.files.length === 0) return;

      const file = target.files[0];

      target.value = ''; //This row solves the problem with uploading the same file twice

      try {
        dispatch(setLoader({ active: true }));

        setFieldValue(fieldName, {
          file,
          fileRef: '',
          fileName: file.name,
        } as IAttachment);
      } catch (error: any) {
        handleError(error);
      } finally {
        fileFormikValues && setFileFormikValues(undefined);
        setTimeout(() => {
          dispatch(setLoader({ active: false }));
        }, 500);
      }
    },
    [dispatch, fileFormikValues, setFieldValue, setFileFormikValues]
  );

  return (
    <Stack alignItems="flex-start">
      <Button startIcon={<AddIcon />} component="label" variant="text">
        {t('services.serviceRequest.documents.uploadModal.uploadFile.label')}
        <Input
          hidden
          name={fieldName}
          id="contained-button-file"
          type="file"
          onInput={handleInput}
        />
      </Button>
    </Stack>
  );
};

export default AttachFileButton;
