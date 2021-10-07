package java12.projectsalemanagement.config;

import java.util.Arrays;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey())).select()
                .apis(RequestHandlerSelectors.basePackage("cybersoft.java12.gira")).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Cybersoft Gira Application")
                .description("This project is used for education purpose only.")
                .contact(new Contact("Tu Quang Huy", ".dev", "contact@huy.dev")).license("MIT2").build();
    }

    private ApiKey apiKey() {// Apikey la ten header
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {// spring fox
        return SecurityContext.builder().securityReferences(swaggerAuth()).build();
    }

    private List<SecurityReference> swaggerAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("gobal", "use in all request");
        AuthorizationScope[] scope = new AuthorizationScope[1];
        scope[0] = authorizationScope;
        SecurityReference ref = new SecurityReference("JWT", scope);
        return Arrays.asList(ref);
    }
}
