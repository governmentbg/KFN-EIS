﻿using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
    [XmlRoot("DSAKeyValue", Namespace = "http://www.w3.org/2000/09/xmldsig#", IsNullable = false)]
    public class DSAKeyValueType
    {
        [XmlElement(DataType = "base64Binary")]
        public byte[] P { get; set; }

        [XmlElement(DataType = "base64Binary")]
        public byte[] Q { get; set; }

        [XmlElement(DataType = "base64Binary")]
        public byte[] G { get; set; }

        [XmlElement(DataType = "base64Binary")]
        public byte[] Y { get; set; }

        [XmlElement(DataType = "base64Binary")]
        public byte[] J { get; set; }

        [XmlElement(DataType = "base64Binary")]
        public byte[] Seed { get; set; }

        [XmlElement(DataType = "base64Binary")]
        public byte[] PgenCounter { get; set; }
    }
}
