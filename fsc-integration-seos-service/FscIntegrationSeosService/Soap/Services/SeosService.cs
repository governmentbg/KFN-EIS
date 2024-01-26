using FscIntegrationSeosService.Soap.Interfaces;

namespace FscIntegrationSeosService.Soap.Services
{
    public class SeosService : ISeosService
    {
        private readonly ILogger<SeosService> _logger;

        public SeosService(ILogger<SeosService> logger)
        {
            _logger = logger;
        }

        public string Submit(string request)
        {
            return null;
            //var receiveHandler = new ReceiveSeosMessage();
            //return receiveHandler.Submit(request, logger);
        }
    }
}
