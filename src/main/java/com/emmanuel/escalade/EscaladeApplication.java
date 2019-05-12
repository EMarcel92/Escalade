package com.emmanuel.escalade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication  /* = @Configuration @EnableAutoConfiguration @ComponentScan */
public class EscaladeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscaladeApplication.class, args);
	}

/*

	@RequestMapping(value="/")
	@ResponseBody
	public String bootup()
	{
		return "SpringBoot is up and running";
	}
*/

}
