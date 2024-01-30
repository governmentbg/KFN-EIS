package bg.fsc.eis.integrations.edelivery.config;

import lombok.Data;

import static bg.fsc.eis.integrations.edelivery.config.EDeliveryProperties.LOCATION;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = LOCATION)
@Data
public class EDeliveryProperties {

    public static final String LOCATION = "fsc.eis.integrations.edelivery";

}
