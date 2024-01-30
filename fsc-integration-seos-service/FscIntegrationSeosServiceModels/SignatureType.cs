using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
    [XmlRoot("Signature", Namespace = "http://www.w3.org/2000/09/xmldsig#", IsNullable = false)]
    public class SignatureType
    {
        [XmlAttribute(DataType = "ID")]
        public string Id { get; set; }
        public SignedInfoType SignedInfo { get; set; }
        public SignatureValueType SignatureValue { get; set; }
        public KeyInfoType KeyInfo { get; set; }

        [XmlElement("Object")]
        public ObjectType[] Object { get; set; }
    }
}
