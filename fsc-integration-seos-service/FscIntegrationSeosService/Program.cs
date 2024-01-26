using CoreWCF.Configuration;
using CoreWCF.Description;
using CoreWCF;
using Microsoft.Extensions.DependencyInjection.Extensions;
using Microsoft.Extensions.Http;
using FscIntegrationSeosService.Soap.Services;
using FscIntegrationSeosService.Soap.Interfaces;

var builder = WebApplication.CreateBuilder(args);
builder.WebHost.ConfigureKestrel((context, options) =>
{
    options.AllowSynchronousIO = true;
});

builder.Services.AddServiceModelServices().AddServiceModelMetadata();
builder.Services.AddSingleton<IServiceBehavior, UseRequestHeadersForMetadataAddressBehavior>();

builder.Services.RemoveAll<IHttpMessageHandlerBuilderFilter>();
builder.Services.AddHealthChecks();
builder.Services.AddControllers();

var app = builder.Build();

app.MapControllers();

var seosHttpBinding = new WSHttpBinding(SecurityMode.Transport);
seosHttpBinding.Security.Transport.ClientCredentialType = HttpClientCredentialType.None;

app.UseServiceModel(builder =>
{
    builder.AddService<SeosService>((serviceOptions) => { })
    .AddServiceEndpoint<SeosService, ISeosService>(seosHttpBinding, "/SeosService/Submit");
});

var serviceMetadataBehavior = app.Services.GetRequiredService<ServiceMetadataBehavior>();
serviceMetadataBehavior.HttpGetEnabled = true;

app.Run();
