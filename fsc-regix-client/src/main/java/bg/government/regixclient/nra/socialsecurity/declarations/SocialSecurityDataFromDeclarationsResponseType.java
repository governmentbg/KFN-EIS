
package bg.government.regixclient.nra.socialsecurity.declarations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Java class for SocialSecurityDataFromDeclarationsResponseType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="SocialSecurityDataFromDeclarationsResponseType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="DeclarationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PersonIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PersonLastNameAndInitials" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="InsurerIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="InsurerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="InsurerAdress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Year" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PersonType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TotalInsuredDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DaysWorked" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LawEstablishedWorkHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DailyAgreedWorkHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SocialSecurityIncome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="GVRSFundFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CorrectionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SubmissionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RequestPersonIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RequestPersonIdentifierType" type="{}PersonIdentifierTypeEnum" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RequestInsurerIdentificator" minOccurs="0"&amp;gt;
 *           &amp;lt;simpleType&amp;gt;
 *             &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *               &amp;lt;maxLength value="13"/&amp;gt;
 *             &amp;lt;/restriction&amp;gt;
 *           &amp;lt;/simpleType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *         &amp;lt;element name="RequestMonthFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RequestYearFrom" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RequestMonthTo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RequestYearTo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SocialSecurityDataFromDeclarationsResponseType", namespace = "", propOrder = {
    "declarationType",
    "personIdentifier",
    "personLastNameAndInitials",
    "insurerIdentifier",
    "insurerName",
    "insurerAdress",
    "month",
    "year",
    "personType",
    "totalInsuredDays",
    "daysWorked",
    "lawEstablishedWorkHours",
    "dailyAgreedWorkHours",
    "socialSecurityIncome",
    "gvrsFundFlag",
    "correctionCode",
    "submissionDate",
    "requestPersonIdentifier",
    "requestPersonIdentifierType",
    "requestInsurerIdentificator",
    "requestMonthFrom",
    "requestYearFrom",
    "requestMonthTo",
    "requestYearTo"
})
public class SocialSecurityDataFromDeclarationsResponseType {

