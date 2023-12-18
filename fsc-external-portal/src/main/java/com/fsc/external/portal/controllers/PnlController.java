package com.fsc.external.portal.controllers;

import com.fsc.external.portal.dtos.NomenclatureResponse;
import com.fsc.external.portal.services.PnlService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/pnl")
public class PnlController {

    private final PnlService pnlService;

    public PnlController(PnlService pnlService) {
        this.pnlService = pnlService;
    }

    @Operation(summary = "Return pnls by name")
    @GetMapping
    public List<NomenclatureResponse> getPnls(@RequestParam String pnlName) {
        return pnlService.getPnlsByName(pnlName);
    }

    @Operation(summary = "Return pnl by id")
    @GetMapping("/{pnlId}")
    public NomenclatureResponse getPnlById(@PathVariable Long pnlId) {
        return pnlService.getPnlById(pnlId);
    }
}