using FscIntegrationSeosServiceModels.Enums;
using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(AnonymousType = true, Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class MessageHeader
    {
        public string Version { get; set; }

        public MessageType MessageType { get; set; }

        public DateTime MessageDate { get; set; }

        public EntityNodeType Sender { get; set; }

        public EntityNodeType Recipient { get; set; }

        public string MessageGUID { get; set; }
    }
}
