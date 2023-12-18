import { FieldProps } from 'formik';

export type ServiceRequestFormFieldComponentType =
  | 'input'
  | 'select'
  | 'datePicker'
  | 'checkbox'
  | 'checkboxGroup'
  | 'textarea'
  | 'tableGroup'
  | 'group'
  | 'radio';

export type ServiceRequestFormFieldComponentBaseProps = {
  name: string;
  title: string;
  type: ServiceRequestFormFieldComponentType;
};

export type ServiceRequestFormInputValidate =
  | 'text'
  | 'int'
  | 'double'
  | 'personName'
  | 'name'
  | 'email'
  | 'phone'
  | 'date'
  | 'dateTime'
  | 'year';

export type CompleatValue = { key: number; value: string };
export type Direction = 'row' | 'column' | 'row-reverse' | 'column-reverse';

export type ServiceRequestFormFieldComponentAutosuggestionPropType = {
  nomenclature?: string;
  autofill?: { [key: string]: string };
  fieldsToConsiderInSearch?: { fieldNames: string[] };
};

export type ComparisonOperators = '<' | '>' | '<=' | '>=' | '==' | '!=';
export type ServiceRequestActionTriggerPropType = {
  action: 'hide' | 'show' | 'change';
  elementIds?: string[]; //Mandatory for hide/show action
  fieldNames?: string[]; //Mandatory for change action
  newValue?: number | string | boolean; //Mandatory for change action
  operator: ComparisonOperators;
  value: number | string | boolean;
};

export type ServiceRequestFormFieldComponentCompleatValuesDependencyPropType = {
  fieldName: string;
  nomenclature: string;
};

export type IsRequiredFieldCondition = {
  fieldName: string;
  operator: ComparisonOperators;
  valueToCompareWith: number | string | boolean;
};
export type IsRequiredFieldConditions = IsRequiredFieldCondition[];

export type ServiceRequestFormFieldComponentProps =
  ServiceRequestFormFieldComponentBaseProps &
    Partial<{
      value: any;
      width: ' small' | 'medium' | 'large';
      isRequired: boolean;
      isRequiredFieldConditions: IsRequiredFieldConditions;
      disabled: boolean;
      compleatValues: CompleatValue[];
      multiSelect: boolean;
      inputValidate: ServiceRequestFormInputValidate;
      readOnly: boolean;
      isRepeated: boolean;
      struct: ServiceRequestFormFieldComponentProps[];
      saveEngine: string;
      values: any[];
      description: string;
      fields: ServiceRequestFormFieldComponentProps[];
      htmlTemplate: string;
      titleVisibility: 'hidden' | 'visible';
      subType: string;
      actionTriggers: ServiceRequestActionTriggerPropType[];
      /**
       * Type of the `input` element. It should be [a valid HTML5 input type](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input#Form_%3Cinput%3E_types).
       * @default 'text'
       */
      direction: Direction;
      autosuggestion: ServiceRequestFormFieldComponentAutosuggestionPropType;
      compleatValuesDependency: ServiceRequestFormFieldComponentCompleatValuesDependencyPropType;
    }>;

export type FormComponentPropsType = {
  fieldProps: FieldProps;
  componentProps: ServiceRequestFormFieldComponentProps;
};
