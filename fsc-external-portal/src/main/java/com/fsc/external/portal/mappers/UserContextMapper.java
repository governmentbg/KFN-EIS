package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.Person;
import com.fsc.common.addon.entity.PnlPerson;
import com.fsc.common.addon.entity.PnlType;
import com.fsc.common.addon.entity.User;
import com.fsc.common.addon.entity.enums.PersonType;
import com.fsc.external.portal.dtos.UserContextResponse;
import com.fsc.external.portal.dtos.UserContextType;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.StringJoiner;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {UserContextType.class}
)
public abstract class UserContextMapper {

    @Mapping(source = "pnl.id", target = "pnlId")
    @Mapping(source = "mainPerson.id", target = "personId")
    @Mapping(source = "mainPerson", target = "name", qualifiedByName = "personNameToUserContextName")
    @Mapping(source = "mainPerson.personType", target = "userContextType", qualifiedByName = "personTypeToUserContextType")
    @Mapping(source = "pnl.pnlType", target = "pnlType", qualifiedByName = "pnlTypeToUserContextPnlType")
    public abstract UserContextResponse map(PnlPerson person);

    public abstract List<UserContextResponse> map(List<PnlPerson> person);


    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "person.name", target = "name")
    @Mapping(target = "userContextType", expression = "java(UserContextType.ME.getUserContextTypeResponseText())")
    public abstract UserContextResponse mapUserToUserContextResponse(User user);

    @Named("personTypeToUserContextType")
    static String personTypeToUserContextType(PersonType personType) {
        String userContextType = null;
        if (PersonType.LEGAL == personType) {
            userContextType = UserContextType.LEGAL_ENTITY.getUserContextTypeResponseText();
        } else if (PersonType.NATURAL == personType) {
            userContextType = UserContextType.INDIVIDUAL.getUserContextTypeResponseText();
        }
        return userContextType;
    }

    @Named("personNameToUserContextName")
    static String personNameToUserContextName(Person person) {
        String name = null;
        if (PersonType.LEGAL.equals(person.getPersonType())) {
            name = person.getName();
        } else if (PersonType.NATURAL.equals(person.getPersonType())) {
            StringJoiner joiner = new StringJoiner(" ");
            if (person.getFirstName() != null) {
                joiner.add(person.getFirstName());
            }
            if (person.getMiddleName() != null) {
                joiner.add(person.getMiddleName());
            }
            if (person.getLastName() != null) {
                joiner.add(person.getLastName());
            }
            name = joiner.toString();
        }
        return name;
    }

    @Named("pnlTypeToUserContextPnlType")
    static String pnlTypeToUserContextPnlType(PnlType pnlType) {
        String userContextPnlType = null;

        if (pnlType != null) {
            userContextPnlType = pnlType.getName();
        }

        return userContextPnlType;
    }
}