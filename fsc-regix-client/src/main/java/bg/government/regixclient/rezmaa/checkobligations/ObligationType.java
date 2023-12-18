
package bg.government.regixclient.rezmaa.checkobligations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * &lt;p&gt;Java class for ObligationType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="ObligationType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CodeMU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Document" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DocumentNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="TypeObligation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ObligationAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PayingDocument" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PayingDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PayingAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="PayingTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Difference" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ObligationType", propOrder = {
    "codeMU",
    "mu",
    "document",
    "documentNumber",
    "creationDate",
    "typeObligation",
    "obligationAmount",
    "payingDocument",
    "payingDate",
    "payingAmount",
    "payingTotal",
    "difference",
    "status"
})
public class ObligationType {

    @XmlElement(name = "CodeMU")
    protected String codeMU;
    @XmlElement(name = "MU")
    protected String mu;
    @XmlElement(name = "Document")
    protected String document;
    @XmlElement(name = "DocumentNumber")
    protected String documentNumber;
    @XmlElement(name = "CreationDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar creationDate;
    @XmlElement(name = "TypeObligation")
    protected String typeObligation;
    @XmlElement(name = "ObligationAmount")
    protected BigDecimal obligationAmount;
    @XmlElement(name = "PayingDocument")
    protected String payingDocument;
    @XmlElement(name = "PayingDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar payingDate;
    @XmlElement(name = "PayingAmount")
    protected BigDecimal payingAmount;
    @XmlElement(name = "PayingTotal")
    protected BigDecimal payingTotal;
    @XmlElement(name = "Difference")
    protected BigDecimal difference;
    @XmlElement(name = "Status")
    protected String status;

    /**
     * Gets the value of the codeMU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodeMU() {
        return codeMU;
    }

    /**
     * Sets the value of the codeMU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodeMU(String value) {
        this.codeMU = value;
    }

    /**
     * Gets the value of the mu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMU() {
        return mu;
    }

    /**
     * Sets the value of the mu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMU(String value) {
        this.mu = value;
    }

    /**
     * Gets the value of the document property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocument() {
        return document;
    }

    /**
     * Sets the value of the document property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocument(String value) {
        this.document = value;
    }

    /**
     * Gets the value of the documentNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentNumber() {
        return documentNumber;
    }

    /**
     * Sets the value of the documentNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentNumber(String value) {
        this.documentNumber = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreationDate(XMLGregorianCalendar value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the typeObligation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeObligation() {
        return typeObligation;
    }

    /**
     * Sets the value of the typeObligation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeObligation(String value) {
        this.typeObligation = value;
    }

    /**
     * Gets the value of the obligationAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getObligationAmount() {
        return obligationAmount;
    }

    /**
     * Sets the value of the obligationAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setObligationAmount(BigDecimal value) {
        this.obligationAmount = value;
    }

    /**
     * Gets the value of the payingDocument property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayingDocument() {
        return payingDocument;
    }

    /**
     * Sets the value of the payingDocument property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayingDocument(String value) {
        this.payingDocument = value;
    }

    /**
     * Gets the value of the payingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPayingDate() {
        return payingDate;
    }

    /**
     * Sets the value of the payingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPayingDate(XMLGregorianCalendar value) {
        this.payingDate = value;
    }

    /**
     * Gets the value of the payingAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPayingAmount() {
        return payingAmount;
    }

    /**
     * Sets the value of the payingAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPayingAmount(BigDecimal value) {
        this.payingAmount = value;
    }

    /**
     * Gets the value of the payingTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPayingTotal() {
        return payingTotal;
    }

    /**
     * Sets the value of the payingTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPayingTotal(BigDecimal value) {
        this.payingTotal = value;
    }

    /**
     * Gets the value of the difference property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDifference() {
        return difference;
    }

    /**
     * Sets the value of the difference property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDifference(BigDecimal value) {
        this.difference = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

}
