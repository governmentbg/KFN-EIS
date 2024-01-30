using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class EntityNodeType
    {
        public string Id { get; set; }
        public string AdministrativeBodyName { get; set; }
        public string GUID { get; set; }
    }
}
