package com.fsc.external.portal.services.servicerequest;

import com.fsc.common.addon.entity.PnlPerson;
import com.fsc.common.addon.entity.User;
import com.fsc.common.addon.entity.catalog.service.CatalogElement;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.enums.ServiceRequestStatus;
import com.fsc.common.addon.service.user.UserService;
import com.fsc.common.addon.util.DataConvensionUtils;
import com.fsc.external.portal.dtos.ServiceRequestRequest;
import com.fsc.external.portal.dtos.SubmitFormResponse;
import com.fsc.external.portal.services.CatalogElementService;
import com.fsc.external.portal.services.DynamicFormService;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public abstract class CommonServiceRequestCreator implements ServiceRequestCreator {

    private static final String RESULT_FIELD = "result";
    private static final String DATA_FIELD = "data";
    private static final String TEMPLATE_ID_FIELD = "templateId";
    private static final String ERROR_FIELD = "error";
    private static final String RELATED_CORR_NUMBER_KEY = "relatedCorrNumber";

    @Autowired
    protected CurrentAuthentication currentAuthentication;
    @Autowired
    protected CatalogElementService catalogElementService;
    @Autowired
    protected DynamicFormService dynamicFormService;
    @Autowired
    protected DataManager dataManager;
    @Autowired
    protected UserService userService;

    public SubmitFormResponse create(ServiceRequestRequest serviceRequestRequest) {

        deleteExistingDraft(
                serviceRequestRequest.getCatalogElementId(),
                serviceRequestRequest.getPnlId(),
                serviceRequestRequest.getPersonId());

        User user = (User) currentAuthentication.getUser();

        checkIfUserManagePnl(user, serviceRequestRequest.getPnlId(), serviceRequestRequest.getPersonId());

        Map<String, Object> validationResult = dynamicFormService.validate(
                serviceRequestRequest.getPnlId(),
                serviceRequestRequest.getPersonId(),
                serviceRequestRequest.getCatalogElementId(),
                DataConvensionUtils.convertMapToString(serviceRequestRequest.getSubmitData())
        );

        final SubmitFormResponse response = new SubmitFormResponse();

        if (!Boolean.parseBoolean(validationResult.get(RESULT_FIELD).toString())) {

            Map<String, Object> errors = (Map<String, Object>) validationResult.get(ERROR_FIELD);
            response.setErrors(errors);

            return response;
        }

        Map<String, Object> data = (Map<String, Object>) validationResult.get(DATA_FIELD);
        Long templateId = Long.valueOf(validationResult.get(TEMPLATE_ID_FIELD).toString());

        final CatalogElement catalogElement = catalogElementService.getCatalogElement(
                serviceRequestRequest.getCatalogElementId());

        ServiceRequest serviceRequest = create(catalogElement,
                templateId, DataConvensionUtils.convertMapToString(data), user,
                getPnlPerson(serviceRequestRequest.getPnlId(), serviceRequestRequest.getPersonId(), user)
        );

        response.setServiceRequestId(serviceRequest.getId());

        return response;
    }

    private ServiceRequest create(CatalogElement catalogElement, Long templateId, String submitData, User submitter,
                                  PnlPerson pnlPerson) {

        ServiceRequest serviceRequest = dataManager.create(ServiceRequest.class);
        serviceRequest.setTemplateId(templateId);
        serviceRequest.setRequestData(submitData);

        Map<String, Object> submitDataMap = DataConvensionUtils.converStringToMap(submitData);
        if (submitDataMap.containsKey(RELATED_CORR_NUMBER_KEY)) {
            serviceRequest.setRelatedCorrNumber(submitDataMap.get(RELATED_CORR_NUMBER_KEY).toString());
        }

        serviceRequest.setRequestDate(LocalDateTime.now());
        serviceRequest.setCatalogElement(catalogElement);
        serviceRequest.setCatalogueVersion(catalogElement.getCatalogVersion());
        if (userService.isCurrentUserAnonymous()) {
            submitter = null;
        }
        serviceRequest.setSubmittedBy(submitter);
        serviceRequest.setRequestType(catalogElement.getDocumentType().getId().toString());
        serviceRequest.setStatus(ServiceRequestStatus.DRAFT);

        if (!Objects.isNull(pnlPerson)) {
            serviceRequest.setPerson(pnlPerson.getMainPerson());
            serviceRequest.setPnl(pnlPerson.getPnl());
        }

        dataManager.save(serviceRequest);

        return serviceRequest;
    }

    protected abstract void deleteExistingDraft(Long catalogId, Long pnlId, Long personId);

    protected abstract void checkIfUserManagePnl(User user, Long pnlId, Long personId);

    protected abstract PnlPerson getPnlPerson(Long pnlId, Long personId, User user);
}