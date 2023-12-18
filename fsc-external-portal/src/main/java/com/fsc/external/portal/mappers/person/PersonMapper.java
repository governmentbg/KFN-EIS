package com.fsc.external.portal.mappers.person;

import com.fsc.common.addon.entity.Person;
import com.fsc.external.portal.dtos.person.PersonResponse;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class},
        uses = PnlPersonMapper.class
)
public abstract class PersonMapper {

    @Mapping(source = "pnlPerson", target = "pnlPersonResponseList")
    public abstract PersonResponse toPersonResponse(Person person);

}
