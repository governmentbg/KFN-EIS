using System.ComponentModel;
using System.Diagnostics;
using System.Xml;
using System.Xml.Serialization;

namespace FscIntegrationSeosServiceModels
{
    [Serializable()]
    [DebuggerStepThrough()]
    [DesignerCategory("code")]
    [XmlType(Namespace = "http://www.w3.org/2000/09/xmldsig#")]
    [XmlRoot("SPKIData", Namespace = "http://www.w3.org/2000/09/xmldsig#", IsNullable = false)]
    public class SPKIDataType
    {
        [XmlElement("SPKISexp", DataType = "base64Binary")]
        public byte[][] SPKISexp { get; set; }

        [XmlAnyElement()]
        public XmlElement Any { get; set; }
    }
}
