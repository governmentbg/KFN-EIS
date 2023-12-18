package com.fsc.external.portal.mappers.services;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class OpenDataPnlTypeMapper {

    private final Map<String, Long> pnlTypeStringToLongMap;

    public OpenDataPnlTypeMapper() {
        pnlTypeStringToLongMap = new HashMap<>();
        pnlTypeStringToLongMap.put("regulated-financial-instrument-markets", 17L);
        pnlTypeStringToLongMap.put("investment-intermediaries", 18L);
        pnlTypeStringToLongMap.put("collective-investment-schemes", 19L);
        pnlTypeStringToLongMap.put("national-investment-funds", 20L);
        pnlTypeStringToLongMap.put("management-companies", 21L);
        pnlTypeStringToLongMap.put("broker-individuals-for-investment-consultations-and-transactions", 22L);
        pnlTypeStringToLongMap.put("investment-consultants-individuals-for-investment-consultations-and-transactions", 23L);
        pnlTypeStringToLongMap.put("insurers-and-reinsurers", 24L);
        pnlTypeStringToLongMap.put("insurance-brokers", 25L);
        pnlTypeStringToLongMap.put("insurance-agents", 26L);
        pnlTypeStringToLongMap.put("companies-for-additional-social-security(replaced-by-id=60)", 27L);
        pnlTypeStringToLongMap.put("pension-funds", 28L);
        pnlTypeStringToLongMap.put("professional-schemes", 29L);
        pnlTypeStringToLongMap.put("insurance-intermediaries-of-companies-for-additional-social-security", 30L);
        pnlTypeStringToLongMap.put("persons-managing-aif", 31L);
        pnlTypeStringToLongMap.put("persons-with-recognized-capacity-as-a-responsible-actuary", 32L);
        pnlTypeStringToLongMap.put("public-companies-and-other-securities-issuers", 33L);
        pnlTypeStringToLongMap.put("pension-insurance-company(discontinued)", 34L);
        pnlTypeStringToLongMap.put("insurance", 37L);
        pnlTypeStringToLongMap.put("pension-insurance-company", 60L);
        pnlTypeStringToLongMap.put("custodian-bank", 61L);
        pnlTypeStringToLongMap.put("benchmark-administrators", 70L);
        pnlTypeStringToLongMap.put("data-reporting-service-providers", 71L);
        pnlTypeStringToLongMap.put("multilateral-trading-facilities", 72L);
        pnlTypeStringToLongMap.put("tied-agents-of-investment-intermediaries", 73L);
        pnlTypeStringToLongMap.put("authorized-counselors", 74L);
        pnlTypeStringToLongMap.put("professional-scheme-funds", 75L);
        pnlTypeStringToLongMap.put("schemes-for-alternative-transfer-of-insurance-risk", 76L);
        pnlTypeStringToLongMap.put("securitization-companies", 77L);
        pnlTypeStringToLongMap.put("organized-trading-systems", 78L);
        pnlTypeStringToLongMap.put("approved-reporting-mechanisms-and-approved-publishing-mechanisms", 79L);
        pnlTypeStringToLongMap.put("pension-payment-fund", 80L);
        pnlTypeStringToLongMap.put("central-depository", 81L);
        pnlTypeStringToLongMap.put("contractual-fund", 82L);
        pnlTypeStringToLongMap.put("investor-compensation-fund", 83L);
        pnlTypeStringToLongMap.put("alternative-investment-fund", 84L);
        pnlTypeStringToLongMap.put("aos-compliance-agent", 85L);
        pnlTypeStringToLongMap.put("crowdfunding-service-provider", 86L);
        pnlTypeStringToLongMap.put("person-obliged-under-1d-of-ZPPC", 87L);
        pnlTypeStringToLongMap.put("companies-for-additional-voluntary-unemployment-and-or-professional-insurance", 88L);
        pnlTypeStringToLongMap.put("default-value", -1L);
    }

    public Long getDescription(String pnlTypeString) {
        return pnlTypeStringToLongMap.getOrDefault(pnlTypeString, -1L);
    }
}
