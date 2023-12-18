package bg.government.regixclient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import javax.xml.ws.soap.Addressing;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 */
@Addressing
@WebService(name = "IRegiXEntryPoint", targetNamespace = "http://tempuri.org/")
@XmlSeeAlso({
        ObjectFactory.class,
        bg.government.regixclient.grao.pna.ObjectFactory.class,
        bg.government.regixclient.grao.nbd.ObjectFactory.class,
        bg.government.regixclient.rezmaa.checkobligations.ObjectFactory.class,
        bg.government.regixclient.nra.employmentcontracts.ObjectFactory.class,
        bg.government.regixclient.nra.obligatedperson.ObjectFactory.class,
        bg.government.regixclient.nra.socialsecurity.declarations.ObjectFactory.class,
        bg.government.regixclient.ncid.academicrecognition.ObjectFactory.class
})
public interface IRegiXEntryPoint {


    /**
     * @param request
     * @return returns bg.government.regixclient.ServiceExecuteResult
     */
    @WebMethod(operationName = "Execute", action = "http://tempuri.org/IRegiXEntryPoint/Execute")
    @WebResult(name = "ExecuteResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "Execute", targetNamespace = "http://tempuri.org/", className = "bg.government.regixclient.Execute")
    @ResponseWrapper(localName = "ExecuteResponse", targetNamespace = "http://tempuri.org/", className = "bg.government.regixclient.ExecuteResponse")
    public ServiceExecuteResult execute(
            @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
                    ServiceRequestData request);

    /**
     * @param argument
     * @return returns bg.government.regixclient.ServiceResultData
     */
    @WebMethod(operationName = "CheckResult", action = "http://tempuri.org/IRegiXEntryPoint/CheckResult")
    @WebResult(name = "CheckResultResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "CheckResult", targetNamespace = "http://tempuri.org/", className = "bg.government.regixclient.CheckResult")
    @ResponseWrapper(localName = "CheckResultResponse", targetNamespace = "http://tempuri.org/", className = "bg.government.regixclient.CheckResultResponse")
    public ServiceResultData checkResult(
            @WebParam(name = "argument", targetNamespace = "http://tempuri.org/")
                    ServiceCheckResultArgument argument);

    /**
     * @param request
     * @return returns bg.government.regixclient.ServiceResultData
     */
    @WebMethod(operationName = "ExecuteSynchronous", action = "http://tempuri.org/IRegiXEntryPoint/ExecuteSynchronous")
    @WebResult(name = "ExecuteSynchronousResult", targetNamespace = "http://tempuri.org/")
    @RequestWrapper(localName = "ExecuteSynchronous", targetNamespace = "http://tempuri.org/", className = "bg.government.regixclient.ExecuteSynchronous")
    @ResponseWrapper(localName = "ExecuteSynchronousResponse", targetNamespace = "http://tempuri.org/", className = "bg.government.regixclient.ExecuteSynchronousResponse")
    public ServiceResultData executeSynchronous(
            @WebParam(name = "request", targetNamespace = "http://tempuri.org/")
                    ServiceRequestData request);

}