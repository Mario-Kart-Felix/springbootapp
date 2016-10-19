package com.mastek.dna.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroServiceRegistry {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceRegistry.class, args);
    }
}