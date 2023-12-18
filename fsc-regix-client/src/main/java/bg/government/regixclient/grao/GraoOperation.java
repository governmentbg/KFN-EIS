package bg.government.regixclient.grao;

import bg.government.regixclient.Operation;

public enum GraoOperation implements Operation {
    PERMANENT_ADDRESS_SEARCH("TechnoLogica.RegiX.GraoPNAAdapter.APIService.IPNAAPI.PermanentAddressSearch"),
    TEMPORARY_ADDRESS_SEARCH("TechnoLogica.RegiX.GraoPNAAdapter.APIService.IPNAAPI.TemporaryAddressSearch"),
    PERSON_DATA_SEARCH("TechnoLogica.RegiX.GraoNBDAdapter.APIService.INBDAPI.PersonDataSearch"),
    PERSON_RELATIONS_SEARCH("TechnoLogica.RegiX.GraoNBDAdapter.APIService.INBDAPI.RelationsSearch"),
    VALID_PERSON_SEARCH("TechnoLogica.RegiX.GraoNBDAdapter.APIService.INBDAPI.ValidPersonSearch");

    private final String key;
    
    private GraoOperation(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
