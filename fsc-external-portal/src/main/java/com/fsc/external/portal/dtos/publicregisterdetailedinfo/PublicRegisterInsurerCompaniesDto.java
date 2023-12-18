package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterInsurerCompaniesDto {

    private String personName;
    private String eik;
    private String regOfficeAddress;
    private String mailingAddress;
    private String managementAddress;
    private String insContract;

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

    public String getInsContract() {
        return insContract;
    }

    public void setInsContract(String insContract) {
        this.insContract = insContract;
    }
}
