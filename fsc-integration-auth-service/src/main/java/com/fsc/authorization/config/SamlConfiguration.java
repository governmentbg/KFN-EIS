package com.fsc.authorization.config;

import com.fsc.authorization.util.XmlObjectUtils;
import org.opensaml.saml.saml2.core.AuthnRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.saml2.core.Saml2X509Credential;
import org.springframework.security.saml2.provider.service.metadata.OpenSamlMetadataResolver;
import org.springframework.security.saml2.provider.service.registration.InMemoryRelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistration;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrations;
import org.springframework.security.saml2.provider.service.web.DefaultRelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.RelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.Saml2MetadataFilter;
import org.springframework.security.saml2.provider.service.web.authentication.OpenSaml4AuthenticationRequestResolver;
import org.springframework.security.saml2.provider.service.web.authentication.Saml2AuthenticationRequestResolver;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;

@Configuration
public class SamlConfiguration {

    private static final String REGISTRATION_ID_EGOV = "egov";
    private static final String CERTIFICATE_TYPE = "X.509";
    private static final String CERTIFICATE_ALIAS = "egov_certificate";
    private static final String REQUESTED_PROTOCOL_NAME = "TLS";

    @Value("${egov.metadata.oid.service}")
    private String oidService;

    @Value("${egov.metadata.oid.provider}")
    private String oidProvider;

    @Value("${egov.metadata.assurence}")
    private String levelOfAssurence;

    @Value("${egov.metadata.provider-name}")
    private String providerName;

    @Value("${egov.metadata.certificate}")
    private String egovCertificate;

    @Value("${egov.metadata.url}")
    private String egovMetadataUrl;

    @Value("${eis.certificate.private}")
    private RSAPrivateKey privateKey;

    @Value("${relying.party.entity-id}")
    private String relyingPartyEntityId;

    @Value("${eis.certificate.public}")
    private String eisCertificate;

    @Bean
    public RelyingPartyRegistrationResolver relyingPartyRegistrationResolver(
            RelyingPartyRegistrationRepository registrations) {
        return new DefaultRelyingPartyRegistrationResolver(registrations);
    }

    @Bean
    public FilterRegistrationBean<Saml2MetadataFilter> metadata(RelyingPartyRegistrationResolver registrations) {

        Saml2MetadataFilter metadata = new Saml2MetadataFilter(registrations, new OpenSamlMetadataResolver());
        FilterRegistrationBean<Saml2MetadataFilter> filter = new FilterRegistrationBean<>(metadata);
        filter.setOrder(-101);

        return filter;
    }

    @Bean
    public RelyingPartyRegistrationRepository relyingPartyRegistrationRepository() throws Exception {

        RelyingPartyRegistration egov = RelyingPartyRegistrations
                                                .fromMetadata(getMetadata())
                                                .entityId(relyingPartyEntityId)
                                                .registrationId(REGISTRATION_ID_EGOV)
                                                .signingX509Credentials(
                                                        (c) -> c.add(Saml2X509Credential.signing(privateKey, relyingPartyCertificate())))
                                                .assertingPartyDetails(party -> party.wantAuthnRequestsSigned(true))
                                                .build();

        return new InMemoryRelyingPartyRegistrationRepository(egov);
    }

    private X509Certificate relyingPartyCertificate() {

        try  {
            return (X509Certificate) getCertificate(eisCertificate);
        } catch (Exception ex) {
            throw new UnsupportedOperationException(ex);
        }
    }

    @Bean
    public Saml2AuthenticationRequestResolver authenticationRequestResolver(
            RelyingPartyRegistrationRepository registrations) {

        RelyingPartyRegistrationResolver registrationResolver =
                new DefaultRelyingPartyRegistrationResolver(registrations);

        OpenSaml4AuthenticationRequestResolver authenticationRequestResolver =
                new OpenSaml4AuthenticationRequestResolver(registrationResolver);

        authenticationRequestResolver.setAuthnRequestCustomizer((context) -> {
            AuthnRequest authnRequest = context.getAuthnRequest();
            authnRequest.setProviderName(providerName);
            authnRequest.setExtensions(XmlObjectUtils.buildExtensions(oidService, oidProvider, levelOfAssurence));
        });

        return authenticationRequestResolver;
    }

    @Bean
    public DefaultCookieSerializerCustomizer cookieSerializerCustomizer() {
        return cookieSerializer -> {
            cookieSerializer.setSameSite(null);
        };
    }

    private Certificate getCertificate(String absolutePath) throws Exception {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(absolutePath);

        return CertificateFactory.getInstance(CERTIFICATE_TYPE).generateCertificate(inputStream);
    }

    private InputStream getMetadata() throws Exception {

        Certificate certificate = getCertificate(egovCertificate);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, null);
        keyStore.setCertificateEntry(CERTIFICATE_ALIAS, certificate);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        SSLContext sslContext = SSLContext.getInstance(REQUESTED_PROTOCOL_NAME);
        sslContext.init(null, trustManagerFactory.getTrustManagers(), null);

        HttpsURLConnection connection = (HttpsURLConnection) new URL(egovMetadataUrl).openConnection();
        connection.setSSLSocketFactory(sslContext.getSocketFactory());
        connection.connect();

        return connection.getInputStream();
    }
}