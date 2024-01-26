package bg.fsc.eis.integrations.edelivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import bg.fsc.eis.integrations.edelivery.config.EDeliveryProperties;

@SpringBootApplication
@EnableConfigurationProperties({EDeliveryProperties.class})
public class EDeliveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EDeliveryApplication.class, args);
    }
}
