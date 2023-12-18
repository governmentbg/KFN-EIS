package com.fsc.external.portal.controllers;

import com.fsc.common.addon.entity.Person;
import com.fsc.common.addon.entity.User;
import com.fsc.exception.library.exception.enums.ErrorDefinition;
import com.fsc.external.portal.dtos.person.PhysicalPersonProfileResponse;
import com.fsc.external.portal.mappers.person.PhysicalPersonProfileMapper;
import com.fsc.external.portal.services.PhysicalPersonProfileService;
import io.jmix.core.security.CurrentAuthentication;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/physical-person-user-profile")
public class PhysicalPersonProfileController {

    private final PhysicalPersonProfileService physicalPersonProfileService;
    private final PhysicalPersonProfileMapper personProfileMapper;
    private final CurrentAuthentication currentAuthentication;

    public PhysicalPersonProfileController(PhysicalPersonProfileService physicalPersonProfileService,
                                           PhysicalPersonProfileMapper personProfileMapper, CurrentAuthentication currentAuthentication) {
        this.physicalPersonProfileService = physicalPersonProfileService;
        this.personProfileMapper = personProfileMapper;
        this.currentAuthentication = currentAuthentication;
    }

    @Operation(summary = "Returns physical person's information by logged user.",
            description = "Returns physical person's information by logged user - addresses, contacts, personal identification number "
                    +
                    "contact number, and email address.",
            responses = {
                @ApiResponse(responseCode = "200", description = "Returns personal information and contact information"),
                @ApiResponse(responseCode = "400", description = "BAD REQUEST - Invalid request parameters sent"),
                @ApiResponse(responseCode = "401", description = "UNAUTHORIZED - Authorization information is missing or invalid",
                            content = @Content(schema = @Schema(name = "UNAUTHORIZED",
                                    example = "Full authentication is required to access this resource"))),
                @ApiResponse(responseCode = "404", description = "NOT FOUND - The requested resource could not be found",
                            content = @Content(schema = @Schema(name = "ErrorDefinition",
                                    type = "object", implementation = ErrorDefinition.class)))
            })
    @GetMapping("/personal-info")
    public ResponseEntity<PhysicalPersonProfileResponse> getPhysicalPersonsInfo() {

        User loggedUser = (User) currentAuthentication.getUser();

        Person person = physicalPersonProfileService.getPhysicalPersonInfoByUser(loggedUser);

        return ResponseEntity.ok(personProfileMapper.map(person));
    }

}