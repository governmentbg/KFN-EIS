import React, { useCallback, useContext, useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import { useFormik } from 'formik';
import { useAppSelector } from '../../../../../app/hooks/redux';
import ActionButtons from '../commonComponents/ActionButtons';
import { IServiceRequestContext } from '../ServiceRequestContext';
import { useTranslation } from 'react-i18next';
import Paper from '@mui/material/Paper';
import Stack from '@mui/material/Stack';
import DocumentsDataGrid from './DocumentsDataGrid';
import DocumentUploadModal from './DocumentUploadModal';
import {
  IDocument,
  IDocumentType,
} from '../../../../../contracts/interfaces/document';
import ServiceRequestContext from '../ServiceRequestContext';
import { documentTypesSelector } from '../../../../../store/documentTypes';
import showMissingMandatoryDocuments from '../../../../../utils/serviceHelpers/documentUploadModal/showMissingMandatoryDocuments';
import DocumentTypesCheckList from './DocumentTypesCheckList';
import { DOCUMENT_TYPE_NECESSITY_OPTIONS } from '../../../../../contracts/enums/documentTypes';
import ErrorOutlineOutlinedIcon from '@mui/icons-material/ErrorOutlineOutlined';
import useServiceRequestHandleUserContextChange from '../../../../../app/hooks/serviceRequest/useServiceRequestHandleUserContextChange';

const SecondStep = () => {
  const { handleNext, handleBack, activeStep, steps, documents } =
    useContext<IServiceRequestContext>(ServiceRequestContext);

  useServiceRequestHandleUserContextChange(activeStep);

  const { t } = useTranslation(['formFields', 'errors']);

  const [optionalDocId, setOptionalDocId] = useState<number | null>(null);

  const documentTypes = useAppSelector<IDocumentType[]>(documentTypesSelector);

  // Check if we have other types than Optional
  let hasOnlyOptionalDocs: boolean;

  // Check if we have other than Optional types
  const hasOtherTypes: boolean = documentTypes.some(
    (doc) =>
      doc.mandatory === DOCUMENT_TYPE_NECESSITY_OPTIONS.MANDATORY ||
      doc.mandatory === DOCUMENT_TYPE_NECESSITY_OPTIONS.RECOMMENDED
  );
  // Set flag for the button text
  optionalDocId && hasOtherTypes
    ? (hasOnlyOptionalDocs = false)
    : (hasOnlyOptionalDocs = true);

  const formik = useFormik({
    initialValues: {
      documents,
    },
    onSubmit: async (_values, { setSubmitting }) => {
      setSubmitting(false);

      if (!isFormValid()) {
        showMissingMandatoryDocuments();
      } else {
        handleNext();
      }
    },
  });

  const { handleSubmit } = formik;

  const checkMandatoryDocuments = useCallback(
    (
      mandatoryDocumentTypesIds: string[],
      uploadedDocumentTypesIds: string[]
    ): boolean => {
      if (mandatoryDocumentTypesIds.length === 0) return true;
      if (uploadedDocumentTypesIds.length === 0) return false;

      let isValid = true;
      mandatoryDocumentTypesIds.forEach((mandatoryDocumentTypeId: string) => {
        if (!uploadedDocumentTypesIds.includes(mandatoryDocumentTypeId)) {
          isValid = false;
        }
      });

      return isValid;
    },
    []
  );

  const isFormValid = useCallback((): boolean => {
    if (documentTypes.length === 0) return true;

    const checkResult = checkMandatoryDocuments(
      documentTypes
        .filter(
          (documentType: IDocumentType) =>
            documentType.mandatory === DOCUMENT_TYPE_NECESSITY_OPTIONS.MANDATORY
        )
        .map((d: IDocumentType) => d.id?.toString()),
      documents.map((document: IDocument) =>
        document.sceDocumentTypeId.toString()
      )
    );

    return checkResult;
  }, [checkMandatoryDocuments, documentTypes, documents]);

  const hasMandatoryDocs = documentTypes.some(
    (doc) => doc.mandatory === DOCUMENT_TYPE_NECESSITY_OPTIONS.MANDATORY
  );

  useEffect(() => {
    documentTypes.forEach((doc) => {
      if (doc.mandatory === DOCUMENT_TYPE_NECESSITY_OPTIONS.OPTIONAL) {
        setOptionalDocId(doc.id);
      }
    });
  }, [documentTypes]);

  return (
    <>
      <form noValidate onSubmit={handleSubmit}>
        <Paper
          elevation={2}
          sx={{ p: '1rem', backgroundColor: 'common.white' }}
        >
          <Stack>
            {hasOtherTypes && (
              <Typography variant="h2" component="h2" color="primary.main">
                {t(`attachedDocuments`, { ns: 'common' })}
              </Typography>
            )}
            <Stack
              sx={{
                flexDirection: 'column',
              }}
            >
              <DocumentTypesCheckList showDocumentTypes="mandatory" />

              <DocumentTypesCheckList showDocumentTypes="recommended" />
            </Stack>

            {optionalDocId && (
              <DocumentUploadModal
                keepMounted={true}
                hasOnlyOptionalDocs={hasOnlyOptionalDocs}
                selectedDocumentTypeId={optionalDocId}
              />
            )}
            {optionalDocId || hasOtherTypes ? (
              <Typography
                variant="body2"
                component="h2"
                color="primary.main"
                sx={{ fontStyle: 'italic', color: 'primary', mt: '-.5rem' }}
              >
                {t(`documentsHintMessage`, { ns: 'messages' })}
              </Typography>
            ) : null}
            {hasMandatoryDocs && (
              <Typography
                variant="body2"
                component="h2"
                color="primary.main"
                sx={{
                  fontStyle: 'italic',
                  color: 'primary',
                }}
              >
                <ErrorOutlineOutlinedIcon
                  sx={{
                    color: '#ffa5a5',
                    fontSize: '1.2rem',
                    marginRight: '.3rem',
                    margin: '1rem .3rem -.2rem 0',
                  }}
                />
                {t(`mainLegendMessage`, { ns: 'messages' })}
              </Typography>
            )}
            <Box>
              <Typography
                variant="h2"
                component="h2"
                color="primary.main"
                sx={{ m: '1rem 0rem .5rem 0rem' }}
              >
                {t('attachedDocuments', { ns: 'common' })}
              </Typography>

              <Box sx={{ height: 400, width: '100%' }}>
                <Box sx={{ display: 'flex', height: '100%' }}>
                  <Box sx={{ flexGrow: 1 }}>
                    <DocumentsDataGrid />
                  </Box>
                </Box>
              </Box>
            </Box>
          </Stack>
        </Paper>

        <ActionButtons
          handleBack={handleBack}
          activeStep={activeStep}
          steps={steps}
          rootProps={{ flexDirection: { md: 'row' }, margin: '1rem 0rem' }}
        />
      </form>
    </>
  );
};

export default SecondStep;
