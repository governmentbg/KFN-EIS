package com.fsc.external.portal.dtos.catalog;

import com.fsc.common.addon.entity.enums.CatalogStatus;
import com.fsc.common.addon.entity.enums.CatalogType;

import java.io.Serializable;
import java.util.Set;

public class CatalogElementResponse implements Serializable {
    private Long id;
    private Long level;
    private CatalogStatus catalogStatus;
    private CatalogType catalogType;
    private Long parent;
    private Long correspondenceTypeId;
    private String serviceType;
    private Integer serviceAuthenticationLevel;
    private Set<CatalogStructureResponse> children;
    private Boolean visibleOnExternalPortal;
    private Boolean hasSpecialDocs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public CatalogStatus getCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(CatalogStatus catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public CatalogType getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(CatalogType catalogType) {
        this.catalogType = catalogType;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Set<CatalogStructureResponse> getChildren() {
        return children;
    }

    public void setChildren(Set<CatalogStructureResponse> children) {
        this.children = children;
    }

    public Long getCorrespondenceTypeId() {
        return correspondenceTypeId;
    }

    public void setCorrespondenceTypeId(Long correspondenceTypeId) {
        this.correspondenceTypeId = correspondenceTypeId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public Integer getServiceAuthenticationLevel() {
        return serviceAuthenticationLevel;
    }

    public void setServiceAuthenticationLevel(Integer serviceAuthenticationLevel) {
        this.serviceAuthenticationLevel = serviceAuthenticationLevel;
    }

    public Boolean getVisibleOnExternalPortal() {
        return visibleOnExternalPortal;
    }

    public void setVisibleOnExternalPortal(Boolean visibleOnExternalPortal) {
        this.visibleOnExternalPortal = visibleOnExternalPortal;
    }

    public Boolean getHasSpecialDocs() {
        return hasSpecialDocs;
    }

    public void setHasSpecialDocs(Boolean hasSpecialDocs) {
        this.hasSpecialDocs = hasSpecialDocs;
    }
}
