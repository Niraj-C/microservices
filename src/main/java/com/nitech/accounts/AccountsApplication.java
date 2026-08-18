package com.nitech.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*
	@ComponentScan({@ComponentScan("com.nitech.accounts.controller")})
	@EnableJpaRepositories("com.nitech.accounts.repository)
	@EntityScan("com.nitech.accounts.model")
	this are use when your packeges are out from the com.nitech.account
 */

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Account Microservices REST API Documentation",
				description = "Simple Account Bank microservice REST API Documentation",
				version = "v2",
				contact = @Contact(
						name = "Niraj Chaudhary",
						email = "bankAccount@service.com",
						url = "https://mail.google.com/mail/u/0/#inbox" +
								"http://localhost:8080/swagger-ui/index.html"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://localhost:8080/swagger-ui/index.html"

				)

		),
		externalDocs = @ExternalDocumentation(
				description = "Easy Bank Account Microservice REST API Documentation",
				url = "https://mail.google.com/mail/u/3/#inbox"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
