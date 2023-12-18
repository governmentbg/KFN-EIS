
package bg.government.regixclient.ncid.academicrecognition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Справка за академично признаване на придобито висше образование в чужбина
 * 
 * &lt;p&gt;Java class for AcademicRecognitionResponseType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AcademicRecognitionResponseType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="InternalAppNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="InternalAppDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AcademicRecognitionStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RecognitionRefusal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IdentificatorType" type="{http://egov.bg/RegiX/NACID/PDVO/AcademicRecognitionResponse}IdentificatorType" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="IdentificatorTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Identificator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RecognizedEduLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RecognizedProfQualName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UniversityOriginalName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="UniversityNameCyrillic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CountryNameCyrillic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SettlementAbroadName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="AddressDescriptionAbroad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CertificateNums" type="{http://egov.bg/RegiX/NACID/PDVO/AcademicRecognitionResponse}CertificateNumbers" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="RecognizedSpecialities" type="{http://egov.bg/RegiX/NACID/PDVO/AcademicRecognitionResponse}RecognizedSpecialities" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcademicRecognitionResponseType", propOrder = {
    "internalAppNum",
    "internalAppDate",
    "academicRecognitionStatus",
    "recognitionRefusal",
    "firstName",
    "middleName",
    "lastName",
    "identificatorType",
    "identificatorTypeName",
    "identificator",
    "recognizedEduLevel",
    "recognizedProfQualName",
    "universityOriginalName",
    "universityNameCyrillic",
    "countryNameCyrillic",
    "settlementAbroadName",
    "addressDescriptionAbroad",
    "certificateNums",
    "recognizedSpecialities"
})
public class AcademicRecognitionResponseType {

    @XmlElement(name = "InternalAppNum")
    protected String internalAppNum;
    @XmlElement(name = "InternalAppDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar internalAppDate;
    @XmlElement(name = "AcademicRecognitionStatus")
    protected String academicRecognitionStatus;
    @XmlElement(name = "RecognitionRefusal")
    protected String recognitionRefusal;
    @XmlElement(name = "FirstName")
    protected String firstName;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "LastName")
    protected String lastName;
    @XmlElement(name = "IdentificatorType")
    protected Integer identificatorType;
    @XmlElement(name = "IdentificatorTypeName")
    protected String identificatorTypeName;
    @XmlElement(name = "Identificator")
    protected String identificator;
    @XmlElement(name = "RecognizedEduLevel")
    protected String recognizedEduLevel;
    @XmlElement(name = "RecognizedProfQualName")
    protected String recognizedProfQualName;
    @XmlElement(name = "UniversityOriginalName")
    protected String universityOriginalName;
    @XmlElement(name = "UniversityNameCyrillic")
    protected String universityNameCyrillic;
    @XmlElement(name = "CountryNameCyrillic")
    protected String countryNameCyrillic;
    @XmlElement(name = "SettlementAbroadName")
    protected String settlementAbroadName;
    @XmlElement(name = "AddressDescriptionAbroad")
    protected String addressDescriptionAbroad;
    @XmlElement(name = "CertificateNums")
    protected CertificateNumbers certificateNums;
    @XmlElement(name = "RecognizedSpecialities")
    protected RecognizedSpecialities recognizedSpecialities;

    /**
     * Gets the value of the internalAppNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalAppNum() {
        return internalAppNum;
    }

    /**
     * Sets the value of the internalAppNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalAppNum(String value) {
        this.internalAppNum = value;
    }

    /**
     * Gets the value of the internalAppDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInternalAppDate() {
        return internalAppDate;
    }

    /**
     * Sets the value of the internalAppDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInternalAppDate(XMLGregorianCalendar value) {
        this.internalAppDate = value;
    }

    /**
     * Gets the value of the academicRecognitionStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcademicRecognitionStatus() {
        return academicRecognitionStatus;
    }

    /**
     * Sets the value of the academicRecognitionStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcademicRecognitionStatus(String value) {
        this.academicRecognitionStatus = value;
    }

    /**
     * Gets the value of the recognitionRefusal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecognitionRefusal() {
        return recognitionRefusal;
    }

    /**
     * Sets the value of the recognitionRefusal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecognitionRefusal(String value) {
        this.recognitionRefusal = value;
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
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the identificatorType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIdentificatorType() {
        return identificatorType;
    }

    /**
     * Sets the value of the identificatorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIdentificatorType(Integer value) {
        this.identificatorType = value;
    }

    /**
     * Gets the value of the identificatorTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificatorTypeName() {
        return identificatorTypeName;
    }

    /**
     * Sets the value of the identificatorTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificatorTypeName(String value) {
        this.identificatorTypeName = value;
    }

    /**
     * Gets the value of the identificator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificator() {
        return identificator;
    }

    /**
     * Sets the value of the identificator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificator(String value) {
        this.identificator = value;
    }

    /**
     * Gets the value of the recognizedEduLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecognizedEduLevel() {
        return recognizedEduLevel;
    }

    /**
     * Sets the value of the recognizedEduLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecognizedEduLevel(String value) {
        this.recognizedEduLevel = value;
    }

    /**
     * Gets the value of the recognizedProfQualName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecognizedProfQualName() {
        return recognizedProfQualName;
    }

    /**
     * Sets the value of the recognizedProfQualName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecognizedProfQualName(String value) {
        this.recognizedProfQualName = value;
    }

    /**
     * Gets the value of the universityOriginalName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniversityOriginalName() {
        return universityOriginalName;
    }

    /**
     * Sets the value of the universityOriginalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniversityOriginalName(String value) {
        this.universityOriginalName = value;
    }

    /**
     * Gets the value of the universityNameCyrillic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniversityNameCyrillic() {
        return universityNameCyrillic;
    }

    /**
     * Sets the value of the universityNameCyrillic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniversityNameCyrillic(String value) {
        this.universityNameCyrillic = value;
    }

    /**
     * Gets the value of the countryNameCyrillic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryNameCyrillic() {
        return countryNameCyrillic;
    }

    /**
     * Sets the value of the countryNameCyrillic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryNameCyrillic(String value) {
        this.countryNameCyrillic = value;
    }

    /**
     * Gets the value of the settlementAbroadName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementAbroadName() {
        return settlementAbroadName;
    }

    /**
     * Sets the value of the settlementAbroadName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementAbroadName(String value) {
        this.settlementAbroadName = value;
    }

    /**
     * Gets the value of the addressDescriptionAbroad property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressDescriptionAbroad() {
        return addressDescriptionAbroad;
    }

    /**
     * Sets the value of the addressDescriptionAbroad property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressDescriptionAbroad(String value) {
        this.addressDescriptionAbroad = value;
    }

    /**
     * Gets the value of the certificateNums property.
     * 
     * @return
     *     possible object is
     *     {@link CertificateNumbers }
     *     
     */
    public CertificateNumbers getCertificateNums() {
        return certificateNums;
    }

    /**
     * Sets the value of the certificateNums property.
     * 
     * @param value
     *     allowed object is
     *     {@link CertificateNumbers }
     *     
     */
    public void setCertificateNums(CertificateNumbers value) {
        this.certificateNums = value;
    }

    /**
     * Gets the value of the recognizedSpecialities property.
     * 
     * @return
     *     possible object is
     *     {@link RecognizedSpecialities }
     *     
     */
    public RecognizedSpecialities getRecognizedSpecialities() {
        return recognizedSpecialities;
    }

    /**
     * Sets the value of the recognizedSpecialities property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecognizedSpecialities }
     *     
     */
    public void setRecognizedSpecialities(RecognizedSpecialities value) {
        this.recognizedSpecialities = value;
    }

}
