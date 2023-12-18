package com.fsc.external.portal.services.servicerequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceRequestCreatorConfiguration {

    @Bean(name = ServiceRequestCreator.INTEGRATION_CREATOR)
    public ServiceRequestCreator integrationServiceRequestCreator() {
        return new IntegrationServiceRequestCreator();
    }

    @Bean(name = ServiceRequestCreator.ANONYMOUS_CREATOR)
    public ServiceRequestCreator anonymousServiceRequestCreator() {
        return new AnonymousServiceRequestCreator();
    }

    @Bean(name = ServiceRequestCreator.DEFAULT_CREATOR)
    public ServiceRequestCreator defaultServiceRequestCreator() {
        return new DefaultServiceRequestCreator();
    }
}