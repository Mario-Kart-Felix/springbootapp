package com.mastek.dna.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(scanBasePackages = "com.mastek.dna")
@EnableEurekaClient
public class ProfileApplication
{
	public static void main(final String[] args)
	{
		SpringApplication.run(ProfileApplication.class, args);
	}
}
