package com.api.pgc.core.APIRestPGC.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    //Inicializacion de Swagger
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.pgc.core.APIRestPGC"))
                .paths(regex("/rest.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {
        ApiInfo ApiInfo = new ApiInfo(
                "SRECI - API PGC | Swagger",
                "Documentación de la API Rest | API PGC",
                "1.0",
                "Terms of Service",
                new Contact("SECRETARÍA DE RELACIONES EXTERIORES Y COOPERACIÓN INTERNACIONAL", "http://pgc.sre.gob.hn",
                        "nahum.sreci@gmail.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );

        return ApiInfo;
    }
}
