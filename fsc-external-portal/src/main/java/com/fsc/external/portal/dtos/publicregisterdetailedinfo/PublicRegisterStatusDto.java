package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

public class PublicRegisterStatusDto {
    private Long publicRegisterId;
    private String publicRegisterName;

    public Long getPublicRegisterId() {
        return publicRegisterId;
    }

    public void setPublicRegisterId(Long publicRegisterId) {
        this.publicRegisterId = publicRegisterId;
    }

    public String getPublicRegisterName() {
        return publicRegisterName;
    }

    public void setPublicRegisterName(String publicRegisterName) {
        this.publicRegisterName = publicRegisterName;
    }
}
