
package bg.government.regixclient.nra.obligatedperson;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for IdentityTypeRequest complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="IdentityTypeRequest"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="ID" type="{http://egov.bg/RegiX/NRA/Obligations/Request}IDTypeRequest"/&amp;gt;
 *         &amp;lt;element name="TYPE" type="{http://egov.bg/RegiX/NRA/Obligations/Request}EikTypeTypeRequest"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdentityTypeRequest", namespace = "http://egov.bg/RegiX/NRA/Obligations/Request", propOrder = {
    "id",
    "type"
})
public class IdentityTypeRequest {

    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(name = "TYPE", required = true)
    @XmlSchemaType(name = "string")
    protected EikTypeTypeRequest type;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EikTypeTypeRequest }
     *     
     */
    public EikTypeTypeRequest getTYPE() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EikTypeTypeRequest }
     *     
     */
    public void setTYPE(EikTypeTypeRequest value) {
        this.type = value;
    }

}
