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
    public class DocumentStatusResponseType
    {
        public DocumentIdentificationType DocID { get; set; }

        public DocumentStatusType DocRegStatus { get; set; }

        public string RejectionReason { get; set; }

        [XmlElement(DataType = "date")]
        public DateTime DocExpectCloseDate { get; set; }

        [XmlIgnore()]
        public bool DocExpectCloseDateSpecified { get; set; }

        public AdditionalDataType DocAddData { get; set; }
    }
}
