package com.fsc.external.portal.mappers.pnl;

import com.fsc.common.addon.entity.accounting.charges.Charges;
import com.fsc.common.addon.entity.accounting.other.PaymentRequest;
import com.fsc.common.addon.entity.accounting.other.TotalObligationsLeftView;
import com.fsc.external.portal.dtos.pnl.charges.ChargesDetailResponse;
import com.fsc.external.portal.dtos.pnl.charges.ChargesPaymentDetailResponse;
import com.fsc.external.portal.utils.RoundingUtil;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDateTime;
import java.util.Set;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = RoundingUtil.class)
public abstract class ChargesDetailMapper {

    @Mapping(target = "chargeId", source = "charges.id")
    @Mapping(target = "personId", source = "charges.person.id")
    @Mapping(target = "chargeTypeName", source = "charges.type.name")
    @Mapping(target = "createdOn", source = "charges.createdOn")
    @Mapping(target = "principalValueMain", source = "charges.principalValueMain",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "principalValueSecond", source = "charges.principalValueSecond",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "interestValueMain", source = "charges.interestValueMain",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "interestValueSecond", source = "charges.interestValueSecond",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "dueDate", source = "charges.dueDate")
    @Mapping(target = "status", source = "charges.status")
    @Mapping(target = "periodFrom", source = "charges.periodFrom")
    @Mapping(target = "periodTo", source = "charges.periodTo")
    @Mapping(target = "currencyMainCode", source = "charges.currencyMain.codeLetters")
    @Mapping(target = "currencySecondCode", source = "charges.currencySecond.codeLetters")
    @Mapping(target = "notPaidMainPrincipal", source = "totalObligationsLeftView.mainPrincipal",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "notPaidMainInterest", source = "totalObligationsLeftView.mainInterest",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "notPaidMainTotal", source = "totalObligationsLeftView.mainTotal",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "notPaidSecondPrincipal", source = "totalObligationsLeftView.secondPrincipal",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "notPaidSecondInterest", source = "totalObligationsLeftView.secondInterest",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "notPaidSecondTotal", source = "totalObligationsLeftView.secondTotal",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "chargesPayments", source = "paymentDetailResponses")
    @Mapping(target = "serviceName", source = "serviceName")
    @Mapping(target = "paymentAccessCode", source = "charges.paymentRequest", qualifiedByName = "mapPaymentAccessCode")
    public abstract ChargesDetailResponse toDetailResponse(Charges charges, Set<ChargesPaymentDetailResponse> paymentDetailResponses,
                                                           TotalObligationsLeftView totalObligationsLeftView, String serviceName);

    /**
     * check if PaymentRequest is expired.
     * @param paymentRequest object
     * @return payment access code or null
     */
    @Named("mapPaymentAccessCode")
    public String mapPaymentAccessCode(PaymentRequest paymentRequest) {
        if (LocalDateTime.now().isBefore(paymentRequest.getExpirationDate())) {
            return paymentRequest.getAccessCode();
        }
        return null;
    }
}
