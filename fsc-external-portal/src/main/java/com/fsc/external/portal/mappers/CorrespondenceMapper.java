package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.document.Correspondence;
import com.fsc.common.addon.entity.view.documents.CorrespondenceListView;
import com.fsc.external.portal.dtos.CorrespondenceRequest;
import com.fsc.external.portal.dtos.CorrespondenceResponse;

import com.fsc.external.portal.dtos.RequestedServicesResponse;
import io.jmix.core.DataManager;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ObjectFactory;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CorrespondenceMapper {
    @Autowired
    private DataManager dataManager;

    @Mapping(target = "type.id", source = "typeId")
    @Mapping(target = "pnl.id", source = "pnlId")
    public abstract Correspondence toCorrespondence(CorrespondenceRequest correspondenceRequest);

    public abstract CorrespondenceResponse toResponse(Correspondence correspondence);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "creationDate", source = "date")
    @Mapping(target = "typeId", source = "typeID")
    @Mapping(target = "typeName", source = "typeName")
    @Mapping(target = "personName", source = "personName")
    public abstract RequestedServicesResponse toResponse(CorrespondenceListView correspondence);

    @ObjectFactory
    Correspondence resolve() {
        return dataManager.create(Correspondence.class);
    }
}
