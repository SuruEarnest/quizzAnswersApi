package com.odysseyenergysolutions.exercise.quiz.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@ComponentScan("com.odysseyenergysolutions.exercise.quiz.config")
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {


    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.odysseyenergysolutions.exercise.quiz.controller"))
                .paths(regex("/answers.*")).build().apiInfo(metaData())
                .securitySchemes(Arrays.asList(apiKey()));
    }


    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Odyssey Energy Open API Documentation.")
                .description("This API provides endpoints for Odyssey Energy Solutions.")
                .version("1.0.0")
                .license("Odyssey Energy Solutions License Version 1.0")
                .contact(new Contact("Earnest Suru", "https://www.linkedin.com/in/suru-earnest/", "earnest.suru@gmail.com"))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("apiKey", "Authorization", "header");
    }


}
