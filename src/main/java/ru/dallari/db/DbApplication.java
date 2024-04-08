package ru.dallari.db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.ui.Model;

@EnableMethodSecurity
@SpringBootApplication
public class DbApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}

}
