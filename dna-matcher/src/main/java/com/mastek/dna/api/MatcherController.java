package com.mastek.dna.api;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class MatcherController
{
	private static final String ROOT_URL = "/fullmatcher";
	
	
	@GetMapping(ROOT_URL)
	public String home() {
        return "Hello World!";
    }
	

}