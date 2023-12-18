import { CompleatValue } from '../../../contracts/types/serviceRequest/formComponents/fields';

const checkFieldCompleatValuesPropsTypes = (
  fieldName: string,
  compleatValues: CompleatValue[]
) => {
  if (compleatValues && compleatValues?.length > 0) {
    const { key, value } = compleatValues[0];

    if (typeof key !== 'number') {
      throw new Error(
        `The field ${fieldName} has invalid options "key" property type. The type should be "number", but "${typeof key} given instead"`
      );
    }

    if (typeof value !== 'string') {
      throw new Error(
        `The field ${fieldName} has invalid options "key" property type. The type should be "string", but "${typeof key} given instead"`
      );
    }
  }
};

export default checkFieldCompleatValuesPropsTypes;
