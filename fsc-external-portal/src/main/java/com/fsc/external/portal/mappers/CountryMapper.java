package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.Country;
import com.fsc.external.portal.dtos.CountryResponse;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CountryMapper {

    public abstract CountryResponse toCountryResponse(Country country);

    public abstract List<CountryResponse> toCountryResponse(List<Country> countryList);
}
