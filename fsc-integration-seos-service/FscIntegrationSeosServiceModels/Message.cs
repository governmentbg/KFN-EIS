using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(AnonymousType = true, Namespace = "http://schemas.egov.bg/messaging/v1")]
    [XmlRoot(Namespace = "http://schemas.egov.bg/messaging/v1", IsNullable = false)]
    public class Message
    {
        public MessageHeader Header { get; set; }

        public MessageBody Body { get; set; }

        [XmlElement(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
        public SignatureType Signature { get; set; }
    }
}
