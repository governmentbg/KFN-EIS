using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class DocumentIdentificationType
    {
        [XmlElement("DocumentNumber", typeof(DocumentNumberType))]
        [XmlElement("DocumentURI", typeof(DocumentURI))]
        public object Item { get; set; }
        public string DocumentGUID { get; set; }
    }
}