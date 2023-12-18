package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.Pnl;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.pnl.PnlDossierResponse;
import com.fsc.external.portal.services.DossierService;
import com.fsc.external.portal.services.PnlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@Validated
@RestController
@RequestMapping("/rest/dossier")
public class DossierController {

    private final PnlService pnlService;
    private final DossierService dossierService;

    public DossierController(PnlService pnlService, DossierService dossierService) {
        this.pnlService = pnlService;
        this.dossierService = dossierService;
    }

    @Operation(
            summary = "Returns pnl dossier by pnl id.",
            description = "Returns pnl data information.",
            parameters = {
                @Parameter(name = "pnlId",
                            description = "Pnl id which comes after person logged in change pnl context", required = true)
            },
            responses = {
                @ApiResponse(responseCode = "200", description = "Returns name, address, email, eik/bulstat, phone"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - Pnl id passed not in correct format"),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                            content = @Content(schema = @Schema(name = "ErrorDefinition",
                                    type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/{pnlId}")
    public ResponseEntity<PnlDossierResponse> getDossierByPnlId(
            @PathVariable(value = "pnlId") @Min(value = 1) Long pnlId) {

        Pnl pnl = pnlService.getPnlByPnlId(pnlId);

        return dossierService.getDossierByPnl(pnl);
    }
}
