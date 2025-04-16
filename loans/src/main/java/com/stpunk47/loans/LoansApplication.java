package com.stpunk47.loans;

import com.stpunk47.loans.dto.LoansContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(LoansContactInfoDto.class)
@OpenAPIDefinition(
		info = @Info(
				title = "Loans microservice REST API Documentation",
				description = "ST_Bank Loans microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Dmytro Doe",
						email = "dmytro@hostname.com",
						url = "https://www.hostname.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.hostname.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "ST_Bank Loans microservice REST API Documentation",
				url = "https://www.hostname.com/swagger-ui.html"
		)
)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
