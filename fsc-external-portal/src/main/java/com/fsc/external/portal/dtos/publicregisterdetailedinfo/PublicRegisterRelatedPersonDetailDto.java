package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterRelatedPersonDetailDto {
    private String pnlId;
    private String personId;
    private String personName;
    private String officeAddress;
    private String regOfficeAddress;
    private String mailingAddress;
    private String managementAddress;
    private String role;
    private String activity;
    private String personType;

    private String eik;

    public String getPnlId() {
        return pnlId;
    }

    public void setPnlId(String pnlId) {
        this.pnlId = pnlId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getRegOfficeAddress() {
        return regOfficeAddress;
    }

    public void setRegOfficeAddress(String regOfficeAddress) {
        this.regOfficeAddress = regOfficeAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getManagementAddress() {
        return managementAddress;
    }

    public void setManagementAddress(String managementAddress) {
        this.managementAddress = managementAddress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getEik() {
        return eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
    }
}
