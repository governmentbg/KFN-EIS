using System.ComponentModel;
using System.Diagnostics;
using System.Xml;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
    [XmlRoot("Object", Namespace = "http://www.w3.org/2000/09/xmldsig#", IsNullable = false)]
    public class ObjectType
    {
        [XmlAttribute(DataType = "ID")]
        public string Id { get; set; }
        [XmlText()]
        [XmlAnyElement()]
        public XmlNode[] Any { get; set; }
        [XmlAttribute()]
        public string MimeType { get; set; }
        [XmlAttribute(DataType = "anyURI")]
        public string Encoding { get; set; }
    }
}
