import { useRouter } from 'next/router';
import { useEffect } from 'react';
import { useAppDispatch } from '../../../app/hooks';
import { setLoader } from '../../../store/loader';

const ObligationsPaymentPage = () => {
  const router = useRouter();
  const dispatch = useAppDispatch();
  const { accessCode } = router.query || {};

  useEffect(() => {
    const submitForm = async () => {
      await dispatch(setLoader({ active: true }));

      const chargesForm = document.getElementById('charges-payment-form');

      if (chargesForm) {
        (chargesForm as HTMLFormElement).requestSubmit();
      }

      router.back();
    };

    submitForm();
  }, [dispatch, router]);

  if (!accessCode) throw new Error('No accessCode provided!');

  return (
    <form
      action="https://pay-test.egov.bg/Home/AccessByPaymentRequestCode"
      encType="application/x-www-form-urlencoded"
      method="post"
      target="_blank"
      id="charges-payment-form"
    >
      <input
        hidden={true}
        name="code"
        value={accessCode}
        style={{ display: 'none' }}
      />
      <input
        type="submit"
        name="submit"
        title="vsus"
        value="Send"
        style={{ display: 'none' }}
        id="charges-payment-submit-button"
      />
    </form>
  );
};

export default ObligationsPaymentPage;
