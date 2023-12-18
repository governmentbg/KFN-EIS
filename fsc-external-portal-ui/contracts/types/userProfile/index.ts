export type UserProfileResponseType = {
  personFirstName: string;
  personMiddleName: string;
  personLastName: string;
  egn: string;
  addressResponse: { id: number; value: string }[];
  phoneResponse: { id: number; value: string }[];
  emailResponse: { id: number; value: string }[];
};
