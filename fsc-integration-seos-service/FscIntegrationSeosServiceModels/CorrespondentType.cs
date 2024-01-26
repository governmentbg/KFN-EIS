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
    public class CorrespondentType
    {
        public string CorName { get; set; }
        public string CorCity { get; set; }
        public string CorAddress { get; set; }
        public string CorEGN { get; set; }
        public string CorIDCard { get; set; }
        public string CorBULSTAT { get; set; }
        public string CorEMail { get; set; }
        public string CorPhone { get; set; }
        public string CorMobilePhone { get; set; }
        public string CorMOL { get; set; }
        public string CorComment { get; set; }
        public CorrespondentKindType CorKind { get; set; }
        [XmlIgnore()]
        public bool CorKindSpecified { get; set; }
    }
}
