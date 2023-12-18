import { CSS_CLASSES } from '../../../constants/cssClasses';
import { ServiceRequestActionTriggerPropType } from '../../../contracts/types/serviceRequest/formComponents/fields';
import compareValues from './compareValues';

const THERE_IS_NO_FIELD_NAME_SPECIFIED = 'There is no fieldName specified';

type ExecuteFieldActionsByActionTriggersPropPropsType = {
  actionTriggers?: ServiceRequestActionTriggerPropType[];
  fieldValue?: number | boolean;
  setFieldValue: (
    field: string,
    value: any,
    shouldValidate?: boolean | undefined
  ) => void;
};

const executeFieldActionsByActionTriggersProp = ({
  actionTriggers,
  fieldValue,
  setFieldValue,
}: ExecuteFieldActionsByActionTriggersPropPropsType) => {
  if (!actionTriggers) return;

  actionTriggers?.forEach(
    ({
      elementIds,
      fieldNames,
      value,
      operator,
      action,
      newValue,
    }: ServiceRequestActionTriggerPropType) => {
      const expressionResult = compareValues(fieldValue, value, operator);

      if (expressionResult) {
        switch (action) {
          case 'hide': {
            elementIds?.forEach((elementId) => {
              const element: Element | null = elementId
                ? document.getElementById(elementId)
                : null;

              if (element?.classList.contains(CSS_CLASSES.HIDDEN)) return;

              element?.classList.add(CSS_CLASSES.HIDDEN);
            });

            break;
          }

          case 'show': {
            elementIds?.forEach((elementId) => {
              const element: Element | null = elementId
                ? document.getElementById(elementId)
                : null;

              if (!element?.classList.contains(CSS_CLASSES.HIDDEN)) return;

              element?.classList.remove(CSS_CLASSES.HIDDEN);
            });

            break;
          }

          case 'change': {
            fieldNames?.forEach((fieldName) => {
              if (!fieldName) throw new Error(THERE_IS_NO_FIELD_NAME_SPECIFIED);
              setFieldValue(fieldName, newValue);
            });

            break;
          }

          default:
            break;
        }
      }
    }
  );
};

export default executeFieldActionsByActionTriggersProp;
