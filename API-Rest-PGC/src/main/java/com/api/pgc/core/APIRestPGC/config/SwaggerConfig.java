package com.api.pgc.core.APIRestPGC.config;


import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    //Inicializacion de Swagger
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-pgc")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.pgc.core.APIRestPGC"))
                .paths(regex("/rest.*"))
                .build()
                .securitySchemes(Lists.newArrayList(apiKey()))//Habilita la Inclucion de Autorization, para los EndPoint que lo Solicitan
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo ApiInfo = new ApiInfo(
                "API Rest PGC | SRECI",
                "Documentación de la API Rest | API PGC",
                "1.0",
                "Terms of Service",
                new Contact("| Nahúm Martinez - DCPD", "http://pgc.sre.gob.hn",
                        "nahum.sreci@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );
        //Return de Api
        return ApiInfo;
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}
