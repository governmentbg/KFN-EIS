using System.CodeDom.Compiler;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels.Enums
{
    [GeneratedCodeAttribute("xsd", "4.6.1055.0")]
    [SerializableAttribute()]
    [XmlTypeAttribute(Namespace = "http://www.w3.org/2000/09/xmldsig#", IncludeInSchema = false)]
    public enum ItemsChoiceType1
    {
        [XmlEnumAttribute("##any:")]
        Item,
        PGPKeyID,
        PGPKeyPacket,
    }
}
