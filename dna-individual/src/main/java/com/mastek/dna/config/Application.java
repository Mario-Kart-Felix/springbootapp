package com.mastek.dna.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "com.mastek.dna")
@EnableAutoConfiguration
@EntityScan(basePackages = "com.mastek.dna.model")
public class Application
{
	public static void main(final String... args)
	{
		SpringApplication.run(Application.class, args);
	}
}