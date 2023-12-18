
package bg.government.regixclient.nra.socialsecurity.declarations;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bg.government.regixclient.pdvo.getObligatedPersons package. 
 * &lt;p&gt;An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetSocialSecurityDataFromDeclarationsRequest_QNAME = new QName("http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request", "GetSocialSecurityDataFromDeclarationsRequest");
    private final static QName _ObligationStatusType_QNAME = new QName("http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations", "ObligationStatusType");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.government.regixclient.pdvo.getObligatedPersons
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetSocialSecurityDataFromDeclarationsResponse }
     * 
     */
    public GetSocialSecurityDataFromDeclarationsResponse createGetSocialSecurityDataFromDeclarationsResponse() {
        return new GetSocialSecurityDataFromDeclarationsResponse();
    }

    /**
     * Create an instance of {@link SocialSecurityDataFromDeclarationRequestType }
     * 
     */
    public SocialSecurityDataFromDeclarationRequestType createSocialSecurityDataFromDeclarationRequestType() {
        return new SocialSecurityDataFromDeclarationRequestType();
    }

    /**
     * Create an instance of {@link GetSocialSecurityDataFromDeclarationsResponse.SocialSecurityDataFromDeclarations }
     * 
     */
    public GetSocialSecurityDataFromDeclarationsResponse.SocialSecurityDataFromDeclarations createGetSocialSecurityDataFromDeclarationsResponseSocialSecurityDataFromDeclarations() {
        return new GetSocialSecurityDataFromDeclarationsResponse.SocialSecurityDataFromDeclarations();
    }

    /**
     * Create an instance of {@link SocialSecurityDataFromDeclarationsResponseType }
     * 
     */
    public SocialSecurityDataFromDeclarationsResponseType createSocialSecurityDataFromDeclarationsResponseType() {
        return new SocialSecurityDataFromDeclarationsResponseType();
    }

    /**
     * Create an instance of {@link IdentityType }
     * 
     */
    public IdentityType createIdentityType() {
        return new IdentityType();
    }

    /**
     * Create an instance of {@link ResponseIdentityType }
     * 
     */
    public ResponseIdentityType createResponseIdentityType() {
        return new ResponseIdentityType();
    }

    /**
     * Create an instance of {@link StatusType }
     * 
     */
    public StatusType createStatusType() {
        return new StatusType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SocialSecurityDataFromDeclarationRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SocialSecurityDataFromDeclarationRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations/Request", name = "GetSocialSecurityDataFromDeclarationsRequest")
    public JAXBElement<SocialSecurityDataFromDeclarationRequestType> createGetSocialSecurityDataFromDeclarationsRequest(SocialSecurityDataFromDeclarationRequestType value) {
        return new JAXBElement<SocialSecurityDataFromDeclarationRequestType>(_GetSocialSecurityDataFromDeclarationsRequest_QNAME, SocialSecurityDataFromDeclarationRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObligationStatusType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObligationStatusType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/SocialSecurityDataFromDeclarations", name = "ObligationStatusType")
    public JAXBElement<ObligationStatusType> createObligationStatusType(ObligationStatusType value) {
        return new JAXBElement<ObligationStatusType>(_ObligationStatusType_QNAME, ObligationStatusType.class, null, value);
    }

}
