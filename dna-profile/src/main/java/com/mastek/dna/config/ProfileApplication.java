package com.mastek.dna.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.mastek.dna")
@EnableAutoConfiguration
@EntityScan(basePackages = "com.mastek.dna.model")
@EnableJpaRepositories(basePackages = "com.mastek.dna.dao")
@EnableEurekaClient
@EnableAspectJAutoProxy
public class ProfileApplication
{
	public static void main(final String[] args)
	{
		SpringApplication.run(ProfileApplication.class, args);
	}
}
