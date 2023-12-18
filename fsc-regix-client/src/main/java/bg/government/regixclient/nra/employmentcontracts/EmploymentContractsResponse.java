
package bg.government.regixclient.nra.employmentcontracts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


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
 *         &amp;lt;element name="Identity" type="{http://egov.bg/RegiX/NRA/EmploymentContracts}ResponseIdentityType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EContracts" minOccurs="0"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;sequence&amp;gt;
 *                   &amp;lt;element ref="{http://egov.bg/RegiX/NRA/EmploymentContracts}EContract" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *                 &amp;lt;/sequence&amp;gt;
 *               &amp;lt;/restriction&amp;gt;
 *             &amp;lt;/complexContent&amp;gt;
 *           &amp;lt;/complexType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="Status" type="{http://egov.bg/RegiX/NRA/EmploymentContracts}StatusType" minOccurs="0"/&amp;gt;
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
    "eContracts",
    "status"
})
@XmlRootElement(name = "EmploymentContractsResponse", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Response")
public class EmploymentContractsResponse {

    @XmlElement(name = "Identity", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Response")
    protected ResponseIdentityType identity;
    @XmlElement(name = "EContracts", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Response")
    protected EContracts eContracts;
    @XmlElement(name = "Status", namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Response")
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
     * Gets the value of the eContracts property.
     * 
     * @return
     *     possible object is
     *     {@link EContracts }
     *     
     */
    public EContracts getEContracts() {
        return eContracts;
    }

    /**
     * Sets the value of the eContracts property.
     * 
     * @param value
     *     allowed object is
     *     {@link EContracts }
     *     
     */
    public void setEContracts(EContracts value) {
        this.eContracts = value;
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
     *         &amp;lt;element ref="{http://egov.bg/RegiX/NRA/EmploymentContracts}EContract" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
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
        "eContract"
    })
    public static class EContracts {

        @XmlElement(name = "EContract")
        protected List<EContract> eContract;

        /**
         * Gets the value of the eContract property.
         * 
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the eContract property.
         * 
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         *    getEContract().add(newItem);
         * &lt;/pre&gt;
         * 
         * 
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link EContract }
         * 
         * 
         */
        public List<EContract> getEContract() {
            if (eContract == null) {
                eContract = new ArrayList<EContract>();
            }
            return this.eContract;
        }

    }

}
