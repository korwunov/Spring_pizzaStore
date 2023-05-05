package com.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories("com.pizzeria.repositories")	//решило проблему с бинами репозиториев для сервисов
@EnableWebSecurity
@EnableWebMvc
public class Pizzeria {
	public static void main(String[] args) {
		SpringApplication.run(Pizzeria.class, args);
	}

}
