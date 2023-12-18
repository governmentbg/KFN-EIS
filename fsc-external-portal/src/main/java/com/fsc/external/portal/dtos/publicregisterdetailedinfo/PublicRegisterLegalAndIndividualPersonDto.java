package com.fsc.external.portal.dtos.publicregisterdetailedinfo;

import java.util.List;

public class PublicRegisterLegalAndIndividualPersonDto {
    List<PublicRegisterRelatedPersonDetailDto> legalPersons;
    List<PublicRegisterRelatedPersonDto> individualPersons;

    public PublicRegisterLegalAndIndividualPersonDto(List<PublicRegisterRelatedPersonDetailDto> legalPersons,
                                                     List<PublicRegisterRelatedPersonDto> individualPersons) {
        this.legalPersons = legalPersons;
        this.individualPersons = individualPersons;
    }

    public List<PublicRegisterRelatedPersonDetailDto> getLegalPersons() {
        return legalPersons;
    }

    public void setLegalPersons(
            List<PublicRegisterRelatedPersonDetailDto> legalPersons) {
        this.legalPersons = legalPersons;
    }

    public List<PublicRegisterRelatedPersonDto> getIndividualPersons() {
        return individualPersons;
    }

    public void setIndividualPersons(List<PublicRegisterRelatedPersonDto> individualPersons) {
        this.individualPersons = individualPersons;
    }
}
