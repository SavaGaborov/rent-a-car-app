package rentacar.mvp.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Created by savagaborov on 8.1.2020
 */
@Data
@Configuration
@EnableConfigurationProperties(CustomProperties.class)
@ConfigurationProperties(prefix = "custom", ignoreUnknownFields = false)
@Validated
public class CustomProperties {
}
