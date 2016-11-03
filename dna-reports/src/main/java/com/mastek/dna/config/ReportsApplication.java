package com.mastek.dna.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.mastek.dna")
@EnableAutoConfiguration
public class ReportsApplication
{
    public static void main(final String... args)
    {
        SpringApplication.run(ReportsApplication.class, args);
    }

}