﻿using System.ComponentModel;
using System.Diagnostics;
using System.Xml;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
    [XmlRoot("SignatureMethod", Namespace = "http://www.w3.org/2000/09/xmldsig#", IsNullable = false)]
    public class SignatureMethodType
    {
        [XmlElement(DataType = "integer")]
        public string HMACOutputLength { get; set; }
        [XmlText()]
        [XmlAnyElement()]
        public XmlNode[] Any { get; set; }
        [XmlAttribute(DataType = "anyURI")]
        public string Algorithm { get; set; }
    }
}
