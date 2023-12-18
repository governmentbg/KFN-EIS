const isDocumentDescriptionValid = (
  documentDescription: string,
  allowedDescriptionLength: number
): boolean =>
  documentDescription && documentDescription.length > allowedDescriptionLength
    ? false
    : true;

    export default isDocumentDescriptionValid