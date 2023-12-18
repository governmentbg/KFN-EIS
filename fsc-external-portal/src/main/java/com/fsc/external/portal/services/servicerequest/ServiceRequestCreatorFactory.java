package com.fsc.external.portal.services.servicerequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestCreatorFactory {

    @Autowired
    private ApplicationContext context;

    public ServiceRequestCreator getServiceRequestCreator(String type) {
        if (ServiceRequestCreator.INTEGRATION_CREATOR.equals(type)) {
            return (IntegrationServiceRequestCreator) context.getBean(CommonServiceRequestCreator.INTEGRATION_CREATOR);
        } else if (ServiceRequestCreator.ANONYMOUS_CREATOR.equals(type)) {
            return (AnonymousServiceRequestCreator) context.getBean(CommonServiceRequestCreator.ANONYMOUS_CREATOR);
        }
        return (DefaultServiceRequestCreator) context.getBean(CommonServiceRequestCreator.DEFAULT_CREATOR);
    }
}