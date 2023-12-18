package com.fsc.external.portal.mappers.person;

import com.fsc.common.addon.entity.PnlPerson;
import com.fsc.external.portal.dtos.person.PnlPersonResponse;
import com.fsc.external.portal.mappers.pnl.PnlMapper;
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
        imports = {LocalDateTime.class},
        uses = {PnlMapper.class, PersonShortMapper.class, RelationTypeMapper.class}
)
public abstract class PnlPersonMapper {

    public abstract PnlPersonResponse map(PnlPerson person);

    public abstract List<PnlPersonResponse> map(List<PnlPerson> person);

}
