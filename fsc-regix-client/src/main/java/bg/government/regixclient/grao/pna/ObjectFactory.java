
package bg.government.regixclient.grao.pna;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bg.government.regixclient.grao.pna package. 
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

    private final static QName _PermanentAddressRequest_QNAME = new QName("http://egov.bg/RegiX/GRAO/PNA/PermanentAddressRequest", "PermanentAddressRequest");
    private final static QName _PermanentAddressResponse_QNAME = new QName("http://egov.bg/RegiX/GRAO/PNA/PermanentAddressResponse", "PermanentAddressResponse");
    private final static QName _TemporaryAddressRequest_QNAME = new QName("http://egov.bg/RegiX/GRAO/PNA/TemporaryAddressRequest", "TemporaryAddressRequest");
    private final static QName _TemporaryAddressResponse_QNAME = new QName("http://egov.bg/RegiX/GRAO/PNA/TemporaryAddressResponse", "TemporaryAddressResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.government.regixclient.grao.pna
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PermanentAddressRequestType }
     * 
     */
    public PermanentAddressRequestType createPermanentAddressRequestType() {
        return new PermanentAddressRequestType();
    }

    /**
     * Create an instance of {@link PermanentAddressResponseType }
     * 
     */
    public PermanentAddressResponseType createPermanentAddressResponseType() {
        return new PermanentAddressResponseType();
    }

    /**
     * Create an instance of {@link TemporaryAddressRequestType }
     * 
     */
    public TemporaryAddressRequestType createTemporaryAddressRequestType() {
        return new TemporaryAddressRequestType();
    }

    /**
     * Create an instance of {@link TemporaryAddressResponseType }
     * 
     */
    public TemporaryAddressResponseType createTemporaryAddressResponseType() {
        return new TemporaryAddressResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermanentAddressRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PermanentAddressRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/PNA/PermanentAddressRequest", name = "PermanentAddressRequest")
    public JAXBElement<PermanentAddressRequestType> createPermanentAddressRequest(PermanentAddressRequestType value) {
        return new JAXBElement<PermanentAddressRequestType>(_PermanentAddressRequest_QNAME, PermanentAddressRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PermanentAddressResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PermanentAddressResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/PNA/PermanentAddressResponse", name = "PermanentAddressResponse")
    public JAXBElement<PermanentAddressResponseType> createPermanentAddressResponse(PermanentAddressResponseType value) {
        return new JAXBElement<PermanentAddressResponseType>(_PermanentAddressResponse_QNAME, PermanentAddressResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporaryAddressRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TemporaryAddressRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/PNA/TemporaryAddressRequest", name = "TemporaryAddressRequest")
    public JAXBElement<TemporaryAddressRequestType> createTemporaryAddressRequest(TemporaryAddressRequestType value) {
        return new JAXBElement<TemporaryAddressRequestType>(_TemporaryAddressRequest_QNAME, TemporaryAddressRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemporaryAddressResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link TemporaryAddressResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/PNA/TemporaryAddressResponse", name = "TemporaryAddressResponse")
    public JAXBElement<TemporaryAddressResponseType> createTemporaryAddressResponse(TemporaryAddressResponseType value) {
        return new JAXBElement<TemporaryAddressResponseType>(_TemporaryAddressResponse_QNAME, TemporaryAddressResponseType.class, null, value);
    }

}
