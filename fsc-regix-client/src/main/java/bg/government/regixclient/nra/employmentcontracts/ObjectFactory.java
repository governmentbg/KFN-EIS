
package bg.government.regixclient.nra.employmentcontracts;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bg.government.regixclient.rezmaa.NRAEmploymentContractsAdapter package. 
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

    private final static QName _EmploymentContractsRequest_QNAME = new QName("http://egov.bg/RegiX/NRA/EmploymentContracts/Request", "EmploymentContractsRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.government.regixclient.rezmaa.NRAEmploymentContractsAdapter
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmploymentContractsResponse }
     * 
     */
    public EmploymentContractsResponse createEmploymentContractsResponse() {
        return new EmploymentContractsResponse();
    }

    /**
     * Create an instance of {@link EContract }
     * 
     */
    public EContract createEContract() {
        return new EContract();
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
     * Create an instance of {@link EmploymentContractsRequest }
     * 
     */
    public EmploymentContractsRequest createEmploymentContractsRequest() {
        return new EmploymentContractsRequest();
    }

    /**
     * Create an instance of {@link StatusTypeRequest }
     * 
     */
    public StatusTypeRequest createStatusTypeRequest() {
        return new StatusTypeRequest();
    }

    /**
     * Create an instance of {@link IdentityTypeRequest }
     * 
     */
    public IdentityTypeRequest createIdentityTypeRequest() {
        return new IdentityTypeRequest();
    }

    /**
     * Create an instance of {@link EmploymentContractsResponse.EContracts }
     * 
     */
    public EmploymentContractsResponse.EContracts createEmploymentContractsResponseEContracts() {
        return new EmploymentContractsResponse.EContracts();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmploymentContractsRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EmploymentContractsRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NRA/EmploymentContracts/Request", name = "EmploymentContractsRequest")
    public JAXBElement<EmploymentContractsRequest> createEmploymentContractsRequest(EmploymentContractsRequest value) {
        return new JAXBElement<EmploymentContractsRequest>(_EmploymentContractsRequest_QNAME, EmploymentContractsRequest.class, null, value);
    }

}
