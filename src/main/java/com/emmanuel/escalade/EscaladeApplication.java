package com.emmanuel.escalade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication  /* Précise la classe de démarrage = @Configuration @EnableAutoConfiguration @ComponentScan */
//@EnableJpaRepositories
public class EscaladeApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EscaladeApplication.class, args);
	}

}
