
package bg.government.regixclient.grao.nbd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bg.government.regixclient.grao.nbd package. 
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

    private final static QName _PersonDataRequest_QNAME = new QName("http://egov.bg/RegiX/GRAO/NBD/PersonDataRequest", "PersonDataRequest");
    private final static QName _PersonDataResponse_QNAME = new QName("http://egov.bg/RegiX/GRAO/NBD/PersonDataResponse", "PersonDataResponse");
    private final static QName _RelationsRequestType_QNAME = new QName("http://egov.bg/RegiX/GRAO/NBD/RelationsRequest", "RelationsRequestType");
    private final static QName _RelationsResponse_QNAME = new QName("http://egov.bg/RegiX/GRAO/NBD/RelationsResponse", "RelationsResponse");
    private final static QName _ValidPersonRequest_QNAME = new QName("http://egov.bg/RegiX/GRAO/NBD/ValidPersonRequest", "ValidPersonRequest");
    private final static QName _ValidPersonResponse_QNAME = new QName("http://egov.bg/RegiX/GRAO/NBD/ValidPersonResponse", "ValidPersonResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.government.regixclient.grao.nbd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Gender }
     * 
     */
    public Gender createGender() {
        return new Gender();
    }

    /**
     * Create an instance of {@link PersonNames }
     * 
     */
    public PersonNames createPersonNames() {
        return new PersonNames();
    }

    /**
     * Create an instance of {@link Nationality }
     * 
     */
    public Nationality createNationality() {
        return new Nationality();
    }

    /**
     * Create an instance of {@link PersonRelationType }
     * 
     */
    public PersonRelationType createPersonRelationType() {
        return new PersonRelationType();
    }

    /**
     * Create an instance of {@link PersonDataRequestType }
     * 
     */
    public PersonDataRequestType createPersonDataRequestType() {
        return new PersonDataRequestType();
    }

    /**
     * Create an instance of {@link PersonDataResponseType }
     * 
     */
    public PersonDataResponseType createPersonDataResponseType() {
        return new PersonDataResponseType();
    }

    /**
     * Create an instance of {@link RelationsRequestType }
     * 
     */
    public RelationsRequestType createRelationsRequestType() {
        return new RelationsRequestType();
    }

    /**
     * Create an instance of {@link RelationsResponseType }
     * 
     */
    public RelationsResponseType createRelationsResponseType() {
        return new RelationsResponseType();
    }

    /**
     * Create an instance of {@link ValidPersonRequestType }
     * 
     */
    public ValidPersonRequestType createValidPersonRequestType() {
        return new ValidPersonRequestType();
    }

    /**
     * Create an instance of {@link ValidPersonResponseType }
     * 
     */
    public ValidPersonResponseType createValidPersonResponseType() {
        return new ValidPersonResponseType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonDataRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PersonDataRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/NBD/PersonDataRequest", name = "PersonDataRequest")
    public JAXBElement<PersonDataRequestType> createPersonDataRequest(PersonDataRequestType value) {
        return new JAXBElement<PersonDataRequestType>(_PersonDataRequest_QNAME, PersonDataRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PersonDataResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link PersonDataResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/NBD/PersonDataResponse", name = "PersonDataResponse")
    public JAXBElement<PersonDataResponseType> createPersonDataResponse(PersonDataResponseType value) {
        return new JAXBElement<PersonDataResponseType>(_PersonDataResponse_QNAME, PersonDataResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelationsRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RelationsRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/NBD/RelationsRequest", name = "RelationsRequestType")
    public JAXBElement<RelationsRequestType> createRelationsRequestType(RelationsRequestType value) {
        return new JAXBElement<RelationsRequestType>(_RelationsRequestType_QNAME, RelationsRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RelationsResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RelationsResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/NBD/RelationsResponse", name = "RelationsResponse")
    public JAXBElement<RelationsResponseType> createRelationsResponse(RelationsResponseType value) {
        return new JAXBElement<RelationsResponseType>(_RelationsResponse_QNAME, RelationsResponseType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidPersonRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidPersonRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/NBD/ValidPersonRequest", name = "ValidPersonRequest")
    public JAXBElement<ValidPersonRequestType> createValidPersonRequest(ValidPersonRequestType value) {
        return new JAXBElement<ValidPersonRequestType>(_ValidPersonRequest_QNAME, ValidPersonRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidPersonResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ValidPersonResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/GRAO/NBD/ValidPersonResponse", name = "ValidPersonResponse")
    public JAXBElement<ValidPersonResponseType> createValidPersonResponse(ValidPersonResponseType value) {
        return new JAXBElement<ValidPersonResponseType>(_ValidPersonResponse_QNAME, ValidPersonResponseType.class, null, value);
    }

}
