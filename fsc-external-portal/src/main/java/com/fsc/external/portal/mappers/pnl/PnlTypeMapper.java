package com.fsc.external.portal.mappers.pnl;

import com.fsc.common.addon.entity.PnlType;
import com.fsc.external.portal.dtos.pnl.PnlTypeResponse;
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
public abstract class PnlTypeMapper {

    public abstract PnlTypeResponse toPnlTypeResponse(PnlType pnlType);
}
