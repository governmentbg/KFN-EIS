package com.fsc.external.portal.mappers.services;

import com.fsc.common.addon.entity.catalog.service.CatalogElementText;
import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.enums.ServiceRequestStatus;
import com.fsc.common.addon.entity.view.PnlReportView;
import com.fsc.external.portal.controllers.myservices.MyReportsStatus;
import com.fsc.external.portal.controllers.myservices.MyServicesStatus;
import org.mapstruct.Named;

import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

class BaseServicesMapper {

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("com.fsc.common.addon.messages",
                                                                           new Locale("bg"));

    @Named("mapServiceName")
    String mapServiceName(ServiceRequest serviceRequest) {
        Optional<CatalogElementText> serviceRequestNameOptional = serviceRequest.getCatalogElement()
                                                                                .getCatalogElementTexts().stream()
                                                                                .filter(e -> e.getId().getLocale()
                                                                                              .equals("BG"))
                                                                                .findFirst();
        return serviceRequestNameOptional.map(CatalogElementText::getName).orElse(null);
    }

    @Named("mapServiceStatusId")
    MyServicesStatus mapServiceStatusId(ServiceRequestStatus status) {
        switch (status) {
            case DRAFT, ERROR -> {
                return MyServicesStatus.AWAITS_PROCESSING;
            }
            case SUBMITTED -> {
                return MyServicesStatus.REQUESTED;
            }
            case PROCESSING, PROCESSED -> {
                return MyServicesStatus.ACCEPTED;
            }
            default -> {
                return null;
            }
        }
    }

    @Named("mapReportStatusId")
    MyReportsStatus mapReportStatusId(PnlReportView report) {
        switch (report.getDocumentStatus()) {
            case FOR_CORRECTION -> {
                return MyReportsStatus.CORRECTION;
            }
            case CONFIRM -> {
                return report.getVersionId() > 1 ? MyReportsStatus.CORRECTED : MyReportsStatus.ACCEPTED;
            }
            case NEW -> {
                return MyReportsStatus.AWAITS_PROCESSING;
            }
            case IN_PROGRESS -> {
                return MyReportsStatus.ACCEPTED;
            }
            default -> {
                return null;
            }
        }
    }
}
