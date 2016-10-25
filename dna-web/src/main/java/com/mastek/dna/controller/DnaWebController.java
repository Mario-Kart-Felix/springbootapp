package com.mastek.dna.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@Configuration
@ComponentScan
public class DnaWebController extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
{
	private static final String[] RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };

	@Override
	public void addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations(RESOURCE_LOCATIONS);
}

}