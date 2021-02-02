package co.example.config;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.webflux.core.converters.WebFluxSupportConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        ModelConverters.getInstance().addConverter(new WebFluxSupportConverter());

        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("JWE", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWE")))
                .info(new Info()
                        .title("Clothes store API")
                        .version("v0.0.1"));
    }
}
