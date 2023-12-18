package com.fsc.external.portal.utils;

import org.mapstruct.Qualifier;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
public class RoundingUtil {

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface RoundingTwoAfterDecimalPoint { }

    @RoundingTwoAfterDecimalPoint
    public BigDecimal roundingTwoAfterDecimalPoint(BigDecimal in) {
        return in == null ? BigDecimal.ZERO : in.setScale(2, RoundingMode.HALF_UP);
    }
}
