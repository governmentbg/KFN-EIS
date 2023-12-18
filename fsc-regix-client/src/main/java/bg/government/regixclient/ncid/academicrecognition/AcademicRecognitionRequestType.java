
package bg.government.regixclient.ncid.academicrecognition;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Входни параметри за справка за академично признаване на придобито висше образование в чужбина
 * 
 * &lt;p&gt;Java class for AcademicRecognitionRequestType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="AcademicRecognitionRequestType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="InternalAppNum" type="{http://www.w3.org/2001/XMLSchema}string"/&amp;gt;
 *         &amp;lt;element name="InternalAppDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AcademicRecognitionRequestType", namespace = "http://egov.bg/RegiX/NACID/PDVO/AcademicRecognitionRequest", propOrder = {
    "internalAppNum",
    "internalAppDate"
})
public class AcademicRecognitionRequestType {

    @XmlElement(name = "InternalAppNum", required = true)
    protected String internalAppNum;
    @XmlElement(name = "InternalAppDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar internalAppDate;

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

}
