using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://ereg.egov.bg/segment/0009-000001")]
    public class DocumentURI
    {
        [XmlElement(DataType = "integer")]
        public string RegisterIndex { get; set; }

        [XmlElement(DataType = "integer")]
        public string SequenceNumber { get; set; }

        [XmlElement(DataType = "date")]
        public DateTime ReceiptOrSigningDate { get; set; }
        [XmlIgnore()]
        public bool ReceiptOrSigningDateSpecified { get; set; }
    }
}
