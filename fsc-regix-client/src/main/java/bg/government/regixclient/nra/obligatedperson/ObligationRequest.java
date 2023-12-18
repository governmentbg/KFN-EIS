
package bg.government.regixclient.nra.obligatedperson;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ObligationRequest complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ObligationRequest"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Identity" type="{http://egov.bg/RegiX/NRA/Obligations/Request}IdentityTypeRequest"/&amp;gt;
 *         &amp;lt;element name="Threshold" type="{http://egov.bg/RegiX/NRA/Obligations/Request}ThresholdTypeRequest" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObligationRequest", namespace = "http://egov.bg/RegiX/NRA/Obligations/Request", propOrder = {
    "identity",
    "threshold"
})
public class ObligationRequest {

    @XmlElement(name = "Identity", required = true)
    protected IdentityTypeRequest identity;
    @XmlElement(name = "Threshold")
    @XmlSchemaType(name = "unsignedShort")
    protected Integer threshold;

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link IdentityTypeRequest }
     *     
     */
    public IdentityTypeRequest getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentityTypeRequest }
     *     
     */
    public void setIdentity(IdentityTypeRequest value) {
        this.identity = value;
    }

    /**
     * Gets the value of the threshold property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getThreshold() {
        return threshold;
    }

    /**
     * Sets the value of the threshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setThreshold(Integer value) {
        this.threshold = value;
    }

}
