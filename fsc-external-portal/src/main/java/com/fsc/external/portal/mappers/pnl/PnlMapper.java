package com.fsc.external.portal.mappers.pnl;

import com.fsc.common.addon.entity.Pnl;
import com.fsc.external.portal.dtos.pnl.PnlResponse;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class}
)
public abstract class PnlMapper {

    public abstract PnlResponse toPnlResponse(Pnl pnl);

    public abstract List<PnlResponse> toPnlResponse(List<Pnl> pnls);
}
