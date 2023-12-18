package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.accounting.charges.Charges;
import com.fsc.common.addon.entity.accounting.other.TotalObligationsLeftView;
import com.fsc.common.addon.entity.accounting.payment.ChargesPayment;
import com.fsc.common.addon.entity.accounting.payment.enums.PaymentDistributionStatus;
import com.fsc.common.addon.entity.view.OrderedCharges;
import com.fsc.common.addon.service.accounting.AccountingService;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.EpaymentResponse;
import com.fsc.external.portal.dtos.EpaymentStatusChangedRequest;
import com.fsc.external.portal.dtos.PageResponse;
import com.fsc.external.portal.dtos.PayChargeRequest;
import com.fsc.external.portal.dtos.pnl.charges.ChargesDetailResponse;
import com.fsc.external.portal.dtos.pnl.charges.PnlChargesRequest;
import com.fsc.external.portal.dtos.pnl.charges.PnlChargesResponse;
import com.fsc.external.portal.dtos.pnl.charges.PnlChargesTotalDueRequest;
import com.fsc.external.portal.dtos.pnl.charges.PnlChargesTotalDueResponse;
import com.fsc.external.portal.mappers.pnl.ChargesDetailMapper;
import com.fsc.external.portal.mappers.pnl.ChargesPaymentMapper;
import com.fsc.external.portal.mappers.pnl.PnlChargesMapper;
import com.fsc.external.portal.services.CatalogElementService;
import com.fsc.external.portal.services.pnl.PnlChargesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/pnl-charges")
public class ChargesController {

    private static final String CHARGES_DUE_MAIN_CURRENCY = "BGN";

    private final PnlChargesMapper pnlChargesMapper;
    private final PnlChargesService pnlChargesService;
    private final AccountingService accountingService;
    private final ChargesDetailMapper chargesDetailMapper;
    private final ChargesPaymentMapper chargesPaymentMapper;
    private final CatalogElementService catalogElementService;

    public ChargesController(PnlChargesMapper pnlChargesMapper,
                             PnlChargesService pnlChargesService,
                             AccountingService accountingService,
                             CatalogElementService catalogElementService,
                             ChargesPaymentMapper chargesPaymentMapper,
                             ChargesDetailMapper chargesDetailMapper) {
        this.pnlChargesMapper = pnlChargesMapper;
        this.pnlChargesService = pnlChargesService;
        this.accountingService = accountingService;
        this.catalogElementService = catalogElementService;
        this.chargesDetailMapper = chargesDetailMapper;
        this.chargesPaymentMapper = chargesPaymentMapper;
    }

    @Operation(summary = "Get charges by personId or pnlId with paging and filter", responses = {
        @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST - personId not passed or not in correct format")
    })
    @PostMapping("/charges")
    public PageResponse<PnlChargesResponse> getCharges(
            @Valid @RequestBody PnlChargesRequest pnlChargesRequest) {

        Page<OrderedCharges> pnlCharges = pnlChargesService.getCharges(pnlChargesRequest);

        return pnlChargesMapper.toPageResponse((PageImpl<OrderedCharges>) pnlCharges);
    }

    @PostMapping("/total-due")
    public PnlChargesTotalDueResponse getTotalDue(@Valid @RequestBody PnlChargesTotalDueRequest pnlChargesTotalDue) {

        BigDecimal totalDue = pnlChargesService.getTotalDue(pnlChargesTotalDue);
        PnlChargesTotalDueResponse response = new PnlChargesTotalDueResponse();
        response.setTotalDueValue(totalDue);
        response.setTotalDueCurrency(CHARGES_DUE_MAIN_CURRENCY);
        return response;
    }

    @Operation(summary = "Get pnl charges by id", description = "Returns detail charge info.",
        parameters = {
            @Parameter(name = "sectionId", description = "section id for which detailed info to be fetched", required = true),
            @Parameter(name = "person", description = "person id for which detailed info to be fetched", required = true)
        },
        responses = {
            @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP requests"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST - pnlId not in correct format"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                content = @Content(
                    schema = @Schema(name = "ErrorDefinition", type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/{chargesId}/person/{personId}")
    public ChargesDetailResponse getChargeById(@PathVariable(value = "chargesId") @Min(value = 1) Long chargesId,
                                               @PathVariable(value = "personId") @Min(value = 1) Long personId,
                                               @RequestParam(value = "pnlId", required = false) Long pnlId) {

        Charges chargeInfo = pnlChargesService.getChargeInfo(personId, pnlId, chargesId);

        final TotalObligationsLeftView totalObligationsLeftView = accountingService.getTotalObligationsLeftByChargeId(chargesId);
        // view only active payments
        final Set<ChargesPayment> activeChargesPayments =
                chargeInfo.getChargesPayments().stream()
                    .filter(x -> !PaymentDistributionStatus.CANCELED.equals(x.getStatus())).collect(Collectors.toSet());

        final String catalogElementName = catalogElementService.getCatalogElementNameByDocument(chargeInfo.getDocument());

        return chargesDetailMapper.toDetailResponse(chargeInfo,
                                                    chargesPaymentMapper.toPaymentDetails(activeChargesPayments),
                                                    totalObligationsLeftView, catalogElementName);
    }

    @Operation(summary = "Create request to e-payment for charges payment", responses = {
        @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP request."
                                             + "The response body contains an access code for the actual payment.",
                    content = @Content(
                            schema = @Schema(name = "EpaymentResponse", type = "object",
                                             implementation = EpaymentResponse.class)
                    )),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested charge could not be found")
    })
    @PostMapping("/pay")
    public EpaymentResponse payCharges(@RequestBody List<PayChargeRequest> charges) {
        // return the access code generated by e-payment to the front end in order to proceed with actual payment
        return pnlChargesService.createPaymentRequest(charges);
    }

    @Operation(summary = "The status of the e-payment request is changed", responses = {
        @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP request."),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @PostMapping("/payment/status")
    public void paymentConfirmed(@RequestBody EpaymentStatusChangedRequest epaymentStatusChangedRequest) {
        pnlChargesService.paymentStatusChanged(epaymentStatusChangedRequest);
    }

    @Operation(summary = "Get access code by e-payment id", responses = {
        @ApiResponse(responseCode = "200", description = "Standard response for successful HTTP request.",
                     content = @Content(
                             schema = @Schema(name = "EpaymentResponse", type = "object",
                                              implementation = EpaymentResponse.class)
                     )),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
        @ApiResponse(responseCode = "500", description = "External system exception")
    })
    @GetMapping("/payment/{paymentId}/accessCode")
    public EpaymentResponse getAccessCode(@PathVariable(value = "paymentId") @Min(value = 1) String paymentId) {
        return pnlChargesService.getAccessCode(paymentId);
    }
}
