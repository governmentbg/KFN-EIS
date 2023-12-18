package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterRelatedPnlWithRegisterDto {
    private Long pnlId;
    private String pnlName;
    private String eikEgn;
    private String regOfficeAddress;
    private String managementAddress;
    private String pnlTypeName;
    private Long registerId;

    public Long getPnlId() {
        return pnlId;
    }

    public void setPnlId(Long pnlId) {
        this.pnlId = pnlId;
    }

    public String getPnlName() {
        return pnlName;
    }

    public void setPnlName(String pnlName) {
        this.pnlName = pnlName;
    }

    public String getEikEgn() {
        return eikEgn;
    }

    public void setEikEgn(String eikEgn) {
        this.eikEgn = eikEgn;
    }

    public String getRegOfficeAddress() {
        return regOfficeAddress;
    }

    public void setRegOfficeAddress(String regOfficeAddress) {
        this.regOfficeAddress = regOfficeAddress;
    }

    public String getManagementAddress() {
        return managementAddress;
    }

    public void setManagementAddress(String managementAddress) {
        this.managementAddress = managementAddress;
    }

    public String getPnlTypeName() {
        return pnlTypeName;
    }

    public void setPnlTypeName(String pnlTypeName) {
        this.pnlTypeName = pnlTypeName;
    }

    public Long getRegisterId() {
        return registerId;
    }

    public void setRegisterId(Long registerId) {
        this.registerId = registerId;
    }
}
