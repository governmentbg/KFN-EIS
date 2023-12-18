
package bg.government.regixclient.grao.nbd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Java class for PersonDataResponseType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="PersonDataResponseType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="PersonNames" type="{http://egov.bg/RegiX/GRAO/NBD}PersonNames" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LatinNames" type="{http://egov.bg/RegiX/GRAO/NBD}PersonNames" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ForeignNames" type="{http://egov.bg/RegiX/GRAO/NBD}PersonNames" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Gender" type="{http://egov.bg/RegiX/GRAO/NBD}Gender" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EGN" type="{http://egov.bg/RegiX/GRAO/NBD}EGN" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BirthDate" type="{http://egov.bg/RegiX/GRAO/NBD}BirthDate" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PlaceBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
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
@XmlType(name = "PersonDataResponseType", namespace = "http://egov.bg/RegiX/GRAO/NBD/PersonDataResponse", propOrder = {
    "personNames",
    "alias",
    "latinNames",
    "foreignNames",
    "gender",
    "egn",
    "birthDate",
    "placeBirth",
    "nationality",
    "deathDate"
})
public class PersonDataResponseType {

    @XmlElement(name = "PersonNames")
    protected PersonNames personNames;
    @XmlElement(name = "Alias")
    protected String alias;
    @XmlElement(name = "LatinNames")
    protected PersonNames latinNames;
    @XmlElement(name = "ForeignNames")
    protected PersonNames foreignNames;
    @XmlElement(name = "Gender")
    protected Gender gender;
    @XmlElement(name = "EGN")
    protected String egn;
    @XmlElement(name = "BirthDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar birthDate;
    @XmlElement(name = "PlaceBirth")
    protected String placeBirth;
    @XmlElement(name = "Nationality")
    protected Nationality nationality;
    @XmlElement(name = "DeathDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar deathDate;

    /**
     * Gets the value of the personNames property.
     * 
     * @return
     *     possible object is
     *     {@link PersonNames }
     *     
     */
    public PersonNames getPersonNames() {
        return personNames;
    }

    /**
     * Sets the value of the personNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonNames }
     *     
     */
    public void setPersonNames(PersonNames value) {
        this.personNames = value;
    }

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Gets the value of the latinNames property.
     * 
     * @return
     *     possible object is
     *     {@link PersonNames }
     *     
     */
    public PersonNames getLatinNames() {
        return latinNames;
    }

    /**
     * Sets the value of the latinNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonNames }
     *     
     */
    public void setLatinNames(PersonNames value) {
        this.latinNames = value;
    }

    /**
     * Gets the value of the foreignNames property.
     * 
     * @return
     *     possible object is
     *     {@link PersonNames }
     *     
     */
    public PersonNames getForeignNames() {
        return foreignNames;
    }

    /**
     * Sets the value of the foreignNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonNames }
     *     
     */
    public void setForeignNames(PersonNames value) {
        this.foreignNames = value;
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
     * Gets the value of the egn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEgn() {
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
    public void setEgn(String value) {
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
     * Gets the value of the placeBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlaceBirth() {
        return placeBirth;
    }

    /**
     * Sets the value of the placeBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlaceBirth(String value) {
        this.placeBirth = value;
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
