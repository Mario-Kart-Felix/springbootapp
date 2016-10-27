package com.mastek.dna.service;

import com.mastek.api.Individual;
import com.mastek.feign.config.FeignIndividualClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "dna-individual",configuration = FeignIndividualClientConfiguration.class)
public interface IndividualClient {

	@RequestMapping(method = RequestMethod.GET, value = "/individual/{id}")
    Individual find(@PathVariable("id") final int id);
}