import router from 'next/router';
import { GridRenderCellParams } from '@mui/x-data-grid';
import fileDownload from 'js-file-download';
import { IDocumentFileReference } from '../../../../contracts/interfaces/document';
import { AnyAction, Dispatch, ThunkDispatch } from '@reduxjs/toolkit';
import { fetchFileByFileRefPublic } from '../../../../store/file/actions';
import { handleError } from '../../../handlers/errorHandlers';
import RenderCellExpand from '../../../../views/public/PublicRegisterView/PublicRegisterDataGrid/RenderCellExpand';
import { TypographyProps } from '@mui/material/Typography';

const getRenderCellBySectionId = (
  params: GridRenderCellParams,
  key: string,
  sectionId: number,
  dispatch: ThunkDispatch<any, undefined, AnyAction> & Dispatch<AnyAction>
) => {
  const commonRenderCellExpandAdditionalParams = {
    typographyProps: {
      component: 'a',
      href: `/public-register/${router.query.id}/detailed-view/${params.row['pnlId']}/`,
    } as TypographyProps,
  };

  const getRenderCellExpandDocumentFile = (params: GridRenderCellParams) => {
    const fileReference =
      (params.row['fileRef'] as IDocumentFileReference) || {};
    const { fileName } = fileReference || {};

    if (!fileName) throw new Error(`fileName prop is missing!`);

    const fileReferenceStr = params.row['fileReferenceStr'] as string;

    if (!fileReferenceStr) throw new Error(`fileReferenceStr prop is missing!`);

    const documentId = params.row['documentId'] || {};

    if (documentId === null || documentId === undefined)
      throw new Error(`documentId prop is missing!`);

    return RenderCellExpand(params, {
      typographyProps: {
        component: 'a',
        href: 'JavaScript:void(0);',
        onClick: async () => {
          try {
            const file = await dispatch(
              fetchFileByFileRefPublic({
                fileReferenceStr,
                documentId,
              })
            ).unwrap();

            fileDownload(file, fileName);
          } catch (error) {
            handleError(error);
          }
        },
      },
    });
  };
  switch (sectionId) {
    case 140: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 150: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 160: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 170: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 180: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 190: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 200: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 210: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 230: {
      if (params.row[key] && key === 'pnlName') {
        return RenderCellExpand(params, commonRenderCellExpandAdditionalParams);
      }
    }

    case 250: {
      if (params.row[key] && key === 'documentFile') {
        return getRenderCellExpandDocumentFile(params);
      }
    }

    case 320: {
      if (params.row[key] && key === 'documentFile') {
        return getRenderCellExpandDocumentFile(params);
      }
    }

    default: {
      return RenderCellExpand(params);
    }
  }
};

export default getRenderCellBySectionId;
