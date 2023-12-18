package com.fsc.external.portal.controllers;

import com.fsc.external.portal.dtos.NomenclatureResponse;
import com.fsc.external.portal.services.ExternalDynamicFormCommunicationService;
import com.fsc.external.portal.services.NomenclatureService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rest/nomenclature")
public class NomenclatureController {

    private final NomenclatureService nomenclatureService;
    private final ExternalDynamicFormCommunicationService externalDynamicFormCommunicationService;

    public NomenclatureController(
            NomenclatureService nomenclatureService,
            ExternalDynamicFormCommunicationService externalDynamicFormCommunicationService) {
        this.nomenclatureService = nomenclatureService;
        this.externalDynamicFormCommunicationService = externalDynamicFormCommunicationService;
    }

    @Operation(summary = "Get list of certain nomenclature.")
    @GetMapping("/type/{type}")
    public List<NomenclatureResponse> getNumenclature(@PathVariable final String type) {
        return nomenclatureService.getByType(type);
    }

    @Operation(summary = "Get certain nomenclature.")
    @GetMapping("/type/{type}/{id}")
    public NomenclatureResponse getNumenclatureElementById(
            @PathVariable final String type,
            @PathVariable final Long id
    ) {
        return nomenclatureService.getByTypeAndId(type, id);
    }

    // TODO will be deleted/refactor when we know what we need
    @Operation(summary = "Delete this when the time is right or left or whatever.")
    @GetMapping("/{type}")
    public Object getNumenclatureFromPhPByName(
            @PathVariable final String type,
            @RequestParam(required = false) final String search,
            @RequestParam(required = false) final Long id
    ) {
        return externalDynamicFormCommunicationService.getNumenclatureFromPhPByName(type, search, id);
    }

    // TODO will be deleted/refactor when we know what we need
    @Operation(summary = "Delete this when the time is right or left or whatever.")
    @GetMapping("/{type}/{id}")
    public Object getNumenclatureFromPhPById(
            @PathVariable final String type,
            @PathVariable final Long id
    ) {
        return externalDynamicFormCommunicationService.getNumenclatureFromPhPById(type, id);
    }
}