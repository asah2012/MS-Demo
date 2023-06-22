package com.torq.inventory;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title="Inventory - Spring Boot Rest API documentation",
				description = "Inventory - Spring Boot Rest API documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Amrit",
						email = "torquewms@gmail.com",
						url = "https://torqchain.io"
				),
				license = @License(
						name = "Apache 2.0",
						url="https://torqchain.io"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring Boot Inventory documentation",
				url="https://torqchain.io"
		)
)
public class InventoryApplication {
	/*
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
*/
	@Bean
	public WebClient webClient(){
		return WebClient.builder().build();
	}
	/*public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	 */
	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

}
