
package bg.government.regixclient.rezmaa.checkobligations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for StatusType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="StatusType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="има задължение"/&amp;gt;
 *     &amp;lt;enumeration value="няма задължение"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "StatusType", namespace = "http://egov.bg/RegiX/AM/REZMA/CheckObligationsResponse")
@XmlEnum
public enum StatusType {

    @XmlEnumValue("\u0438\u043c\u0430 \u0437\u0430\u0434\u044a\u043b\u0436\u0435\u043d\u0438\u0435")
    ИМА_ЗАДЪЛЖЕНИЕ("\u0438\u043c\u0430 \u0437\u0430\u0434\u044a\u043b\u0436\u0435\u043d\u0438\u0435"),
    @XmlEnumValue("\u043d\u044f\u043c\u0430 \u0437\u0430\u0434\u044a\u043b\u0436\u0435\u043d\u0438\u0435")
    НЯМА_ЗАДЪЛЖЕНИЕ("\u043d\u044f\u043c\u0430 \u0437\u0430\u0434\u044a\u043b\u0436\u0435\u043d\u0438\u0435");
    private final String value;

    StatusType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static StatusType fromValue(String v) {
        for (StatusType c: StatusType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
