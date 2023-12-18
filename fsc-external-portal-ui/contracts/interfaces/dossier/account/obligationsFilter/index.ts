export interface IObligationsFilter {
  chargeId: string | null;
  chargeType: string | null;
  chargeDate: Date | null;
  paymentDueDate: Date | null;
  status: { value: string; label: string }[] | [];
}
