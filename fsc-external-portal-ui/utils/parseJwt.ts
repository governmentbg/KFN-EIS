const parseJwt = (token: string) => {
  if (!token) return undefined;
  const splittedTokenArr = token?.split('.');
  const base64Payload = splittedTokenArr?.length > 1 ? splittedTokenArr[1] : '';
  const payload = Buffer.from(base64Payload, 'base64');

  return JSON.parse(payload?.toString());
};

export default parseJwt;
