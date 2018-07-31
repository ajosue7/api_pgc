package com.api.pgc.core.APIRestPGC.config.swagger;


import com.api.pgc.core.APIRestPGC.utilities.configAPI;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.PathProvider;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

import static com.api.pgc.core.APIRestPGC.utilities.configAPI.API_BASE_PATH;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig implements WebMvcConfigurer {

    // Constantes de la API
    private configAPI configApi;
    private ServletContext servletContext;

    //Inicializacion de Swagger
    @Bean
    public Docket pgcApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // .pathMapping("nam")
                .pathProvider(pathProvider())
                .groupName("api-pgc")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.api.pgc.core.APIRestPGC"))
                .paths(regex( configAPI.API_BASE_PATH + ".*"))
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
                new Contact("| Nahúm Martinez ", "http://pgc.sre.gob.hn",
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

    private PathProvider pathProvider() {
        return new RelativePathProvider(servletContext) {
            @Override
            protected String applicationPath() {
                return "/testAPI/v2/test/doc";
            }
        };
    }

}
