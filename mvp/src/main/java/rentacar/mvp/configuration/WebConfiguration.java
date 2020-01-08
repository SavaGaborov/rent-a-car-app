package rentacar.mvp.configuration;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by savagaborov on 8.1.2020
 */
@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfiguration implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(WebConfiguration.class);

    @Bean
    public CorsFilter corsFilter() {

        log.info("*** Initializing CORS filter ***");

        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("PATCH");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("OPTIONS");

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("**/swagger-ui.html", config);
        source.registerCorsConfiguration("/v2/api-docs/**", config);

        return new CorsFilter(source);
    }
}
