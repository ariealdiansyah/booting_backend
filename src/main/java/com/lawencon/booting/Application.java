package com.lawencon.booting;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public Docket swaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.lawencon.booting.controller"))
				.build()
				.apiInfo(apiDetails());
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Booting Aplication API",
				"Api Documentation",
				"1.0",
				"",
				new springfox.documentation.service.Contact("PT.Lawencon International", "https://www.lawencon.com", "dummy@gmail.com"),
				"",
				"",
				Collections.emptyList());
	}
	
	

}
