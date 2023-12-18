package com.fsc.external.portal.mappers.person;

import com.fsc.common.addon.entity.RelationType;
import com.fsc.external.portal.dtos.person.RelationTypeResponse;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class RelationTypeMapper {

    public abstract RelationTypeResponse toRelationTypeResponse(RelationType relationType);
}
