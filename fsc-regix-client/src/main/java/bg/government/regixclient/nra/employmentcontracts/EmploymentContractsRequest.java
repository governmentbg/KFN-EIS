
package bg.government.regixclient.nra.employmentcontracts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for EmploymentContractsRequest complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="EmploymentContractsRequest"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="Identity" type="{http://egov.bg/RegiX/NRA/EmploymentContracts/Request}IdentityTypeRequest"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmploymentContractsRequest", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Request", propOrder = {
    "identity"
})
public class EmploymentContractsRequest {

    @XmlElement(name = "Identity", required = true)
    protected IdentityTypeRequest identity;

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

}
