
package bg.government.regixclient.grao.nbd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Пол
 * 
 * &lt;p&gt;Java class for Gender complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Gender"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="GenderCode" type="{http://egov.bg/RegiX/GRAO/NBD}GenderCodeType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GenderName" type="{http://egov.bg/RegiX/GRAO/NBD}GenderNameType" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Gender", propOrder = {
    "genderCode",
    "genderName"
})
public class Gender {

    @XmlElement(name = "GenderCode")
    protected Integer genderCode;
    @XmlElement(name = "GenderName")
    @XmlSchemaType(name = "string")
    protected GenderNameType genderName;

    /**
     * Gets the value of the genderCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getGenderCode() {
        return genderCode;
    }

    /**
     * Sets the value of the genderCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setGenderCode(Integer value) {
        this.genderCode = value;
    }

    /**
     * Gets the value of the genderName property.
     * 
     * @return
     *     possible object is
     *     {@link GenderNameType }
     *     
     */
    public GenderNameType getGenderName() {
        return genderName;
    }

    /**
     * Sets the value of the genderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenderNameType }
     *     
     */
    public void setGenderName(GenderNameType value) {
        this.genderName = value;
    }

}
