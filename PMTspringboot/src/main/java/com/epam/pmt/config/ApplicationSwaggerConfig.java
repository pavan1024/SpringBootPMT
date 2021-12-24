package com.epam.pmt.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ApplicationSwaggerConfig {

	@Bean
	public Docket pmtApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).build()
				.apiInfo(getDocumentInfo());
	}

	public ApiInfo getDocumentInfo() {
		return new ApiInfo("PMT Service API", "API for Password Management Tool", "1.0",
				"Terms of Service",
				new Contact("Pavan Kumar Shivashetty", "https://localhost:9000/master/", "pavankumar161024@gmail.com"),
				"Apache License Version", "https://apache.com", new ArrayList());
	}
}
