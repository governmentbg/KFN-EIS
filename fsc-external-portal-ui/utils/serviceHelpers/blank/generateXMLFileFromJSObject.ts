import { json2xml, Options, xml2json } from 'xml-js';

const generateXMLFileFromJSObject = (
  values: { [key: string]: any },
  fileName: string = 'report.xml',
  options: Options.JS2XML | undefined = {
    compact: true,
    spaces: 4,
    fullTagEmptyElement: true,
  }
): File => {
  const jsonValues = JSON.stringify(values);

  const outputXML = json2xml(jsonValues, options);

  const xmlStartTag = '<?xml version="1.0" encoding="UTF-8"?>';

  const xmlContentWrapperTagName = 'report'; //TODO: TO be taken from backend

  const xmlContentWrapperStartTag = `<${xmlContentWrapperTagName}>`;
  const xmlContentWrapperEndTag = `</${xmlContentWrapperTagName}>`;

  const finalXML =
    xmlStartTag +
    xmlContentWrapperStartTag +
    outputXML +
    xmlContentWrapperEndTag;


  const fileType = 'application/xml';

  const file = new File([finalXML], fileName, { type: fileType });

  return file;
};

export default generateXMLFileFromJSObject;
