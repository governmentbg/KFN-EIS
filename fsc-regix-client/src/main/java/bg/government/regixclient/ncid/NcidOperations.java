package bg.government.regixclient.ncid;

import bg.government.regixclient.Operation;

public enum NcidOperations implements Operation {

    ACADEMIC_RECOGNITION_CHECK("TechnoLogica.RegiX.PDVOAdapter.APIService.IPDVOAPI.GetAcademicRecognition");

    private final String key;

    NcidOperations(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
