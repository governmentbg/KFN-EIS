package com.fsc.external.portal.mappers.pnl;

import com.fsc.common.addon.entity.Address;
import com.fsc.common.addon.entity.Person;
import com.fsc.common.addon.entity.enums.PersonType;
import com.fsc.common.addon.entity.enums.PnlAddressType;
import com.fsc.external.portal.dtos.pnl.PnlDossierResponse;
import com.fsc.external.portal.mappers.BasePageResponseMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)

public abstract class PnlDossierMapper implements BasePageResponseMapper<Person, PnlDossierResponse> {

    @Mapping(target = "name", source = "person", qualifiedByName = "mapName")
    @Mapping(target = "headquartersAddress", source = "person", qualifiedByName = "mapHeadquartersAddress")
    @Mapping(target = "correspondenceAddress", source = "person", qualifiedByName = "mapCorrespondenceAddress")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "phone", source = "phone")
    @Mapping(target = "fax", source = "fax")
    @Mapping(target = "website", source = "website")
    public abstract PnlDossierResponse toResponse(Person person, String email, String phone, String fax, String website);

    @Named("mapName")
    String mapName(Person person) {
        String result = "";
        if (PersonType.LEGAL.equals(person.getPersonType())) {
            result = person.getName();
        }
        if (PersonType.NATURAL.equals(person.getPersonType())) {
            result = String.format("%s %s %s", person.getFirstName(), person.getMiddleName(), person.getLastName());
        }
        return result;
    }

    @Named("mapHeadquartersAddress")
    String mapHeadquartersAddress(Person person) {
        return findAddressByType(person, PnlAddressType.HEAD_ADDRESS);
    }

    @Named("mapCorrespondenceAddress")
    String mapCorrespondenceAddress(Person person) {
        return findAddressByType(person, PnlAddressType.CORRESPONDENCE_ADDRESS);
    }

    private String findAddressByType(Person person, PnlAddressType type) {
        String result = "";
        for (Address address : person.getAddresses()) {
            if (type.equals(address.getPnlAddressType())) {
                result = address.getAddress();
                break;
            }
        }
        return result;
    }
}
