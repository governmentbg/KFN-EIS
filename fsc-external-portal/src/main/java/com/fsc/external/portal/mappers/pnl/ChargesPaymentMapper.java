package com.fsc.external.portal.mappers.pnl;

import com.fsc.common.addon.entity.accounting.payment.ChargesPayment;
import com.fsc.external.portal.dtos.pnl.charges.ChargesPaymentDetailResponse;
import com.fsc.external.portal.utils.RoundingUtil;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RoundingUtil.class})
public abstract class ChargesPaymentMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "createdOn", source = "createdOn")
    @Mapping(target = "currencyMainCode", source = "currencyMain.codeLetters")
    @Mapping(target = "currencySecondCode", source = "currencySecond.codeLetters")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "principalValueMain", source = "principalValueMain",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "principalValueSecond", source = "principalValueSecond",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "interestValueMain", source = "interestValueMain", qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Mapping(target = "interestValueSecond", source = "interestValueSecond",
            qualifiedBy = RoundingUtil.RoundingTwoAfterDecimalPoint.class)
    @Named("toDto")
    abstract ChargesPaymentDetailResponse toPaymentDetail(ChargesPayment chargesPayment);

    @IterableMapping(qualifiedByName = "toDto")
    public abstract Set<ChargesPaymentDetailResponse> toPaymentDetails(Set<ChargesPayment> chargesPayments);
}