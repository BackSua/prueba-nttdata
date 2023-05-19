package com.prueba.crudtaks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CrudTaksApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudTaksApplication.class, args);
	}

}
