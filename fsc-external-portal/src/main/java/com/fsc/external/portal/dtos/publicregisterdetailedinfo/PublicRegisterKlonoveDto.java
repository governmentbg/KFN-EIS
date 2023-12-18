package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterKlonoveDto {

    private String personName;
    private Long klonId;
    private String klonName;
    private String address;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getKlonId() {
        return klonId;
    }

    public void setKlonId(Long klonId) {
        this.klonId = klonId;
    }

    public String getKlonName() {
        return klonName;
    }

    public void setKlonName(String klonName) {
        this.klonName = klonName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
