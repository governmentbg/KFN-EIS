﻿using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
    [XmlRoot("Transform", Namespace = "http://www.w3.org/2000/09/xmldsig#", IsNullable = false)]
    public class TransformType
    {
        [XmlAnyElement()]
        [XmlElement("XPath", typeof(string))]
        public object[] Items { get; set; }
        [XmlText()]
        public string[] Text { get; set; }
        [XmlAttribute(DataType = "anyURI")]
        public string Algorithm { get; set; }
    }
}
