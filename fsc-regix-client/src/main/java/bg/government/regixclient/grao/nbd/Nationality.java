
package bg.government.regixclient.grao.nbd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for Nationality complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="Nationality"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="NationalityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NationalityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NationalityCode2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="NationalityName2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Nationality", propOrder = {
    "nationalityCode",
    "nationalityName",
    "nationalityCode2",
    "nationalityName2"
})
public class Nationality {

    @XmlElement(name = "NationalityCode")
    protected String nationalityCode;
    @XmlElement(name = "NationalityName")
    protected String nationalityName;
    @XmlElement(name = "NationalityCode2")
    protected String nationalityCode2;
    @XmlElement(name = "NationalityName2")
    protected String nationalityName2;

    /**
     * Gets the value of the nationalityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalityCode() {
        return nationalityCode;
    }

    /**
     * Sets the value of the nationalityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalityCode(String value) {
        this.nationalityCode = value;
    }

    /**
     * Gets the value of the nationalityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalityName() {
        return nationalityName;
    }

    /**
     * Sets the value of the nationalityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalityName(String value) {
        this.nationalityName = value;
    }

    /**
     * Gets the value of the nationalityCode2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalityCode2() {
        return nationalityCode2;
    }

    /**
     * Sets the value of the nationalityCode2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalityCode2(String value) {
        this.nationalityCode2 = value;
    }

    /**
     * Gets the value of the nationalityName2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationalityName2() {
        return nationalityName2;
    }

    /**
     * Sets the value of the nationalityName2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationalityName2(String value) {
        this.nationalityName2 = value;
    }

}
