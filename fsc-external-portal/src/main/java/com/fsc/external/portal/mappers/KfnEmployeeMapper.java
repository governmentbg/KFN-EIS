package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.KfnEmployee;
import com.fsc.external.portal.dtos.KfnEmployeeResponse;
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

public abstract class KfnEmployeeMapper {
    public abstract KfnEmployeeResponse toEmployee(KfnEmployee kfnEmployee);
}
