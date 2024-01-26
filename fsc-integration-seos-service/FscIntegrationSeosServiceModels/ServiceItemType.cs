using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class ServiceItemType
    {
        public string ServiceName { get; set; }
        public string ServiceType { get; set; }
        public string ServiceCode { get; set; }
    }
}
