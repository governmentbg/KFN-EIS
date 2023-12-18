
package bg.government.regixclient.nra.socialsecurity.declarations;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for PersonIdentifierTypeEnumeration.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="PersonIdentifierTypeEnumeration"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="NOT_SPECIFIED"/&amp;gt;
 *     &amp;lt;enumeration value="EGN"/&amp;gt;
 *     &amp;lt;enumeration value="LNCh"/&amp;gt;
 *     &amp;lt;enumeration value="NRASystemNo"/&amp;gt;
 *     &amp;lt;enumeration value="EIK_BULSTAT"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "PersonIdentifierTypeEnumeration", namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request")
@XmlEnum
public enum PersonIdentifierTypeEnumeration {

    NOT_SPECIFIED("NOT_SPECIFIED"),
    EGN("EGN"),
    @XmlEnumValue("LNCh")
    LN_CH("LNCh"),
    @XmlEnumValue("NRASystemNo")
    NRA_SYSTEM_NO("NRASystemNo"),
    EIK_BULSTAT("EIK_BULSTAT");
    private final String value;

    PersonIdentifierTypeEnumeration(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PersonIdentifierTypeEnumeration fromValue(String v) {
        for (PersonIdentifierTypeEnumeration c: PersonIdentifierTypeEnumeration.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
