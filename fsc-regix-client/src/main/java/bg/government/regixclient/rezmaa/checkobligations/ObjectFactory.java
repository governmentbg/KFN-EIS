
package bg.government.regixclient.rezmaa.checkobligations;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bg.government.regixclient.rezmaa.checkobligations package. 
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

    private final static QName _CheckObligationsRequest_QNAME = new QName("http://egov.bg/RegiX/AM/REZMA/CheckObligationsRequest", "CheckObligationsRequest");
    private final static QName _CheckObligationsResponse_QNAME = new QName("http://egov.bg/RegiX/AM/REZMA/CheckObligationsResponse", "CheckObligationsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.government.regixclient.rezmaa.checkobligations
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckObligationsRequestType }
     * 
     */
    public CheckObligationsRequestType createCheckObligationsRequestType() {
        return new CheckObligationsRequestType();
    }

    /**
     * Create an instance of {@link CheckObligationsResponseType }
     * 
     */
    public CheckObligationsResponseType createCheckObligationsResponseType() {
        return new CheckObligationsResponseType();
    }

    /**
     * Create an instance of {@link ObligationType }
     * 
     */
    public ObligationType createObligationType() {
        return new ObligationType();
    }

    /**
     * Create an instance of {@link ObligationsType }
     * 
     */
    public ObligationsType createObligationsType() {
        return new ObligationsType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckObligationsRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckObligationsRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/AM/REZMA/CheckObligationsRequest", name = "CheckObligationsRequest")
    public JAXBElement<CheckObligationsRequestType> createCheckObligationsRequest(CheckObligationsRequestType value) {
        return new JAXBElement<CheckObligationsRequestType>(_CheckObligationsRequest_QNAME, CheckObligationsRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckObligationsResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckObligationsResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/AM/REZMA/CheckObligationsResponse", name = "CheckObligationsResponse")
    public JAXBElement<CheckObligationsResponseType> createCheckObligationsResponse(CheckObligationsResponseType value) {
        return new JAXBElement<CheckObligationsResponseType>(_CheckObligationsResponse_QNAME, CheckObligationsResponseType.class, null, value);
    }

}
