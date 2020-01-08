package rentacar.mvp.configuration;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by savagaborov on 8.1.2020
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private final Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Bean
    public Docket swaggerSpringfoxDocket() {

        log.info("*** Initializing swagger ***");

        final StopWatch watch = new StopWatch();
        watch.start();

        final Docket docket = new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).genericModelSubstitutes(ResponseEntity.class).ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class).directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class).directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalTime.class, Date.class).select().paths(PathSelectors.regex("/api/.*")).build();

        watch.stop();
        log.info("*** Swagger started in {} ms ***", watch.getTotalTimeMillis());
        return docket;
    }

}
