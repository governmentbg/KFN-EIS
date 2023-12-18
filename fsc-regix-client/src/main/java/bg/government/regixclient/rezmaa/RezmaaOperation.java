package bg.government.regixclient.rezmaa;

import bg.government.regixclient.Operation;

public enum RezmaaOperation implements Operation {
    CHECK_OBLIGATIONS("TechnoLogica.RegiX.REZMAAdapter.APIService.IREZMAAPI.CheckObligations");

    private final String key;
    
    private RezmaaOperation(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
