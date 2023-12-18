package com.fsc.external.portal.services.servicerequest;

import com.fsc.external.portal.dtos.ServiceRequestRequest;
import com.fsc.external.portal.dtos.SubmitFormResponse;

public interface ServiceRequestCreator {

    String DEFAULT_CREATOR = "defaultServiceRequestCreator";
    String INTEGRATION_CREATOR = "integrationServiceRequestCreator";
    String ANONYMOUS_CREATOR = "anonymousServiceRequestCreator";

    SubmitFormResponse create(ServiceRequestRequest serviceRequestRequest);
}