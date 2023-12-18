
package bg.government.regixclient.nra.obligatedperson;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Identity" type="{http://egov.bg/RegiX/NRA/Obligations}ResponseIdentityType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Threshold" type="{http://egov.bg/RegiX/NRA/Obligations}ThresholdType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ObligationStatus" type="{http://egov.bg/RegiX/NRA/Obligations}ObligationStatusType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Status" type="{http://egov.bg/RegiX/NRA/Obligations}StatusType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "identity",
    "threshold",
    "obligationStatus",
    "status"
})
@XmlRootElement(name = "ObligationResponse", namespace = "http://egov.bg/RegiX/NRA/Obligations/Response")
public class ObligationResponse {

    @XmlElement(name = "Identity", namespace = "http://egov.bg/RegiX/NRA/Obligations/Response")
    protected ResponseIdentityType identity;
    @XmlElement(name = "Threshold", namespace = "http://egov.bg/RegiX/NRA/Obligations/Response")
    @XmlSchemaType(name = "unsignedShort")
    protected Integer threshold;
    @XmlElement(name = "ObligationStatus", namespace = "http://egov.bg/RegiX/NRA/Obligations/Response")
    @XmlSchemaType(name = "string")
    protected ObligationStatusType obligationStatus;
    @XmlElement(name = "Status", namespace = "http://egov.bg/RegiX/NRA/Obligations/Response")
    protected StatusType status;

    /**
     * Gets the value of the identity property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseIdentityType }
     *     
     */
    public ResponseIdentityType getIdentity() {
        return identity;
    }

    /**
     * Sets the value of the identity property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseIdentityType }
     *     
     */
    public void setIdentity(ResponseIdentityType value) {
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

    /**
     * Gets the value of the obligationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link ObligationStatusType }
     *     
     */
    public ObligationStatusType getObligationStatus() {
        return obligationStatus;
    }

    /**
     * Sets the value of the obligationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObligationStatusType }
     *     
     */
    public void setObligationStatus(ObligationStatusType value) {
        this.obligationStatus = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link StatusType }
     *     
     */
    public StatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatusType }
     *     
     */
    public void setStatus(StatusType value) {
        this.status = value;
    }

}
