import { useRouter } from 'next/router';
import { useAppSelector } from '../../../../../app/hooks';
import { ROUTES } from '../../../../../constants';
import { useGetObligationsPaymentIdAndAccessCodeMutation } from '../../../../../store/api/obligationsSlice';
import { IUser, userSelector } from '../../../../../store/user';
import { handleError } from '../../../../handlers/errorHandlers';

const useInitObligationsPayment = () => {
  const router = useRouter();
  const { accessToken } = useAppSelector<IUser>(userSelector);
  const [getObligationsPaymentIdAndAccessCode] =
    useGetObligationsPaymentIdAndAccessCodeMutation();

  const initObligationsPayment = async ({
    obligations,
    alreadyCreatedAccessCode,
  }: {
    obligations: { chargeId: number; value: number }[];
    alreadyCreatedAccessCode?: string;
  }) => {
    try {
      const { accessCode } = alreadyCreatedAccessCode
        ? { accessCode: alreadyCreatedAccessCode }
        : await getObligationsPaymentIdAndAccessCode({
            accessToken,
            obligations,
          }).unwrap();

      router.push(`${ROUTES.OBLIGATIONS.PAYMENT}?accessCode=${accessCode}`);
    } catch (error) {
      handleError(error);
    }
  };

  return [initObligationsPayment];
};

export default useInitObligationsPayment;
