
package bg.government.regixclient.grao.nbd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Java class for PersonRelationType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PersonRelationType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RelationCode" type="{http://egov.bg/RegiX/GRAO/NBD}RelationType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EGN" type="{http://egov.bg/RegiX/GRAO/NBD}EGN" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BirthDate" type="{http://egov.bg/RegiX/GRAO/NBD}BirthDate" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SurName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FamilyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Gender" type="{http://egov.bg/RegiX/GRAO/NBD}Gender" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Nationality" type="{http://egov.bg/RegiX/GRAO/NBD}Nationality" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DeathDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonRelationType", propOrder = {
    "relationCode",
    "egn",
    "birthDate",
    "firstName",
    "surName",
    "familyName",
    "gender",
    "nationality",
    "deathDate"
})
public class PersonRelationType {

    @XmlElement(name = "RelationCode")
    @XmlSchemaType(name = "string")
    protected RelationType relationCode;
    @XmlElement(name = "EGN")
    protected String egn;
    @XmlElement(name = "BirthDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;
    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "SurName")
    protected String surName;
    @XmlElement(name = "FamilyName")
    protected String familyName;
    @XmlElement(name = "Gender")
    protected Gender gender;
    @XmlElement(name = "Nationality")
    protected Nationality nationality;
    @XmlElement(name = "DeathDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deathDate;

    /**
     * Gets the value of the relationCode property.
     * 
     * @return
     *     possible object is
     *     {@link RelationType }
     *     
     */
    public RelationType getRelationCode() {
        return relationCode;
    }

    /**
     * Sets the value of the relationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link RelationType }
     *     
     */
    public void setRelationCode(RelationType value) {
        this.relationCode = value;
    }

    /**
     * Gets the value of the egn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEGN() {
        return egn;
    }

    /**
     * Sets the value of the egn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEGN(String value) {
        this.egn = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDate(XMLGregorianCalendar value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the surName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurName() {
        return surName;
    }

    /**
     * Sets the value of the surName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurName(String value) {
        this.surName = value;
    }

    /**
     * Gets the value of the familyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the value of the familyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link Gender }
     *     
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link Gender }
     *     
     */
    public void setGender(Gender value) {
        this.gender = value;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link Nationality }
     *     
     */
    public Nationality getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link Nationality }
     *     
     */
    public void setNationality(Nationality value) {
        this.nationality = value;
    }

    /**
     * Gets the value of the deathDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeathDate() {
        return deathDate;
    }

    /**
     * Sets the value of the deathDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeathDate(XMLGregorianCalendar value) {
        this.deathDate = value;
    }

}
