
package bg.government.regixclient.grao.pna;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Java class for TemporaryAddressResponseType complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="TemporaryAddressResponseType"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="CountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CountryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DistrictCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="DistrictName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MunicipalityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="MunicipalityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SettlementCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="SettlementName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CityAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="CityArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LocationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="LocationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="BuildingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Entrance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Floor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="Apartment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="FromDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TemporaryAddressResponseType", namespace = "http://egov.bg/RegiX/GRAO/PNA/TemporaryAddressResponse", propOrder = {
    "countryCode",
    "countryName",
    "districtCode",
    "districtName",
    "municipalityCode",
    "municipalityName",
    "settlementCode",
    "settlementName",
    "cityAreaCode",
    "cityArea",
    "locationCode",
    "locationName",
    "buildingNumber",
    "entrance",
    "floor",
    "apartment",
    "fromDate"
})
public class TemporaryAddressResponseType {

    @XmlElement(name = "CountryCode")
    protected String countryCode;
    @XmlElement(name = "CountryName")
    protected String countryName;
    @XmlElement(name = "DistrictCode")
    protected String districtCode;
    @XmlElement(name = "DistrictName")
    protected String districtName;
    @XmlElement(name = "MunicipalityCode")
    protected String municipalityCode;
    @XmlElement(name = "MunicipalityName")
    protected String municipalityName;
    @XmlElement(name = "SettlementCode")
    protected String settlementCode;
    @XmlElement(name = "SettlementName")
    protected String settlementName;
    @XmlElement(name = "CityAreaCode")
    protected String cityAreaCode;
    @XmlElement(name = "CityArea")
    protected String cityArea;
    @XmlElement(name = "LocationCode")
    protected String locationCode;
    @XmlElement(name = "LocationName")
    protected String locationName;
    @XmlElement(name = "BuildingNumber")
    protected String buildingNumber;
    @XmlElement(name = "Entrance")
    protected String entrance;
    @XmlElement(name = "Floor")
    protected String floor;
    @XmlElement(name = "Apartment")
    protected String apartment;
    @XmlElement(name = "FromDate")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fromDate;

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the countryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Sets the value of the countryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryName(String value) {
        this.countryName = value;
    }

    /**
     * Gets the value of the districtCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * Sets the value of the districtCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictCode(String value) {
        this.districtCode = value;
    }

    /**
     * Gets the value of the districtName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * Sets the value of the districtName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistrictName(String value) {
        this.districtName = value;
    }

    /**
     * Gets the value of the municipalityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityCode() {
        return municipalityCode;
    }

    /**
     * Sets the value of the municipalityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityCode(String value) {
        this.municipalityCode = value;
    }

    /**
     * Gets the value of the municipalityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityName() {
        return municipalityName;
    }

    /**
     * Sets the value of the municipalityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityName(String value) {
        this.municipalityName = value;
    }

    /**
     * Gets the value of the settlementCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementCode() {
        return settlementCode;
    }

    /**
     * Sets the value of the settlementCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementCode(String value) {
        this.settlementCode = value;
    }

    /**
     * Gets the value of the settlementName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementName() {
        return settlementName;
    }

    /**
     * Sets the value of the settlementName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementName(String value) {
        this.settlementName = value;
    }

    /**
     * Gets the value of the cityAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityAreaCode() {
        return cityAreaCode;
    }

    /**
     * Sets the value of the cityAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityAreaCode(String value) {
        this.cityAreaCode = value;
    }

    /**
     * Gets the value of the cityArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityArea() {
        return cityArea;
    }

    /**
     * Sets the value of the cityArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityArea(String value) {
        this.cityArea = value;
    }

    /**
     * Gets the value of the locationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationCode() {
        return locationCode;
    }

    /**
     * Sets the value of the locationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationCode(String value) {
        this.locationCode = value;
    }

    /**
     * Gets the value of the locationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationName() {
        return locationName;
    }

    /**
     * Sets the value of the locationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationName(String value) {
        this.locationName = value;
    }

    /**
     * Gets the value of the buildingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildingNumber() {
        return buildingNumber;
    }

    /**
     * Sets the value of the buildingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildingNumber(String value) {
        this.buildingNumber = value;
    }

    /**
     * Gets the value of the entrance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntrance() {
        return entrance;
    }

    /**
     * Sets the value of the entrance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntrance(String value) {
        this.entrance = value;
    }

    /**
     * Gets the value of the floor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Sets the value of the floor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFloor(String value) {
        this.floor = value;
    }

    /**
     * Gets the value of the apartment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApartment() {
        return apartment;
    }

    /**
     * Sets the value of the apartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApartment(String value) {
        this.apartment = value;
    }

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFromDate(XMLGregorianCalendar value) {
        this.fromDate = value;
    }

}
