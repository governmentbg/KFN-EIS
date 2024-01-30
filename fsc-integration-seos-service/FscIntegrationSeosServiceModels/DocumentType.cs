using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class DocumentType
    {
        public DocumentIdentificationType DocID { get; set; }
        public DocumentIdentificationType DocParentID { get; set; }
        public string DocKind { get; set; }
        [XmlArrayItem("Corespondent", IsNullable = false)]
        public CorrespondentType[] DocCorrespondentList { get; set; }
        [XmlArrayItem("Attachment", IsNullable = false)]
        public AttachmentFileType[] DocAttachmentList { get; set; }
        public string DocAbout { get; set; }
        public ServiceItemType DocService { get; set; }
        public string DocComment { get; set; }
        public AdditionalDataType DocAddData { get; set; }
        [XmlElement(DataType = "date")]
        public DateTime DocReqDateClose { get; set; }
        [XmlIgnore()]
        public bool DocReqDateCloseSpecified { get; set; }
        public string DocAttentionTo { get; set; }

        [XmlElement(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
        public SignatureType Signature { get; set; }
    }
}
