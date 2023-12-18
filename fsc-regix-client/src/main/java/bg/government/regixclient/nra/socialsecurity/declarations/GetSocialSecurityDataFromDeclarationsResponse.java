
package bg.government.regixclient.nra.socialsecurity.declarations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * &lt;p&gt;Java class for anonymous complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="SocialSecurityDataFromDeclarations" minOccurs="0"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;sequence&amp;gt;
 *                   &amp;lt;element name="SocialSecurityDataFromDeclaration" type="{}SocialSecurityDataFromDeclarationsResponseType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *                 &amp;lt;/sequence&amp;gt;
 *               &amp;lt;/restriction&amp;gt;
 *             &amp;lt;/complexContent&amp;gt;
 *           &amp;lt;/complexType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "socialSecurityDataFromDeclarations"
})
@XmlRootElement(name = "GetSocialSecurityDataFromDeclarationsResponse", namespace = "")
public class GetSocialSecurityDataFromDeclarationsResponse {

    @XmlElement(name = "SocialSecurityDataFromDeclarations")
    protected SocialSecurityDataFromDeclarations socialSecurityDataFromDeclarations;

    /**
     * Gets the value of the socialSecurityDataFromDeclarations property.
     * 
     * @return
     *     possible object is
     *     {@link SocialSecurityDataFromDeclarations }
     *     
     */
    public SocialSecurityDataFromDeclarations getSocialSecurityDataFromDeclarations() {
        return socialSecurityDataFromDeclarations;
    }

    /**
     * Sets the value of the socialSecurityDataFromDeclarations property.
     * 
     * @param value
     *     allowed object is
     *     {@link SocialSecurityDataFromDeclarations }
     *     
     */
    public void setSocialSecurityDataFromDeclarations(SocialSecurityDataFromDeclarations value) {
        this.socialSecurityDataFromDeclarations = value;
    }


    /**
     * &lt;p&gt;Java class for anonymous complex type.
     * 
     * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
     * 
     * &lt;pre&gt;
     * &amp;lt;complexType&amp;gt;
     *   &amp;lt;complexContent&amp;gt;
     *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
     *       &amp;lt;sequence&amp;gt;
     *         &amp;lt;element name="SocialSecurityDataFromDeclaration" type="{}SocialSecurityDataFromDeclarationsResponseType" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
     *       &amp;lt;/sequence&amp;gt;
     *     &amp;lt;/restriction&amp;gt;
     *   &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "socialSecurityDataFromDeclaration"
    })
    public static class SocialSecurityDataFromDeclarations {

        @XmlElement(name = "SocialSecurityDataFromDeclaration")
        protected List<SocialSecurityDataFromDeclarationsResponseType> socialSecurityDataFromDeclaration;

        /**
         * Gets the value of the socialSecurityDataFromDeclaration property.
         * 
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the socialSecurityDataFromDeclaration property.
         * 
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         *    getSocialSecurityDataFromDeclaration().add(newItem);
         * &lt;/pre&gt;
         * 
         * 
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link SocialSecurityDataFromDeclarationsResponseType }
         * 
         * 
         */
        public List<SocialSecurityDataFromDeclarationsResponseType> getSocialSecurityDataFromDeclaration() {
            if (socialSecurityDataFromDeclaration == null) {
                socialSecurityDataFromDeclaration = new ArrayList<SocialSecurityDataFromDeclarationsResponseType>();
            }
            return this.socialSecurityDataFromDeclaration;
        }

    }

}
