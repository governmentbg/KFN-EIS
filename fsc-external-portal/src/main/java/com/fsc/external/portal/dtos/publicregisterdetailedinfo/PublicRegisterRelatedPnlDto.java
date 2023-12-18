package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterRelatedPnlDto {
    private Long pnlId;
    private String pnlName;
    private String eikEgn;
    private String regOfficeAddress;
    private String managementAddress;
    private String pnlTypeName;
    private String activity;

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

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
