
package bg.government.regixclient.nra.obligatedperson;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for ObligationStatusType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="ObligationStatusType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="ABSENT"/&amp;gt;
 *     &amp;lt;enumeration value="PRESENT"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "ObligationStatusType")
@XmlEnum
public enum ObligationStatusType {

    ABSENT,
    PRESENT;

    public String value() {
        return name();
    }

    public static ObligationStatusType fromValue(String v) {
        return valueOf(v);
    }

}
