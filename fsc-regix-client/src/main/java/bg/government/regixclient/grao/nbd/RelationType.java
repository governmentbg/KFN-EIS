
package bg.government.regixclient.grao.nbd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for RelationType.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="RelationType"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="Баща"/&amp;gt;
 *     &amp;lt;enumeration value="Осиновител"/&amp;gt;
 *     &amp;lt;enumeration value="Майка"/&amp;gt;
 *     &amp;lt;enumeration value="Осиновителка"/&amp;gt;
 *     &amp;lt;enumeration value="Син"/&amp;gt;
 *     &amp;lt;enumeration value="Дъщеря"/&amp;gt;
 *     &amp;lt;enumeration value="Брат"/&amp;gt;
 *     &amp;lt;enumeration value="Сестра"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "RelationType")
@XmlEnum
public enum RelationType {

    @XmlEnumValue("\u0411\u0430\u0449\u0430")
    БАЩА("\u0411\u0430\u0449\u0430"),
    @XmlEnumValue("\u041e\u0441\u0438\u043d\u043e\u0432\u0438\u0442\u0435\u043b")
    ОСИНОВИТЕЛ("\u041e\u0441\u0438\u043d\u043e\u0432\u0438\u0442\u0435\u043b"),
    @XmlEnumValue("\u041c\u0430\u0439\u043a\u0430")
    МАЙКА("\u041c\u0430\u0439\u043a\u0430"),
    @XmlEnumValue("\u041e\u0441\u0438\u043d\u043e\u0432\u0438\u0442\u0435\u043b\u043a\u0430")
    ОСИНОВИТЕЛКА("\u041e\u0441\u0438\u043d\u043e\u0432\u0438\u0442\u0435\u043b\u043a\u0430"),
    @XmlEnumValue("\u0421\u0438\u043d")
    СИН("\u0421\u0438\u043d"),
    @XmlEnumValue("\u0414\u044a\u0449\u0435\u0440\u044f")
    ДЪЩЕРЯ("\u0414\u044a\u0449\u0435\u0440\u044f"),
    @XmlEnumValue("\u0411\u0440\u0430\u0442")
    БРАТ("\u0411\u0440\u0430\u0442"),
    @XmlEnumValue("\u0421\u0435\u0441\u0442\u0440\u0430")
    СЕСТРА("\u0421\u0435\u0441\u0442\u0440\u0430");
    private final String value;

    RelationType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RelationType fromValue(String v) {
        for (RelationType c: RelationType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
