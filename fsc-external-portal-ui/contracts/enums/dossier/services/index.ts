export enum SERVICE_STATUSES {
  REQUESTED = 'requested',
  ACCEPTED = 'accepted',
  AWAITS_PROCESSING = 'awaitsProcessing',
  CORRECTION = 'correction',
  CORRECTED = 'corrected',
}

export enum SERVICE_ALLOWED_OPERATION {
  C = 'correction',
  R = 'requestCorrection',
}
