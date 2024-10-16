package com.caelestis.accounts;

import com.caelestis.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "Accounts microservice bank integrated with many others microservice",
				version = "v1",
				contact = @Contact(
						name = "Jefferson Celestino",
						email = "contato@jeffersoncelestino.dev.br",
						url = "https://www.linkedin.com/in/caelestis/"
				),
				license = @License(
						name = "Licence: Apache 2.0",
						url = "https://www.linkedin.com/in/caelestis/"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Accounts microservice REST API External Documentation",
				url = "https://www.linkedin.com/in/caelestis/"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
