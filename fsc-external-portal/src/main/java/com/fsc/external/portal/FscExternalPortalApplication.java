package com.fsc.external.portal;

import com.google.common.base.Strings;
import io.jmix.core.repository.EnableJmixDataRepositories;
import io.jmix.notifications.NotificationType;
import io.jmix.notifications.NotificationTypesRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@SpringBootApplication
@EnableJmixDataRepositories
@EnableCaching
@EnableScheduling
public class FscExternalPortalApplication {

    @Autowired
    private Environment environment;
    @Autowired
    private NotificationTypesRepository notificationTypesRepository;

    public static void main(String[] args) {
        SpringApplication.run(FscExternalPortalApplication.class, args);
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource")
    DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("main.datasource.hikari")
    DataSource dataSource(@Qualifier("dataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean(name = "migrationDataSourceProperties")
    @ConfigurationProperties("migration.datasource")
    DataSourceProperties migrationDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "migrationDataSource")
    @ConfigurationProperties("migration.datasource.hikari")
    DataSource migrationDataSource(@Qualifier("migrationDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder().build();
    }

    @EventListener
    public void printApplicationUrl(ApplicationStartedEvent event) {
        LoggerFactory.getLogger(FscExternalPortalApplication.class).info("Application started at "
                + "http://localhost:"
                + environment.getProperty("local.server.port")
                + Strings.nullToEmpty(environment.getProperty("server.servlet.context-path")));
    }

    @PostConstruct
    public void postConstruct() {
        notificationTypesRepository.registerTypes(
                new NotificationType("general", "GENERAL")
        );
    }
}
