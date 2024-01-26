using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels.Enums
{
    [Serializable()]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#", IncludeInSchema = false)]
    public enum ItemsChoiceType2
    {
        [XmlEnum("##any:")]
        Item,
        KeyName,
        KeyValue,
        MgmtData,
        PGPData,
        RetrievalMethod,
        SPKIData,
        X509Data,
    }
}
