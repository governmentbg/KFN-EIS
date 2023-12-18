
package bg.government.regixclient.nra.socialsecurity.declarations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for EikTypeType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="EikTypeType"&amp;gt;
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
@XmlType(name = "EikTypeType")
@XmlEnum
public enum EikTypeType {

    @XmlEnumValue("Bulstat")
    BULSTAT("Bulstat"),
    EGN("EGN"),
    LNC("LNC"),
    @XmlEnumValue("SystemNo")
    SYSTEM_NO("SystemNo"),
    @XmlEnumValue("BulstatCL")
    BULSTAT_CL("BulstatCL");
    private final String value;

    EikTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static EikTypeType fromValue(String v) {
        for (EikTypeType c: EikTypeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
