import { useRouter } from 'next/router';
import { useFetchPublicRegisterPnlQuery } from '../../../../store/api/publicRegisterPnlSlice';
import { handleError } from '../../../handlers/errorHandlers';

const useGetSectionUrlBySectionId = () => {
  const router = useRouter();
  const pnlId = router.query?.pnlId?.toString() ?? ``;
  const publicRegisterId = router.query.id;

  const { data: pnlData } = useFetchPublicRegisterPnlQuery({
    pnlId,
  });

  const getSectionUrlBySectionId = (sectionId: number) => {
    try {
      switch (sectionId) {
        case 10:
          return `person/${pnlId}/${pnlData?.personId}`;
        case 20:
          return ``;
        case 30:
          return `office/30/${pnlId}`;
        case 40:
          return `capital/${pnlId}`;
        case 50:
          return `person-union-list/50/${pnlId}`;
        case 60:
          return `person-management/section/60/pnl/${pnlId}`;
        case 70:
          return ``;
        case 80:
          return ``;
        case 90:
          return ``;
        case 100:
          return ``;
        case 110:
          return ``;
        case 120:
          return ``;
        case 130:
          return ``;
        case 140:
          return `related-pnl/140/${pnlId}`;
        case 150:
          return `related-pnl/150/${pnlId}`;
        case 160:
          return `related-pnl/160/${pnlId}`;
        case 170:
          return `related-pnl/170/${pnlId}`;
        case 180:
          return `related-pnl/180/${pnlId}`;
        case 190:
          return `related-pnl/190/${pnlId}`;
        case 200:
          return `related-pnl/200/${pnlId}`;
        case 210:
          return `related-pnl/210/${pnlId}`;
        case 220:
          return ``;
        case 230:
          return `related-pnl/230/${pnlId}`;
        case 240:
          return `related-person/240/${pnlId}`;
        case 250:
          return `person-document/register/${publicRegisterId}/section/250/pnl/${pnlId}`;
        case 260:
          return `related-person/260/${pnlId}`;
        case 270:
          return `related-pnl/270/${pnlId}`;
        case 280:
          return `person-union-list/280/${pnlId}`;
        case 290:
          return ``;
        case 300:
          return ``;
        case 310:
          return ``;
        case 320:
          return `status-documents/${publicRegisterId}/320/${pnlId}`;
        case 330:
          return `insurer-companies/330/${pnlId}`;

        default:
          return ``;
      }
    } catch (error) {
      handleError(error);
    }
  };

  return getSectionUrlBySectionId;
};

export default useGetSectionUrlBySectionId;
