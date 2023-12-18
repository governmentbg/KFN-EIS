
package bg.government.regixclient.nra.obligatedperson;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bg.government.regixclient.nra.obligatedperson package. 
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

    private final static QName _ObligationStatusType_QNAME = new QName("http://egov.bg/RegiX/NRA/Obligations", "ObligationStatusType");
    private final static QName _ObligationRequest_QNAME = new QName("http://egov.bg/RegiX/NRA/Obligations/Request", "ObligationRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.government.regixclient.nra.obligatedperson
     * 
     */
    public ObjectFactory() {
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
     * Create an instance of {@link ObligationRequest }
     * 
     */
    public ObligationRequest createObligationRequest() {
        return new ObligationRequest();
    }

    /**
     * Create an instance of {@link IdentityTypeRequest }
     * 
     */
    public IdentityTypeRequest createIdentityTypeRequest() {
        return new IdentityTypeRequest();
    }

    /**
     * Create an instance of {@link StatusTypeRequest }
     * 
     */
    public StatusTypeRequest createStatusTypeRequest() {
        return new StatusTypeRequest();
    }

    /**
     * Create an instance of {@link ObligationResponse }
     * 
     */
    public ObligationResponse createObligationResponse() {
        return new ObligationResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObligationStatusType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObligationStatusType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/Obligations", name = "ObligationStatusType")
    public JAXBElement<ObligationStatusType> createObligationStatusType(ObligationStatusType value) {
        return new JAXBElement<ObligationStatusType>(_ObligationStatusType_QNAME, ObligationStatusType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObligationRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ObligationRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/Obligations/Request", name = "ObligationRequest")
    public JAXBElement<ObligationRequest> createObligationRequest(ObligationRequest value) {
        return new JAXBElement<ObligationRequest>(_ObligationRequest_QNAME, ObligationRequest.class, null, value);
    }

}
