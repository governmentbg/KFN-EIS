import Box from '@mui/material/Box';
import { useTranslation } from 'react-i18next';
import { IDocumentType } from '../../../../../../../contracts/interfaces/document';
import { useAppSelector } from '../../../../../../../app/hooks/redux';
import { documentTypesSelector } from '../../../../../../../store/documentTypes';
import { useFormikContext } from 'formik';
import { DocumentUploadModalFormikContextType } from '..';
import transformErrors from '../../../../../../../utils/transformers/transformErrors';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import { useEffect } from 'react';

interface DocumentTypeSelectPropsType {
  selectedDocumentTypeId?: number | null;
  modalStateFlag: boolean;
}

export default function DocumentTypeSelect({
  selectedDocumentTypeId,
  modalStateFlag,
}: DocumentTypeSelectPropsType) {
  const { t } = useTranslation(['services']);
  const documentTypes = useAppSelector<IDocumentType[]>(documentTypesSelector);
  const { handleBlur, getFieldMeta, setFieldValue } =
    useFormikContext<DocumentUploadModalFormikContextType>();
  const valueDocumentTypeId = getFieldMeta('documentTypeId').value as string;
  const valueSceDocumentTypeId = getFieldMeta('sceDocumentTypeId')
    .value as string;
  const error = getFieldMeta('documentTypeId').error;
  const touched = getFieldMeta('documentTypeId').touched;
  let conditionalSelectValue: IDocumentType | null;
  let conditionalSelectOptions: IDocumentType[];

  if (selectedDocumentTypeId) {
    conditionalSelectValue =
      documentTypes?.find(
        (el: IDocumentType) => el.id === selectedDocumentTypeId
      ) ?? null;

    conditionalSelectOptions = documentTypes.filter(
      (el) => el.id === selectedDocumentTypeId
    );
  } else {
    conditionalSelectOptions = documentTypes;

    conditionalSelectValue =
      documentTypes?.find(
        (dt: IDocumentType) =>
          dt?.id?.toString() === valueSceDocumentTypeId.toString()
      ) ?? null;
  }

  useEffect(() => {
    if (modalStateFlag) {
      setFieldValue(
        'documentTypeId',
        conditionalSelectValue?.documentTypeId?.toString() ?? ''
      );
      setFieldValue(
        'sceDocumentTypeId',
        conditionalSelectValue?.id?.toString() ?? ''
      );
    }
  }, [modalStateFlag, conditionalSelectValue, setFieldValue]);

  return (
    <Box sx={{ minWidth: { xs: '90%', md: '33%' } }}>
      <Autocomplete
        sx={{ width: 300 }}
        options={conditionalSelectOptions}
        autoHighlight
        disabled
        getOptionLabel={(o) => o?.shortName ?? ''}
        value={
          documentTypes?.find(
            (dt: IDocumentType) =>
              dt?.id?.toString() === valueSceDocumentTypeId.toString()
          ) ?? null
        }
        readOnly={selectedDocumentTypeId ? true : false}
        onChange={(_, o) => {
          setFieldValue(
            'documentTypeId',
            conditionalSelectValue?.documentTypeId?.toString() ?? ''
          );
          setFieldValue(
            'sceDocumentTypeId',
            conditionalSelectValue?.id?.toString() ?? ''
          );
        }}
        renderInput={(params) => (
          <TextField
            {...params}
            name="documentTypeId"
            label={t(
              'services.serviceRequest.documents.uploadModal.documentTypeSelect.label'
            )}
            onBlur={handleBlur}
            variant="outlined"
            error={Boolean(touched && error)}
            helperText={touched && transformErrors(error)}
            inputProps={{
              ...params.inputProps,
            }}
            sx={{ backgroundColor: 'rgba(0,0,0,0.06)', borderRadius: '4px' }}
          />
        )}
      />
    </Box>
  );
}
