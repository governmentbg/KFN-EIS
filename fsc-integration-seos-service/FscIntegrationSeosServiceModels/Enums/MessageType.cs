using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels.Enums
{
    [Serializable()]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public enum MessageType
    {
        MSG_DocumentRegistrationRequest,
        MSG_DocumentStatusResponse,
        MSG_DocumentStatusRequest,
        MSG_Error
    }
}
