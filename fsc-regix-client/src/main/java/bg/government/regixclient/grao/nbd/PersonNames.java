
package bg.government.regixclient.grao.nbd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PersonNames complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PersonNames"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SurName" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FamilyName" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonNames", propOrder = {
    "firstName",
    "surName",
    "familyName"
})
public class PersonNames {

    @XmlElement(name = "FirstName")
    protected Object firstName;
    @XmlElement(name = "SurName")
    protected Object surName;
    @XmlElement(name = "FamilyName")
    protected Object familyName;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFirstName(Object value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the surName property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSurName() {
        return surName;
    }

    /**
     * Sets the value of the surName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSurName(Object value) {
        this.surName = value;
    }

    /**
     * Gets the value of the familyName property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getFamilyName() {
        return familyName;
    }

    /**
     * Sets the value of the familyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setFamilyName(Object value) {
        this.familyName = value;
    }

}
