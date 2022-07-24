package br.com.glandata.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class GlandataWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlandataWebApplication.class, args);
	}

}
