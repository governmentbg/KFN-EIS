package bg.fsc.eis.integrations.edelivery.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SwaggerAutoConfiguration {

    @Value("${spring.application.name}")
    private String title;

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info().title(title));
    }
}
