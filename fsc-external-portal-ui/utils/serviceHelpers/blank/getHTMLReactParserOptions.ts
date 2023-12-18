import { HTMLReactParserOptions, Element, domToReact } from 'html-react-parser';
import {
  DOM_NODE_ATTRIBS,
  DOM_NODE_COLLAPSIBLE_CONTAINER_ATTRIBS,
  DOM_NODE_DATA_COMPONENT_TYPE_ATTRIB_OPTIONS,
  DOM_NODE_DATA_CONTAINER_TYPE_ATTRIB_OPTIONS,
} from '../../../contracts/enums/serviceRequest/formComponents/containers';
import { ServiceRequestFormContainerComponentProps } from '../../../contracts/types/serviceRequest/formComponents/containers';
import { ServiceRequestFormFieldComponentProps } from '../../../contracts/types/serviceRequest/formComponents/fields';
import FormCollapsibleContainer from '../../../views/private/serviceRequest/ServiceRequest/commonComponents/formComponents/containers/FormCollapsibleContainer';
import getFieldKeyComponentPairs from './getFieldKeyComponentPairs';

const getFormContainerComponentPropsByDataContainerTypeAttrib = (
  domNode: Element
): ServiceRequestFormContainerComponentProps => {
  const { attribs, childNodes } = domNode || {};

  const { DATA_CONTAINER_TYPE } = DOM_NODE_ATTRIBS;
  const { COLLAPSIBLE } = DOM_NODE_DATA_CONTAINER_TYPE_ATTRIB_OPTIONS;
  const { DATA_CONTAINER_TITLE, DATA_CONTAINER_EXPANDED_INITIALLY } =
    DOM_NODE_COLLAPSIBLE_CONTAINER_ATTRIBS;

  switch (attribs[DATA_CONTAINER_TYPE]) {
    case COLLAPSIBLE:
      return {
        children: domToReact(childNodes),
        title: attribs[DATA_CONTAINER_TITLE] ?? '',
        expandedInitially:
          attribs[DATA_CONTAINER_EXPANDED_INITIALLY] === 'true' ? true : false,
      };
    default:
      return {};
  }
};

const getFormContainerComponentByDataContainerTypeAttrib = (
  domNode: Element
) => {
  const { attribs } = domNode || {};

  const { DATA_CONTAINER_TYPE } = DOM_NODE_ATTRIBS;
  const { COLLAPSIBLE } = DOM_NODE_DATA_CONTAINER_TYPE_ATTRIB_OPTIONS;

  switch (attribs[DATA_CONTAINER_TYPE]) {
    case COLLAPSIBLE:
      return FormCollapsibleContainer;
    default:
      return null;
  }
};

const getFormComponentByDataComponentTypeAttrib = (
  domNode: Element,
  fieldKeyFormComponentPairs?: { [key: string]: JSX.Element }
) => {
  const { attribs } = domNode || {};
  const { DATA_KEY, DATA_COMPONENT_TYPE } = DOM_NODE_ATTRIBS;
  const { CONTAINER, FIELD } = DOM_NODE_DATA_COMPONENT_TYPE_ATTRIB_OPTIONS;

  const fieldComponent =
    fieldKeyFormComponentPairs?.[attribs[DATA_KEY]] ?? null;

  switch (attribs[DATA_COMPONENT_TYPE]) {
    case CONTAINER: {
      const component =
        getFormContainerComponentByDataContainerTypeAttrib(domNode);

      if (!component) return null;

      return component(
        getFormContainerComponentPropsByDataContainerTypeAttrib(domNode)
      );
    }

    case FIELD:
      return fieldComponent;

    default:
      return fieldComponent;
  }
};

interface IDOMNode extends Element {
  attribs: {
    [name: string | Partial<DOM_NODE_ATTRIBS>]: string;
  };
}

const getHTMLReactParserOptions = (
  componentsData?: ServiceRequestFormFieldComponentProps[]
): HTMLReactParserOptions => ({
  replace: (domNode) => {
    if (
      domNode instanceof Element &&
      domNode.attribs &&
      domNode.attribs['data-type'] === 'replace'
    ) {
      return getFormComponentByDataComponentTypeAttrib(
        domNode as IDOMNode,
        getFieldKeyComponentPairs(componentsData)
      );
    }
  },
});

export default getHTMLReactParserOptions;
