
package bg.government.regixclient.nra.socialsecurity.declarations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Java class for SocialSecurityDataFromDeclarationRequestType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SocialSecurityDataFromDeclarationRequestType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="PersonIdentifier" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="10"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="PersonIdentifierType" type="{http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request}PersonIdentifierTypeEnumeration" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="InsurerIdentificator" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="13"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="MonthFrom" type="{http://www.w3.org/2001/XMLSchema}gMonth"/&amp;gt;
 *         &amp;lt;element name="YearFrom" type="{http://www.w3.org/2001/XMLSchema}gYear"/&amp;gt;
 *         &amp;lt;element name="MonthTo" type="{http://www.w3.org/2001/XMLSchema}gMonth"/&amp;gt;
 *         &amp;lt;element name="YearTo" type="{http://www.w3.org/2001/XMLSchema}gYear"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SocialSecurityDataFromDeclarationRequestType", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request", propOrder = {
    "personIdentifier",
    "personIdentifierType",
    "insurerIdentificator",
    "monthFrom",
    "yearFrom",
    "monthTo",
    "yearTo"
})
public class SocialSecurityDataFromDeclarationRequestType {

    @XmlElement(name = "PersonIdentifier", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request")
    protected String personIdentifier;
    @XmlElement(name = "PersonIdentifierType", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request")
    @XmlSchemaType(name = "string")
    protected PersonIdentifierTypeEnumeration personIdentifierType;
    @XmlElement(name = "InsurerIdentificator", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request")
    protected String insurerIdentificator;
    @XmlElement(name = "MonthFrom", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request", required = true)
    @XmlSchemaType(name = "gMonth")
    protected XMLGregorianCalendar monthFrom;
    @XmlElement(name = "YearFrom", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request", required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar yearFrom;
    @XmlElement(name = "MonthTo", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request", required = true)
    @XmlSchemaType(name = "gMonth")
    protected XMLGregorianCalendar monthTo;
    @XmlElement(name = "YearTo", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request", required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar yearTo;

    /**
     * Gets the value of the personIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonIdentifier() {
        return personIdentifier;
    }

    /**
     * Sets the value of the personIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonIdentifier(String value) {
        this.personIdentifier = value;
    }

    /**
     * Gets the value of the personIdentifierType property.
     * 
     * @return
     *     possible object is
     *     {@link PersonIdentifierTypeEnumeration }
     *     
     */
    public PersonIdentifierTypeEnumeration getPersonIdentifierType() {
        return personIdentifierType;
    }

    /**
     * Sets the value of the personIdentifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonIdentifierTypeEnumeration }
     *     
     */
    public void setPersonIdentifierType(PersonIdentifierTypeEnumeration value) {
        this.personIdentifierType = value;
    }

    /**
     * Gets the value of the insurerIdentificator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurerIdentificator() {
        return insurerIdentificator;
    }

    /**
     * Sets the value of the insurerIdentificator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurerIdentificator(String value) {
        this.insurerIdentificator = value;
    }

    /**
     * Gets the value of the monthFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMonthFrom() {
        return monthFrom;
    }

    /**
     * Sets the value of the monthFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMonthFrom(XMLGregorianCalendar value) {
        this.monthFrom = value;
    }

    /**
     * Gets the value of the yearFrom property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getYearFrom() {
        return yearFrom;
    }

    /**
     * Sets the value of the yearFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setYearFrom(XMLGregorianCalendar value) {
        this.yearFrom = value;
    }

    /**
     * Gets the value of the monthTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMonthTo() {
        return monthTo;
    }

    /**
     * Sets the value of the monthTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMonthTo(XMLGregorianCalendar value) {
        this.monthTo = value;
    }

    /**
     * Gets the value of the yearTo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getYearTo() {
        return yearTo;
    }

    /**
     * Sets the value of the yearTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setYearTo(XMLGregorianCalendar value) {
        this.yearTo = value;
    }

}
