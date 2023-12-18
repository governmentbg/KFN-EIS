package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public class CapitalInfoResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal capital;

    private String assetType;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal emissionValues;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal assetCount;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal assetNominal;

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getEmissionValues() {
        return emissionValues;
    }

    public void setEmissionValues(BigDecimal emissionValues) {
        this.emissionValues = emissionValues;
    }

    public BigDecimal getAssetCount() {
        return assetCount;
    }

    public void setAssetCount(BigDecimal assetCount) {
        this.assetCount = assetCount;
    }

    public BigDecimal getAssetNominal() {
        return assetNominal;
    }

    public void setAssetNominal(BigDecimal assetNominal) {
        this.assetNominal = assetNominal;
    }
}
