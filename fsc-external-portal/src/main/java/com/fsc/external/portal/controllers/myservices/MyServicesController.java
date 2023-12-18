package com.fsc.external.portal.controllers.myservices;

import com.fsc.common.addon.entity.catalog.service.ServiceRequest;
import com.fsc.common.addon.entity.enums.DocumentStatus;
import com.fsc.common.addon.entity.enums.ServiceRequestStatus;
import com.fsc.common.addon.entity.view.PnlReportView;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.config.PagingConfigProperties;
import com.fsc.external.portal.data.PageImpl;
import com.fsc.external.portal.dtos.PageResponse;
import com.fsc.external.portal.entity.servicerequest.ServiceRequestView;
import com.fsc.external.portal.mappers.services.MyServicesDetailedInfoMapper;
import com.fsc.external.portal.mappers.services.MyServicesMapper;
import com.fsc.external.portal.mappers.services.MyServicesReportsMapper;
import com.fsc.external.portal.services.MyServicesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import liquibase.repackaged.org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/my-services")
public class MyServicesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServicesController.class);

    private final MyServicesService myServicesService;
    private final MyServicesMapper myServicesMapper;
    private final MyServicesReportsMapper myReportsMapper;
    private final MyServicesDetailedInfoMapper myServicesDetailedInfoMapper;
    private final PagingConfigProperties pagingConfigProperties;

    public MyServicesController(
            MyServicesService myServicesService,
            MyServicesMapper myServicesMapper,
            MyServicesReportsMapper myReportsMapper,
            MyServicesDetailedInfoMapper myServicesDetailedInfoMapper,
            PagingConfigProperties pagingConfigProperties) {
        this.myServicesService = myServicesService;
        this.myServicesMapper = myServicesMapper;
        this.myReportsMapper = myReportsMapper;
        this.myServicesDetailedInfoMapper = myServicesDetailedInfoMapper;
        this.pagingConfigProperties = pagingConfigProperties;
    }

    @Operation(
        summary = "Get list of all services, excluding reports",
        description = "Returns list of services, excluding reports by personId. Page, size, and filter parameters also can be passed.",
        responses = {
            @ApiResponse(responseCode = "200", description = "Returns array of MyServices or empty array"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid request parameters sent"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - Authorization information is missing or invalid",
                content = @Content(schema = @Schema(name = "UNAUTHORIZED",
                    example = "Full authentication is required to access this resource"))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                content = @Content(schema = @Schema(name = "ErrorDefinition",
                    type = "object", implementation = ErrorDefinition.class)))
        })
    @PostMapping("/services")
    public PageResponse<MyServicesResponse> getMyServicesExcludingReports(
        @Valid @RequestBody MyServicesRequest servicesRequest, HttpServletRequest request) throws ParseException {

        final int page = servicesRequest.getPage() == null ? pagingConfigProperties.getDefaultNumberOfPages() : servicesRequest.getPage();
        final int size = servicesRequest.getSize() == null ? pagingConfigProperties.getDefaultSize() : servicesRequest.getSize();

        List<ServiceRequestStatus> statuses = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(servicesRequest.getStatus())) {
            String[] requestStatuses = servicesRequest.getStatus();
            for (String status : requestStatuses) {
                switch (status) {
                    case "AWAITS_PROCESSING" -> statuses.add(ServiceRequestStatus.ERROR);
                    case "REQUESTED" -> statuses.add(ServiceRequestStatus.SUBMITTED);
                    case "ACCEPTED" -> statuses.add(ServiceRequestStatus.PROCESSED);
                    default -> LOGGER.info(String.format("The status %s is not supported.", status));
                }
            }
        }

        //TODO когато ФЕ започнат да изпращат правилния хедър Accept-language
        final String locale = "BG"; //request.getLocale().toLanguageTag().toUpperCase();

        Page<ServiceRequestView> myServices = myServicesService.getMyServices(servicesRequest, statuses, page, size, locale);

        return myServicesMapper.toPageResponse((PageImpl<ServiceRequestView>) myServices);
    }

    @Operation(
        summary = "Returns list of all reports.",
        description = "Returns list of my reports by personId. Page, size, and filter parameters also can be passed.",
        parameters = {
            @Parameter(name = "myServicesRequest", description = "Dto which includes all parameters", required = true),
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Returns array of MyServices or empty array"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid request parameters sent"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - Authorization information is missing or invalid",
                content = @Content(schema = @Schema(name = "UNAUTHORIZED",
                    example = "Full authentication is required to access this resource"))),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                content = @Content(schema = @Schema(name = "ErrorDefinition",
                    type = "object", implementation = ErrorDefinition.class)))
        })
    @PostMapping("/reports")
    public PageResponse<MyReportsResponse> getMyReports(
        @Valid @RequestBody MyReportsRequest reportsRequest, HttpServletRequest request) throws ParseException {
        final int page = reportsRequest.getPage() == null ? pagingConfigProperties.getDefaultNumberOfPages() : reportsRequest.getPage();
        final int size = reportsRequest.getSize() == null ? pagingConfigProperties.getDefaultSize() : reportsRequest.getSize();

        List<DocumentStatus> statuses = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(reportsRequest.getStatuses())) {
            String[] requestStatuses = reportsRequest.getStatuses();
            for (String status : requestStatuses) {
                switch (status) {
                    case "AWAITS_PROCESSING" -> statuses.addAll(List.of(DocumentStatus.NEW, DocumentStatus.VALIDATE));
                    case "ACCEPTED" -> statuses.addAll(List.of(DocumentStatus.CONFIRM));
                    case "CORRECTION" -> statuses.addAll(List.of(DocumentStatus.FOR_CORRECTION));
                    //TODO За момента връщаме записите в статус Confirmed. Коментирано с Митко П.?
                    case "CORRECTED" -> statuses.addAll(List.of(DocumentStatus.CONFIRM));
                    default -> LOGGER.info(String.format("The status %s is not supported.", status));
                }
            }
        }

        //TODO когато ФЕ започнат да изпращат правилния хедър Accept-language
        final String locale = "BG"; //request.getLocale().toLanguageTag().toUpperCase();

        Page<PnlReportView> myReports = myServicesService.getMyReports(reportsRequest, statuses, page, size, locale);

        return myReportsMapper.toPageResponse((PageImpl<PnlReportView>) myReports);
    }

    @Operation(
        summary = "Returns detailed information on a specific service by service request id.",
        parameters = {
            @Parameter(name = "serviceRequestId",
                description = "Detailed info to be returned for specific service request id for "
                    +
                    "which detailed info to be fetched", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - serviceRequestId not in correct format"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                content = @Content(schema = @Schema(name = "ErrorDefinition",
                    type = "object", implementation = ErrorDefinition.class)))
        })
    @GetMapping("/services/detailed-info/{serviceRequestId}")
    public ResponseEntity<MyServicesDetailedInfoResponse> getMyServicesDetailedInfoResponseById(
        @PathVariable(value = "serviceRequestId") @Min(value = 1) Long serviceRequestId) {

        ServiceRequest serviceRequestById = myServicesService.getMyServiceDetailedInformation(serviceRequestId);

        return ResponseEntity.ok(myServicesDetailedInfoMapper.toResponse(serviceRequestById));
    }

    @GetMapping("/reports/detailed-info/{serviceRequestId}")
    public MyReportDetailedInfoResponse getMyReportsDetailedInfoResponseById(
        @PathVariable(value = "serviceRequestId") @Min(value = 1) Long serviceRequestId) {

        return myServicesService.getMyServiceReportDetailedInformation(serviceRequestId);
    }
}
