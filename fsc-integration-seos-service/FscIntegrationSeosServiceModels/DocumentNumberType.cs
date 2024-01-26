using System.ComponentModel;
using System.Diagnostics;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
   /// [System.CodeDom.Compiler.GeneratedCodeAttribute("xsd", "4.6.1055.0")] Should I need to add this?
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://schemas.egov.bg/messaging/v1")]
    public class DocumentNumberType
    {
        public string DocNumber { get; set; }

        [XmlElement(DataType = "date")]
        public DateTime DocDate { get; set; }
    }
}
