package br.com.tiagopedroso.dioparkingchallenge.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    /*
    Tips: Migrating from SpringFox
    https://springdoc.org/#migrating-from-springfox

    Swagger URL: http://localhost:8080/swagger-ui.html
     */

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("controller")
                .pathsToMatch("/api/v1/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DioParkingChallenge Sever")
                        .description("DIO's challenge for a car parking microservice.")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("DioParkingChallenge - Source code")
                        .url("https://github.com/supertsp/dio-parking-challenge")
                );
    }




//    private SecurityContext actuatorSecurityContext() {
//        return SecurityContext.builder()
//                .securityReferences(Arrays.asList(basicAuthReference()))
//                .build();
//    }
//
//    private SecurityScheme basicAuthScheme() {
//        return new BasicAuth("basicAuth");
//    }
//
//    private SecurityReference basicAuthReference() {
//        return new SecurityReference("basicAuth", new AuthorizationScope[0]);
//    }
//
//    private List<SecurityScheme> basicScheme() {
//        List<SecurityScheme> schemeList = new ArrayList<>();
//        schemeList.add(new BasicAuth("basicAuth"));
//        return schemeList;
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("apiKey", "Authorization", "header");
//    }

}
