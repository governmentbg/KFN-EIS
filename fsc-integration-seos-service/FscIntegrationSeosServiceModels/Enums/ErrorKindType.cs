using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels.Enums
{
    [Serializable()]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public enum ErrorKindType
    {
        ERR_INTERNAL,
        ERR_EXTERNAL,
    }
}
