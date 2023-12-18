
package bg.government.regixclient.nra.employmentcontracts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


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
 *         &amp;lt;element name="ContractorBulstat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ContractorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IndividualEIK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IndividualNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LastAmendDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Reason" type="{http://egov.bg/RegiX/NRA/EmploymentContracts}EContractReasonType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TimeLimit" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="EcoCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ProfessionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Remuneration" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
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
    "contractorBulstat",
    "contractorName",
    "individualEIK",
    "individualNames",
    "startDate",
    "lastAmendDate",
    "endDate",
    "reason",
    "timeLimit",
    "ecoCode",
    "professionCode",
    "remuneration"
})
@XmlRootElement(name = "EContract")
public class EContract {

    @XmlElement(name = "ContractorBulstat")
    protected String contractorBulstat;
    @XmlElement(name = "ContractorName")
    protected String contractorName;
    @XmlElement(name = "IndividualEIK")
    protected String individualEIK;
    @XmlElement(name = "IndividualNames")
    protected String individualNames;
    @XmlElement(name = "StartDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "LastAmendDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar lastAmendDate;
    @XmlElement(name = "EndDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "Reason")
    protected String reason;
    @XmlElement(name = "TimeLimit")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar timeLimit;
    @XmlElement(name = "EcoCode")
    protected String ecoCode;
    @XmlElement(name = "ProfessionCode")
    protected String professionCode;
    @XmlElement(name = "Remuneration")
    protected BigDecimal remuneration;

    /**
     * Gets the value of the contractorBulstat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractorBulstat() {
        return contractorBulstat;
    }

    /**
     * Sets the value of the contractorBulstat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractorBulstat(String value) {
        this.contractorBulstat = value;
    }

    /**
     * Gets the value of the contractorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractorName() {
        return contractorName;
    }

    /**
     * Sets the value of the contractorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractorName(String value) {
        this.contractorName = value;
    }

    /**
     * Gets the value of the individualEIK property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualEIK() {
        return individualEIK;
    }

    /**
     * Sets the value of the individualEIK property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualEIK(String value) {
        this.individualEIK = value;
    }

    /**
     * Gets the value of the individualNames property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndividualNames() {
        return individualNames;
    }

    /**
     * Sets the value of the individualNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndividualNames(String value) {
        this.individualNames = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the lastAmendDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastAmendDate() {
        return lastAmendDate;
    }

    /**
     * Sets the value of the lastAmendDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastAmendDate(XMLGregorianCalendar value) {
        this.lastAmendDate = value;
    }

    /**
     * Gets the value of the endDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * Sets the value of the endDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the timeLimit property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimeLimit() {
        return timeLimit;
    }

    /**
     * Sets the value of the timeLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimeLimit(XMLGregorianCalendar value) {
        this.timeLimit = value;
    }

    /**
     * Gets the value of the ecoCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEcoCode() {
        return ecoCode;
    }

    /**
     * Sets the value of the ecoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEcoCode(String value) {
        this.ecoCode = value;
    }

    /**
     * Gets the value of the professionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfessionCode() {
        return professionCode;
    }

    /**
     * Sets the value of the professionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfessionCode(String value) {
        this.professionCode = value;
    }

    /**
     * Gets the value of the remuneration property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRemuneration() {
        return remuneration;
    }

    /**
     * Sets the value of the remuneration property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRemuneration(BigDecimal value) {
        this.remuneration = value;
    }

}
