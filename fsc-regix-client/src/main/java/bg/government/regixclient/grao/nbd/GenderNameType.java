
package bg.government.regixclient.grao.nbd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for GenderNameType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="GenderNameType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="Мъж"/&amp;gt;
 *     &amp;lt;enumeration value="Жена"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "GenderNameType")
@XmlEnum
public enum GenderNameType {

    @XmlEnumValue("\u041c\u044a\u0436")
    МЪЖ("\u041c\u044a\u0436"),
    @XmlEnumValue("\u0416\u0435\u043d\u0430")
    ЖЕНА("\u0416\u0435\u043d\u0430");
    private final String value;

    GenderNameType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static GenderNameType fromValue(String v) {
        for (GenderNameType c: GenderNameType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
