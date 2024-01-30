using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class AttachmentFileType
    {
        public string AttFileName { get; set; }
        [XmlElement(DataType = "base64Binary")]
        public byte[] AttBody { get; set; }
        public string AttComment { get; set; }
        public string AttMIMEType { get; set; }
    }
}
