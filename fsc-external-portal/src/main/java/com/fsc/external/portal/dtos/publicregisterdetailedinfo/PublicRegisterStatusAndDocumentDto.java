package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

import java.util.List;

public class PublicRegisterStatusAndDocumentDto {
    private List<PublicRegisterStatusDto> statusList;
    private List<PublicRegisterDocumentsDto> documentsList;

    public PublicRegisterStatusAndDocumentDto() {
    }

    public List<PublicRegisterStatusDto> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<PublicRegisterStatusDto> statusList) {
        this.statusList = statusList;
    }

    public List<PublicRegisterDocumentsDto> getDocumentsList() {
        return documentsList;
    }

    public void setDocumentsList(List<PublicRegisterDocumentsDto> documentsList) {
        this.documentsList = documentsList;
    }
}
