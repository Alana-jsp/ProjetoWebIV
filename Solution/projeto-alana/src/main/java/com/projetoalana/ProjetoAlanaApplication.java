package com.projetoalana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ProjetoAlanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoAlanaApplication.class, args);
	}

	/**
	 * @return
	 */
	@Bean
	public Validator validator()
	{
		return new LocalValidatorFactoryBean();
	}
}
