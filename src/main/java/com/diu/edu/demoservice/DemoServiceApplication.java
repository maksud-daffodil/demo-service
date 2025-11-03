package com.diu.edu.demoservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@OpenAPIDefinition(
		info = @Info(title = "Demo Service API"),
		servers = {
				@Server(url = "http://localhost:6004", description = "Dev Env"),
				@Server(url = "https://gateway.diu.edu.bd", description = "Prod Env")
		},
		security = {@SecurityRequirement(name = "OpenIdConnect")}
)
@SecurityScheme(
        name = "OpenIdConnect",
        type = SecuritySchemeType.OPENIDCONNECT,
        openIdConnectUrl = "https://auth0.diu.edu.bd/realms/diu/.well-known/openid-configuration",
        description = "openid connect"
)
public class DemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceApplication.class, args);
	}

}
