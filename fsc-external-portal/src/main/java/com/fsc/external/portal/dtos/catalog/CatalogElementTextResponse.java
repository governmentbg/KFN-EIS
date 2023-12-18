package com.fsc.external.portal.dtos.catalog;

import java.io.Serializable;

public class CatalogElementTextResponse implements Serializable {
    private String name;
    private String descriptionShort;
    private String descriptionLong;
    private CatalogElementResponse catalogElement;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public CatalogElementResponse getCatalogElement() {
        return catalogElement;
    }

    public void setCatalogElement(CatalogElementResponse catalogElement) {
        this.catalogElement = catalogElement;
    }
}
