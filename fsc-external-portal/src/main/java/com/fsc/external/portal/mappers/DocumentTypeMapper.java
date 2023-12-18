package com.fsc.external.portal.mappers;

import com.fsc.common.addon.entity.catalog.service.ServiceCatalogElementDocumentTypes;
import com.fsc.common.addon.entity.document.DocumentType;
import com.fsc.common.addon.entity.document.DocumentTypeInCorrespondence;
import com.fsc.external.portal.dtos.DocumentTypeByCatalogResponse;
import com.fsc.external.portal.dtos.DocumentTypeResponse;
import io.jmix.core.FileRef;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = {LocalDateTime.class}
)
public abstract class DocumentTypeMapper {

    public abstract DocumentTypeResponse toDocument(DocumentType documentRequest);

    public abstract Set<DocumentTypeResponse> toDocument(Set<DocumentType> documentRequest);

    @Mapping(source = "documentType.id", target = "id")
    @Mapping(source = "documentType.longName", target = "longName")
    @Mapping(source = "documentType.shortName", target = "shortName")
    public abstract DocumentTypeResponse toDocumentType(DocumentTypeInCorrespondence documentTypeInCorrespondence);

    public abstract List<DocumentTypeResponse> toDocumentType(List<DocumentTypeInCorrespondence> documentRequest);

    @Mapping(target = "documentTypeId", source = "documentType.id")
    @Mapping(target = "longName", source = "documentType.longName")
    @Mapping(target = "mandatory", source = "mandatory.id")
    @Mapping(target = "shortName", source = "serviceCatalogElementDocumentTypes", qualifiedByName = "applyBusinessDescription")
    @Mapping(target = "templateFileReference", source = "serviceCatalogElementDocumentTypes", qualifiedByName = "mapTemplateFileRef")
    @Mapping(target = "templateFileReferenceStr", source = "serviceCatalogElementDocumentTypes", qualifiedByName = "mapTemplateFileRefStr")
    @Named("toDocumentTypeByCatalogResponse")
    abstract DocumentTypeByCatalogResponse toServiceCatalogElementDocumentType(
            ServiceCatalogElementDocumentTypes serviceCatalogElementDocumentTypes);

    @IterableMapping(qualifiedByName = "toDocumentTypeByCatalogResponse")
    public abstract List<DocumentTypeByCatalogResponse> toServiceCatalogElementDocumentTypes(
            List<ServiceCatalogElementDocumentTypes> serviceCatalogElementDocumentTypes);

    @Named("applyBusinessDescription")
    public String applyBusinessDescription(ServiceCatalogElementDocumentTypes serviceCatalogElementDocumentTypes) {

        return serviceCatalogElementDocumentTypes.getBusinessDescription() != null
                       ? serviceCatalogElementDocumentTypes.getBusinessDescription()
                       : serviceCatalogElementDocumentTypes.getDocumentType().getShortName();
    }

    @Named("mapTemplateFileRef")
    public FileRef mapTemplateFileRef(ServiceCatalogElementDocumentTypes sceDocType) {
        if (sceDocType.getTemplate() != null) {
            return sceDocType.getTemplate().getFileRef();
        } else if (sceDocType.getDocumentType().getTemplate() != null) {
            return sceDocType.getDocumentType().getTemplate().getFileRef();
        } else {
            return null;
        }
    }

    @Named("mapTemplateFileRefStr")
    public String mapTemplateFileReferenceStr(ServiceCatalogElementDocumentTypes sceDocType) {
        if (sceDocType.getTemplate() != null) {
            FileRef tempFileRef = sceDocType.getTemplate().getFileRef();
            return tempFileRef != null ? tempFileRef.toString() : null;
        } else if (sceDocType.getDocumentType().getTemplate() != null) {
            FileRef tempFileRef = sceDocType.getDocumentType().getTemplate().getFileRef();
            return tempFileRef != null ? tempFileRef.toString() : null;
        } else {
            return null;
        }
    }
}
