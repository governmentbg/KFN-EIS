﻿using FscIntegrationSeosServiceModels.Enums;
using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
    [XmlRoot("KeyInfo", Namespace = "http://www.w3.org/2000/09/xmldsig#", IsNullable = false)]
    public class KeyInfoType
    {
        [XmlAttribute(DataType = "ID")]
        public string Id { get; set; }
        [XmlAnyElement()]
        [XmlElement("KeyName", typeof(string))]
        [XmlElement("KeyValue", typeof(KeyValueType))]
        [XmlElement("MgmtData", typeof(string))]
        [XmlElement("PGPData", typeof(PGPDataType))]
        [XmlElement("RetrievalMethod", typeof(RetrievalMethodType))]
        [XmlElement("SPKIData", typeof(SPKIDataType))]
        [XmlElement("X509Data", typeof(X509DataType))]
        [XmlChoiceIdentifier("ItemsElementName")]
        public object[] Items { get; set; }
        [XmlElement("ItemsElementName")]
        [XmlIgnore()]
        public ItemsChoiceType2[] ItemsElementName { get; set; }
        [XmlText()]
        public string[] Text { get; set; }
    }
}
