package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterRelationsDto {

    private String relation;
    private Long personId;
    private String personName;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

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
}
