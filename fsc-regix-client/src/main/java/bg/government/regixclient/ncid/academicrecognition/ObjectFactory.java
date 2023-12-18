
package bg.government.regixclient.ncid.academicrecognition;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bg.government.regixclient.pdvo.academicrecognition package. 
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

    private final static QName _AcademicRecognitionRequest_QNAME = new QName("http://egov.bg/RegiX/NACID/PDVO/AcademicRecognitionRequest", "AcademicRecognitionRequest");
    private final static QName _AcademicRecognitionResponse_QNAME = new QName("http://egov.bg/RegiX/NACID/PDVO/AcademicRecognitionResponse", "AcademicRecognitionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bg.government.regixclient.pdvo.academicrecognition
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AcademicRecognitionRequestType }
     * 
     */
    public AcademicRecognitionRequestType createAcademicRecognitionRequestType() {
        return new AcademicRecognitionRequestType();
    }

    /**
     * Create an instance of {@link AcademicRecognitionResponseType }
     * 
     */
    public AcademicRecognitionResponseType createAcademicRecognitionResponseType() {
        return new AcademicRecognitionResponseType();
    }

    /**
     * Create an instance of {@link CertificateNumbers }
     * 
     */
    public CertificateNumbers createCertificateNumbers() {
        return new CertificateNumbers();
    }

    /**
     * Create an instance of {@link RecognizedSpecialities }
     * 
     */
    public RecognizedSpecialities createRecognizedSpecialities() {
        return new RecognizedSpecialities();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcademicRecognitionRequestType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AcademicRecognitionRequestType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NACID/PDVO/AcademicRecognitionRequest", name = "AcademicRecognitionRequest")
    public JAXBElement<AcademicRecognitionRequestType> createAcademicRecognitionRequest(AcademicRecognitionRequestType value) {
        return new JAXBElement<AcademicRecognitionRequestType>(_AcademicRecognitionRequest_QNAME, AcademicRecognitionRequestType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AcademicRecognitionResponseType }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link AcademicRecognitionResponseType }{@code >}
     */
    @XmlElementDecl(namespace = "http://egov.bg/RegiX/NACID/PDVO/AcademicRecognitionResponse", name = "AcademicRecognitionResponse")
    public JAXBElement<AcademicRecognitionResponseType> createAcademicRecognitionResponse(AcademicRecognitionResponseType value) {
        return new JAXBElement<AcademicRecognitionResponseType>(_AcademicRecognitionResponse_QNAME, AcademicRecognitionResponseType.class, null, value);
    }

}
