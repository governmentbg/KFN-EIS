using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(AnonymousType = true, Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class MessageBody
    {
        [XmlElement("DocumentRegistrationRequest", typeof(DocumentRegistrationRequestType))]
        [XmlElement("DocumentStatusRequest", typeof(DocumentStatusRequestType))]
        [XmlElement("DocumentStatusResponse", typeof(DocumentStatusResponseType))]
        [XmlElement("Error", typeof(ErrorMessageType))]
        public object Item { get; set; }
    }
}
