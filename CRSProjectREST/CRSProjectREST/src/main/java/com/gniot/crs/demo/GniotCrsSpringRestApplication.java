package com.gniot.crs.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.gniot*")
public class GniotCrsSpringRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GniotCrsSpringRestApplication.class, args);
		System.out.println("RUNNING GNIOT_CRS_SPRING_REST");
	}

}
