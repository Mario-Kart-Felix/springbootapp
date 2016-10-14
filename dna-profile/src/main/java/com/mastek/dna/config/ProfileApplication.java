package com.mastek.dna.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mastek.dna")
public class ProfileApplication
{
	public static void main(final String[] args)
	{
		SpringApplication.run(ProfileApplication.class, args);
	}
}
