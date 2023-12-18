package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

import java.util.List;

public class PublicRegisterRelatedPersonAndDocumentsDto {
    private List<PublicRegisterRelatedPersonDetailShortDto> personList;
    private List<PublicRegisterDocumentsDto> documentsList;

    public PublicRegisterRelatedPersonAndDocumentsDto() {
    }

    public List<PublicRegisterRelatedPersonDetailShortDto> getPersonList() {
        return personList;
    }

    public void setPersonList(
            List<PublicRegisterRelatedPersonDetailShortDto> personList) {
        this.personList = personList;
    }

    public List<PublicRegisterDocumentsDto> getDocumentsList() {
        return documentsList;
    }

    public void setDocumentsList(
            List<PublicRegisterDocumentsDto> documentsList) {
        this.documentsList = documentsList;
    }
}
