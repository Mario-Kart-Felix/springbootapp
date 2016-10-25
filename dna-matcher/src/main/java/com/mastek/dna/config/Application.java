package com.mastek.dna.config;

import javax.persistence.NamedStoredProcedureQuery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mastek.dna")
@EnableAutoConfiguration
//@EntityScan(basePackages = "com.mastek.dna.model")
//@EnableJpaRepositories(basePackages = "com.mastek.dna.dao")
//@EnableEurekaClient
//@EnableAspectJAutoProxy

public class Application
{
	public static void main(final String... args)
	{
		
		SpringApplication.run(Application.class, args);
	}
}