    @XmlElement(name = "DeclarationType")
    protected String declarationType;
    @XmlElement(name = "PersonIdentifier")
    protected String personIdentifier;
    @XmlElement(name = "PersonLastNameAndInitials")
    protected String personLastNameAndInitials;
    @XmlElement(name = "InsurerIdentifier")
    protected String insurerIdentifier;
    @XmlElement(name = "InsurerName")
    protected String insurerName;
    @XmlElement(name = "InsurerAdress")
    protected String insurerAdress;
    @XmlElement(name = "Month")
    protected String month;
    @XmlElement(name = "Year")
    protected String year;
    @XmlElement(name = "PersonType")
    protected String personType;
    @XmlElement(name = "TotalInsuredDays")
    protected String totalInsuredDays;
    @XmlElement(name = "DaysWorked")
    protected String daysWorked;
    @XmlElement(name = "LawEstablishedWorkHours")
    protected String lawEstablishedWorkHours;
    @XmlElement(name = "DailyAgreedWorkHours")
    protected String dailyAgreedWorkHours;
    @XmlElement(name = "SocialSecurityIncome")
    protected String socialSecurityIncome;
    @XmlElement(name = "GVRSFundFlag")
    protected String gvrsFundFlag;
    @XmlElement(name = "CorrectionCode")
    protected String correctionCode;
    @XmlElement(name = "SubmissionDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar submissionDate;
    @XmlElement(name = "RequestPersonIdentifier")
    protected String requestPersonIdentifier;
    @XmlElement(name = "RequestPersonIdentifierType")
    @XmlSchemaType(name = "string")
    protected PersonIdentifierTypeEnum requestPersonIdentifierType;
    @XmlElement(name = "RequestInsurerIdentificator")
    protected String requestInsurerIdentificator;
    @XmlElement(name = "RequestMonthFrom")
    protected Integer requestMonthFrom;
    @XmlElement(name = "RequestYearFrom")
    protected Integer requestYearFrom;
    @XmlElement(name = "RequestMonthTo")
    protected Integer requestMonthTo;
    @XmlElement(name = "RequestYearTo")
    protected Integer requestYearTo;

    /**
     * Gets the value of the declarationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeclarationType() {
        return declarationType;
    }

    /**
     * Sets the value of the declarationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeclarationType(String value) {
        this.declarationType = value;
    }

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
     * Gets the value of the personLastNameAndInitials property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonLastNameAndInitials() {
        return personLastNameAndInitials;
    }

    /**
     * Sets the value of the personLastNameAndInitials property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonLastNameAndInitials(String value) {
        this.personLastNameAndInitials = value;
    }

    /**
     * Gets the value of the insurerIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurerIdentifier() {
        return insurerIdentifier;
    }

    /**
     * Sets the value of the insurerIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurerIdentifier(String value) {
        this.insurerIdentifier = value;
    }

    /**
     * Gets the value of the insurerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurerName() {
        return insurerName;
    }

    /**
     * Sets the value of the insurerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurerName(String value) {
        this.insurerName = value;
    }

    /**
     * Gets the value of the insurerAdress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInsurerAdress() {
        return insurerAdress;
    }

    /**
     * Sets the value of the insurerAdress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInsurerAdress(String value) {
        this.insurerAdress = value;
    }

    /**
     * Gets the value of the month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonth(String value) {
        this.month = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(String value) {
        this.year = value;
    }

    /**
     * Gets the value of the personType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonType() {
        return personType;
    }

    /**
     * Sets the value of the personType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonType(String value) {
        this.personType = value;
    }

    /**
     * Gets the value of the totalInsuredDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalInsuredDays() {
        return totalInsuredDays;
    }

    /**
     * Sets the value of the totalInsuredDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalInsuredDays(String value) {
        this.totalInsuredDays = value;
    }

    /**
     * Gets the value of the daysWorked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDaysWorked() {
        return daysWorked;
    }

    /**
     * Sets the value of the daysWorked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaysWorked(String value) {
        this.daysWorked = value;
    }

    /**
     * Gets the value of the lawEstablishedWorkHours property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLawEstablishedWorkHours() {
        return lawEstablishedWorkHours;
    }

    /**
     * Sets the value of the lawEstablishedWorkHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLawEstablishedWorkHours(String value) {
        this.lawEstablishedWorkHours = value;
    }

    /**
     * Gets the value of the dailyAgreedWorkHours property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDailyAgreedWorkHours() {
        return dailyAgreedWorkHours;
    }

    /**
     * Sets the value of the dailyAgreedWorkHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDailyAgreedWorkHours(String value) {
        this.dailyAgreedWorkHours = value;
    }

    /**
     * Gets the value of the socialSecurityIncome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialSecurityIncome() {
        return socialSecurityIncome;
    }

    /**
     * Sets the value of the socialSecurityIncome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialSecurityIncome(String value) {
        this.socialSecurityIncome = value;
    }

    /**
     * Gets the value of the gvrsFundFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGVRSFundFlag() {
        return gvrsFundFlag;
    }

    /**
     * Sets the value of the gvrsFundFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGVRSFundFlag(String value) {
        this.gvrsFundFlag = value;
    }

    /**
     * Gets the value of the correctionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrectionCode() {
        return correctionCode;
    }

    /**
     * Sets the value of the correctionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrectionCode(String value) {
        this.correctionCode = value;
    }

    /**
     * Gets the value of the submissionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSubmissionDate() {
        return submissionDate;
    }

    /**
     * Sets the value of the submissionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSubmissionDate(XMLGregorianCalendar value) {
        this.submissionDate = value;
    }

    /**
     * Gets the value of the requestPersonIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestPersonIdentifier() {
        return requestPersonIdentifier;
    }

    /**
     * Sets the value of the requestPersonIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestPersonIdentifier(String value) {
        this.requestPersonIdentifier = value;
    }

    /**
     * Gets the value of the requestPersonIdentifierType property.
     * 
     * @return
     *     possible object is
     *     {@link PersonIdentifierTypeEnum }
     *     
     */
    public PersonIdentifierTypeEnum getRequestPersonIdentifierType() {
        return requestPersonIdentifierType;
    }

    /**
     * Sets the value of the requestPersonIdentifierType property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonIdentifierTypeEnum }
     *     
     */
    public void setRequestPersonIdentifierType(PersonIdentifierTypeEnum value) {
        this.requestPersonIdentifierType = value;
    }

    /**
     * Gets the value of the requestInsurerIdentificator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestInsurerIdentificator() {
        return requestInsurerIdentificator;
    }

    /**
     * Sets the value of the requestInsurerIdentificator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestInsurerIdentificator(String value) {
        this.requestInsurerIdentificator = value;
    }

    /**
     * Gets the value of the requestMonthFrom property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequestMonthFrom() {
        return requestMonthFrom;
    }

    /**
     * Sets the value of the requestMonthFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequestMonthFrom(Integer value) {
        this.requestMonthFrom = value;
    }

    /**
     * Gets the value of the requestYearFrom property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequestYearFrom() {
        return requestYearFrom;
    }

    /**
     * Sets the value of the requestYearFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequestYearFrom(Integer value) {
        this.requestYearFrom = value;
    }

    /**
     * Gets the value of the requestMonthTo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequestMonthTo() {
        return requestMonthTo;
    }

    /**
     * Sets the value of the requestMonthTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequestMonthTo(Integer value) {
        this.requestMonthTo = value;
    }

    /**
     * Gets the value of the requestYearTo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRequestYearTo() {
        return requestYearTo;
    }

    /**
     * Sets the value of the requestYearTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRequestYearTo(Integer value) {
        this.requestYearTo = value;
    }

}
