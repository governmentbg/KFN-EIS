package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

import java.time.LocalDateTime;

public class DetailedInfoResponse {

    private Long pnlId;
    private String pnlName;
    private String eikEgn;
    private String leiCode;
    private String regOfficeAddress;
    private String mailingAddress;
    private String managementAddress;
    private String email;
    private String website;
    private String phone;
    private String fax;
    private String inRegisters;
    private Long personId;
    private String personType;
    private Long registrationCountryId;
    private String countryName;

    private String personName;
    private String legalRegistration;
    private LocalDateTime registrationDate;
    private String registrationMethod;
    private String registrationPeriod;

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

    public String getLeiCode() {
        return leiCode;
    }

    public void setLeiCode(String leiCode) {
        this.leiCode = leiCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getInRegisters() {
        return inRegisters;
    }

    public void setInRegisters(String inRegisters) {
        this.inRegisters = inRegisters;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getLegalRegistration() {
        return legalRegistration;
    }

    public void setLegalRegistration(String legalRegistration) {
        this.legalRegistration = legalRegistration;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getRegistrationMethod() {
        return registrationMethod;
    }

    public void setRegistrationMethod(String registrationMethod) {
        this.registrationMethod = registrationMethod;
    }

    public String getRegistrationPeriod() {
        return registrationPeriod;
    }

    public void setRegistrationPeriod(String registrationPeriod) {
        this.registrationPeriod = registrationPeriod;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public Long getRegistrationCountryId() {
        return registrationCountryId;
    }

    public void setRegistrationCountryId(Long registrationCountryId) {
        this.registrationCountryId = registrationCountryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
