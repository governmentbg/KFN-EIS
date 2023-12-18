package com.fsc.external.portal.mappers.pnl;

import com.fsc.common.addon.entity.view.OrderedCharges;
import com.fsc.external.portal.dtos.pnl.charges.PnlChargesResponse;
import com.fsc.external.portal.mappers.BasePageResponseMapper;
import com.fsc.external.portal.utils.RoundingUtil;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = RoundingUtil.class)
public abstract class PnlChargesMapper implements BasePageResponseMapper<OrderedCharges, PnlChargesResponse> {

    @Mapping(target = "chargeId", source = "charges.id")
    @Mapping(target = "chargeType", source = "charges.chargesTypeName")
    @Mapping(target = "chargeDate", source = "charges.createdOn")
    @Mapping(target = "chargeValueMain", source = "charges", qualifiedByName = "totalMainValue")
    @Mapping(target = "chargeValueMainCurrency", source = "charges.mainCurrencyCodeLetters")
    @Mapping(target = "chargeValueSecond", source = "charges", qualifiedByName = "totalSecondValue")
    @Mapping(target = "chargeValueSecondCurrency", source = "charges.secondCurrencyCodeLetters")
    @Mapping(target = "paymentDueDate", source = "charges.dueDate")
    @Mapping(target = "status", source = "charges.status")
    @Mapping(target = "currentPrincipalValueMain", source = "charges.currentPrincipalValueMain",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "currentPrincipalValueSecond", source = "charges.currentPrincipalValueSecond",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "currentInterestValueMain", source = "charges.currentInterestValueMain",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "currentInterestValueSecond", source = "charges.currentInterestValueSecond",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    public abstract PnlChargesResponse toResponse(OrderedCharges charges);

    @Named("totalMainValue")
    public BigDecimal totalMainValue(OrderedCharges charges) {
        BigDecimal valueMain = charges.getPrincipalValueMain() == null ? BigDecimal.ZERO : charges.getPrincipalValueMain();
        BigDecimal interestMain = charges.getInterestValueMain() == null ? BigDecimal.ZERO : charges.getInterestValueMain();

        return valueMain.add(interestMain).setScale(2, RoundingMode.HALF_UP);
    }

    @Named("totalSecondValue")
    public BigDecimal totalSecondValue(OrderedCharges charges) {
        BigDecimal valueSecond = charges.getPrincipalValueSecond() == null ? BigDecimal.ZERO : charges.getPrincipalValueSecond();
        BigDecimal interestSecond = charges.getInterestValueSecond() == null ? BigDecimal.ZERO : charges.getInterestValueSecond();

        return valueSecond.add(interestSecond).setScale(2, RoundingMode.HALF_UP);
    }
}


