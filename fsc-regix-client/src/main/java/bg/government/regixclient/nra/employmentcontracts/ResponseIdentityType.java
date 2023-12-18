
package bg.government.regixclient.nra.employmentcontracts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ResponseIdentityType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ResponseIdentityType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ID" type="{http://egov.bg/RegiX/NRA/EmploymentContracts}IDType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TYPE" type="{http://egov.bg/RegiX/NRA/EmploymentContracts}EikTypeType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseIdentityType", propOrder = {
    "id",
    "type"
})
public class ResponseIdentityType {

    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "TYPE")
    @XmlSchemaType(name = "string")
    protected EikTypeType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EikTypeType getType() {
        return type;
    }

    public void setType(EikTypeType type) {
        this.type = type;
    }
}
