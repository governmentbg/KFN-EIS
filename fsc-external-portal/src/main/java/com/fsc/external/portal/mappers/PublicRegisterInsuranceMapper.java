package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.view.publicregister.PublicRegistersView;
import com.fsc.external.portal.dtos.publicregister.PublicRegisterInsuranceResponse;
import com.fsc.external.portal.utils.RoundingUtil;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = RoundingUtil.class
)
public abstract class PublicRegisterInsuranceMapper implements BasePageResponseMapper<PublicRegistersView,
                                                                                  PublicRegisterInsuranceResponse> {

    @Mapping(target = "pnlName", source = "personName")
    public abstract PublicRegisterInsuranceResponse toResponse(PublicRegistersView publicRegister);
}
