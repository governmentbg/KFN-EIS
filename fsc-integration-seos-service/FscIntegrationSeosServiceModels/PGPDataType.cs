using FscIntegrationSeosServiceModels.Enums;
using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
    [XmlRoot("PGPData", Namespace = "http://www.w3.org/2000/09/xmldsig#", IsNullable = false)]
    public class PGPDataType
    {
        [XmlAnyElement()]
        [XmlElement("PGPKeyID", typeof(byte[]), DataType = "base64Binary")]
        [XmlElement("PGPKeyPacket", typeof(byte[]), DataType = "base64Binary")]
        [XmlChoiceIdentifier("ItemsElementName")]
        public object[] Items { get; set; }

        [XmlElement("ItemsElementName")]
        [XmlIgnore()]
        public ItemsChoiceType1[] ItemsElementName { get; set; }
    }
}
