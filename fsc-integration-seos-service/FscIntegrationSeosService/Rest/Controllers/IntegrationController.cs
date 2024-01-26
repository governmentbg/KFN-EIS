using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Diagnostics.HealthChecks;

namespace FscIntegrationSeosService.Rest.Controllers
{
    [ApiController]
    public class IntegrationController : ControllerBase
    {
        private readonly ILogger<IntegrationController> _logger;
        private readonly HealthCheckService _healthCheckService;

        public IntegrationController(ILogger<IntegrationController> logger, HealthCheckService healthCheckService)
        {
            _logger = logger;
            _healthCheckService = healthCheckService;
        }

        [HttpGet("/Health")]
        public async Task<IActionResult> Get()
        {
            var report = await _healthCheckService.CheckHealthAsync();
            var status = Enum.GetName(typeof(HealthStatus), report.Status);
            _logger.LogInformation($"Get Health Status: {status}");
            return Ok(status);
        }

        [HttpGet("/Hello")]
        public string Hello()
        {
            return "Hello";
        }
    }
}
