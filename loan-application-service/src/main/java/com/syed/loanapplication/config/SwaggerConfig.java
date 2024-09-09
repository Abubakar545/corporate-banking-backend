package com.syed.loanapplication.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loan Application Service API")
                        .version("1.0")
                        .description("This API provides endpoints to manage loan applications within the Corporate Banking Loan Management System (CBLMS).")
                        .termsOfService("https://www.syedloanapplication.com/terms")
                        .contact(new Contact()
                                .name("Abubakar Siddiq")
                                .url("https://www.linkedin.com/in/abubakarsiddiq/")
                                .email("sayedsiddiq45@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local server"),
                        new Server().url("https://api.syedloanapplication.com").description("Production server")
                ));
    }
}
