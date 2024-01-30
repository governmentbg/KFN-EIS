using CoreWCF;

namespace FscIntegrationSeosService.Soap.Interfaces
{
    [ServiceContract(Namespace = "http://services.egov.bg/messaging/", ConfigurationName = "EGovEndpoint.IEGovService")]
    public interface ISeosService
    {
        [OperationContract(Action = "http://services.egov.bg/messaging/IEGovService/Submit", ReplyAction = "http://services.egov.bg/messaging/IEGovService/SubmitResponse")]
        string Submit(string request);
    }
}
