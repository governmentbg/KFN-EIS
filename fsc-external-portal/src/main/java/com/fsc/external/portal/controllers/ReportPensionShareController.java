package com.fsc.external.portal.controllers;

import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.ReportPensionShareResponse;
import com.fsc.external.portal.mappers.ReportPensionShareMapper;
import com.fsc.external.portal.services.ReportPensionShareService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@Validated
@RestController
@RequestMapping("/rest/pension-share")
public class ReportPensionShareController {

    private final ReportPensionShareService reportPensionShareService;
    private final ReportPensionShareMapper reportPensionShareMapper;

    public ReportPensionShareController(ReportPensionShareService reportPensionShareService,
                                        ReportPensionShareMapper reportPensionShareMapper) {
        this.reportPensionShareService = reportPensionShareService;
        this.reportPensionShareMapper = reportPensionShareMapper;
    }

    @Operation(
            summary = "Returns list of report pension share.",
            description = "Returns list of report pension share by date.",
            parameters = {
                @Parameter(name = "date",
                            description = "Date for which the data will be retrieved", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - Date not in correct format yyyy-MM-dd"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                            content = @Content(schema = @Schema(name = "ErrorDefinition",
                                    type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/{date}")
    public List<ReportPensionShareResponse> getReportPensionShare(
            @PathVariable(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) throws ParseException {

        return reportPensionShareMapper.toResponse(reportPensionShareService.getReportPensionShareByDate(date));
    }
}
