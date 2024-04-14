package com.questionpro.grocery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableOpenApi
@SpringBootApplication
public class QpAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(QpAssessmentApplication.class, args);
	}

}
