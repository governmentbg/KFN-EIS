package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterRelatedPersonManagementDetailDto {

    private Long pnlId;
    private String role;
    private String personName;
    private String eik;
    private String personTypeName;
    private String regOfficeAddress;
    private String personRepresentative;
    private String wayOfRepresentative;
    private String managementAddress;

    public Long getPnlId() {
        return pnlId;
    }

    public void setPnlId(Long pnlId) {
        this.pnlId = pnlId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getEik() {
        return eik;
    }

    public void setEik(String eik) {
        this.eik = eik;
    }

    public String getPersonTypeName() {
        return personTypeName;
    }

    public void setPersonTypeName(String personTypeName) {
        this.personTypeName = personTypeName;
    }

    public String getRegOfficeAddress() {
        return regOfficeAddress;
    }

    public void setRegOfficeAddress(String regOfficeAddress) {
        this.regOfficeAddress = regOfficeAddress;
    }

    public String getPersonRepresentative() {
        return personRepresentative;
    }

    public void setPersonRepresentative(String personRepresentative) {
        this.personRepresentative = personRepresentative;
    }

    public String getWayOfRepresentative() {
        return wayOfRepresentative;
    }

    public void setWayOfRepresentative(String wayOfRepresentative) {
        this.wayOfRepresentative = wayOfRepresentative;
    }

    public String getManagementAddress() {
        return managementAddress;
    }

    public void setManagementAddress(String managementAddress) {
        this.managementAddress = managementAddress;
    }
}
