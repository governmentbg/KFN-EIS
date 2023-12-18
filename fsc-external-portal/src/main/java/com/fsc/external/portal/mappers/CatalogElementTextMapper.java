package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.catalog.service.CatalogElement;
import com.fsc.common.addon.entity.catalog.service.CatalogElementText;
import com.fsc.common.addon.entity.catalog.service.CatalogStructure;
import com.fsc.external.portal.dtos.catalog.CatalogElementIdsResponse;
import com.fsc.external.portal.dtos.catalog.CatalogElementResponse;
import com.fsc.external.portal.dtos.catalog.CatalogElementTextResponse;
import com.fsc.external.portal.dtos.catalog.CatalogStructureResponse;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CatalogElementTextMapper {

    public abstract List<CatalogElementTextResponse> toResponse(List<CatalogElementText> catalogElementText);

    public abstract List<CatalogElementTextResponse> toResponse(Set<CatalogElementText> catalogElementText);

    public abstract CatalogElementTextResponse toResponse(CatalogElementText catalogElementText);

    public List<CatalogElementIdsResponse> toCatalogElementIdsResponseList(List<Long> catalogElementIds) {
        if (catalogElementIds == null) {
            return null;
        }
        List<CatalogElementIdsResponse> responseList = new ArrayList<>();
        for (Long id : catalogElementIds) {
            CatalogElementIdsResponse response = new CatalogElementIdsResponse();
            response.setId(id);
            responseList.add(response);
        }
        return responseList;
    }

    @Mapping(target = "parent", source = "catalogElement", qualifiedByName = "mapParent")
    @Mapping(target = "correspondenceTypeId", source = "catalogElement.correspondenceType.id")
    public abstract CatalogElementResponse toCatalogElementResponse(CatalogElement catalogElement);

    @Mapping(target = "childId", source = "catalogStructure.id.child")
    public abstract CatalogStructureResponse toCatalogStructureResponse(CatalogStructure catalogStructure);

    @Named("mapParent")
    public Long mapParent(CatalogElement catalogElement) {
        Optional<CatalogStructure> catalogStructure = catalogElement.getParents().stream().findFirst();

        return catalogStructure.isPresent() ? catalogStructure.get().getId().getParent() : 0L;
    }
}
