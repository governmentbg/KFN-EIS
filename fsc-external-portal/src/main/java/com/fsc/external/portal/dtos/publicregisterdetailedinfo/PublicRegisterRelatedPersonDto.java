package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterRelatedPersonDto {

    private Long personId;
    private String personName;
    private String role;

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
