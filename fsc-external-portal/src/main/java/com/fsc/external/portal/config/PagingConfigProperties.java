package com.fsc.external.portal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
@Configuration
@ConfigurationProperties("paging")
public class PagingConfigProperties {

    @NotNull
    private int defaultNumberOfPages;

    @NotNull
    private int defaultSize;

    public int getDefaultNumberOfPages() {
        return defaultNumberOfPages;
    }

    public void setDefaultNumberOfPages(int defaultNumberOfPages) {
        this.defaultNumberOfPages = defaultNumberOfPages;
    }

    public int getDefaultSize() {
        return defaultSize;
    }

    public void setDefaultSize(int defaultSize) {
        this.defaultSize = defaultSize;
    }
}
