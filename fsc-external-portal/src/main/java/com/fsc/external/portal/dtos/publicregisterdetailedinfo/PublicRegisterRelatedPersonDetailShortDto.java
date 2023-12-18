package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterRelatedPersonDetailShortDto {

    private String pnlName;

    private String eik;

    private String regOfficeAddress;

    private String managementAddress;

    private String mailingAddress;

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getPnlName() {
        return pnlName;
    }

    public void setPnlName(String pnlName) {
        this.pnlName = pnlName;
    }

    public String getEik() {
        return eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
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
}
