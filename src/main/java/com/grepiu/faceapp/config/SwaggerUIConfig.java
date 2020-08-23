package com.grepiu.faceapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 스웨거 설정 (Open API 3)
 */
@Configuration
public class SwaggerUIConfig {

  @Bean
  public OpenAPI customOpenAPI(BuildProperties buildProperties) {
    return new OpenAPI()
        .components(
            new Components()
                .addSecuritySchemes(
                    "basicScheme",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP
                        )
                        .scheme("basic")))
        .info(
            new Info()
                .title("GrepIU Face APP API")
                .version(buildProperties.getVersion())
                .description("페이스 앱 API 서버")
                .termsOfService("http://swagger.io/terms/")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }
}
