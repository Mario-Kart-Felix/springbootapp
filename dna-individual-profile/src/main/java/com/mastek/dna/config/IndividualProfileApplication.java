package com.mastek.dna.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(scanBasePackages = "com.mastek.dna")
@EnableAutoConfiguration
@EntityScan(basePackages = "com.mastek.dna.model")
@EnableJpaRepositories(basePackages = "com.mastek.dna.dao")
public class IndividualProfileApplication
{
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
	public static void main(final String... args)
	{
		SpringApplication.run(IndividualProfileApplication.class, args);
	}
}