package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.catalog.service.ServiceRequestDocument;
import com.fsc.common.addon.entity.document.DocumentType;
import com.fsc.external.portal.dtos.ServiceRequestDocumentExternalRequest;
import com.fsc.external.portal.dtos.ServiceRequestDocumentRequest;
import com.fsc.external.portal.dtos.ServiceRequestDocumentResponse;
import io.jmix.core.DataManager;
import io.jmix.core.FileRef;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ObjectFactory;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class}
)
public abstract class ServiceRequestDocumentMapper {

    @Autowired
    private DataManager dataManager;

    @Mapping(target = "documentType.id", source = "documentTypeId")
    @Mapping(target = "createdDate", expression = "java(LocalDateTime.now())")
    @Mapping(target = "fileReference", source = "fileReferenceStr", qualifiedByName = "mapFileRefStr")
    public abstract ServiceRequestDocument toEntity(ServiceRequestDocumentRequest serviceRequestDocumentRequest);

    @Mapping(target = "documentTypeId", source = "documentType.id")
    @Mapping(target = "fileRef", source = "fileReference")
    @Mapping(target = "fileReferenceStr", expression = "java(entity.getFileReference().toString())")
    public abstract ServiceRequestDocumentResponse toResponse(ServiceRequestDocument entity);

    public abstract Set<ServiceRequestDocumentResponse> toResponse(Set<ServiceRequestDocument> entity);

    public abstract DocumentType map(String value);

    public abstract String map(DocumentType value);

    @Mapping(target = "documentType", source = "documentType.shortName")
    @Mapping(target = "description", source = "documentDescription")
    @Mapping(target = "name", source = "fileReference.fileName")
    @Mapping(target = "date", source = "createdDate", dateFormat = "dd.MM.yyyy HH:mm")
    public abstract ServiceRequestDocumentExternalRequest toRequest(ServiceRequestDocument serviceRequestDocument);

    @ObjectFactory
    ServiceRequestDocument resolve() {
        return dataManager.create(ServiceRequestDocument.class);
    }

    @Named("mapFileRefStr")
    public FileRef mapFileRefStr(String fileRef) {
        return FileRef.fromString(fileRef);
    }
}
