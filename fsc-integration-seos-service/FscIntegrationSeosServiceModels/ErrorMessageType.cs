using FscIntegrationSeosServiceModels.Enums;
using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class ErrorMessageType
    {
        public string MessageGUID { get; set; }

        public ErrorKindType ErrorType { get; set; }

        public string ErrorDescription { get; set; }
    }
}
