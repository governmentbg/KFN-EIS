
package bg.government.regixclient.nra;

import bg.government.regixclient.Operation;

public enum NraOperation implements Operation {

    EMPLOYMENT_CONTRACT_SEARCH("TechnoLogica.RegiX.NRAEmploymentContractsAdapter.APIService.INRAEmploymentContractsAPI.GetEmploymentContracts"),
    OBLIGATION_SEARCH("TechnoLogica.RegiX.NRAObligatedPersonsAdapter.APIService.INRAObligatedPersonsAPI.GetObligatedPersons"),
    SOCIAL_SECUIRTY_DATA_FROM_DECLARATION("TechnoLogica.RegiX.NRAObligatedPersonsAdapter.APIService.INRAObligatedPersonsAPI.GetSocialSecurityDataFromDeclarations");

    private final String key;

    NraOperation(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}
