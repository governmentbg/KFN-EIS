using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels.Enums
{
    [Serializable()]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public enum CorrespondentKindType
    {
        Corr_Applicant,
        Corr_Other
    }
}
