
package bg.government.regixclient.nra.obligatedperson;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for EikTypeTypeRequest.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="EikTypeTypeRequest"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="Bulstat"/&amp;gt;
 *     &amp;lt;enumeration value="EGN"/&amp;gt;
 *     &amp;lt;enumeration value="LNC"/&amp;gt;
 *     &amp;lt;enumeration value="SystemNo"/&amp;gt;
 *     &amp;lt;enumeration value="BulstatCL"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "EikTypeTypeRequest", namespace = "http://egov.bg/RegiX/NRA/Obligations/Request")
@XmlEnum
public enum EikTypeTypeRequest {

    @XmlEnumValue("Bulstat")
    BULSTAT("Bulstat"),
    EGN("EGN"),
    LNC("LNC"),
    @XmlEnumValue("SystemNo")
    SYSTEM_NO("SystemNo"),
    @XmlEnumValue("BulstatCL")
    BULSTAT_CL("BulstatCL");
    private final String value;

    EikTypeTypeRequest(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EikTypeTypeRequest fromValue(String v) {
        for (EikTypeTypeRequest c: EikTypeTypeRequest.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
