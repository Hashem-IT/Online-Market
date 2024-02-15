package de.Hashem.bigbazar.Configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/*
 * Klasse für RestTemplateBuilderConfiguration
*/

@Configuration
public class RestTemplateBuilderConfiguration {
    @Bean
    public RestTemplate createRestTemplateBuilder(RestTemplateBuilder builder) {
        return builder.build();
    }
}
