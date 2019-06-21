package com.junebookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiEndPointsInfo())
                .tags(
                        new Tag("Books", "REST API For Books"),
                        new Tag("Users", "REST API For Users")
                )
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.junebookstore.controller"))
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder()
                .title("BookStore REST API")
                .contact(new Contact(
                        "Weeravit Sirita",
                        "",
                        "weeravit.sirita@gmail.com"
                ))
                .version("1.0.0")
                .build();
    }
}