package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

import java.util.List;

public class PublicRegisterManagementResponseDto {

    private String wayOfRepresentative;
    private List<PublicRegisterRelatedPersonDto> respresentativeList;
    private List<PublicRegisterRelatedPersonManagementDetailDto> supervisoryList;

    private List<PublicRegisterRelatedPersonManagementDetailDto> bordOfDirectorList;

    private List<PublicRegisterRelatedPersonManagementDetailDto> directorList;

    public String getWayOfRepresentative() {
        return wayOfRepresentative;
    }

    public void setWayOfRepresentative(String wayOfRepresentative) {
        this.wayOfRepresentative = wayOfRepresentative;
    }

    public List<PublicRegisterRelatedPersonDto> getRespresentativeList() {
        return respresentativeList;
    }

    public void setRespresentativeList(
            List<PublicRegisterRelatedPersonDto> respresentativeList) {
        this.respresentativeList = respresentativeList;
    }

    public List<PublicRegisterRelatedPersonManagementDetailDto> getSupervisoryList() {
        return supervisoryList;
    }

    public void setSupervisoryList(
            List<PublicRegisterRelatedPersonManagementDetailDto> supervisoryList) {
        this.supervisoryList = supervisoryList;
    }

    public List<PublicRegisterRelatedPersonManagementDetailDto> getBordOfDirectorList() {
        return bordOfDirectorList;
    }

    public void setBordOfDirectorList(
            List<PublicRegisterRelatedPersonManagementDetailDto> bordOfDirectorList) {
        this.bordOfDirectorList = bordOfDirectorList;
    }

    public List<PublicRegisterRelatedPersonManagementDetailDto> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(
            List<PublicRegisterRelatedPersonManagementDetailDto> directorList) {
        this.directorList = directorList;
    }
}
