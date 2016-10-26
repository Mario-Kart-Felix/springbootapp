package com.mastek.dna.api;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;

import com.mastek.dna.model.Individual;
import com.mastek.dna.model.IndividualProfile;
import com.mastek.dna.model.Profile;
import com.mastek.dna.model.validator.Api;
import com.mastek.dna.util.ServiceUtils;

@RestController
public class IndividualProfileEndpoint {
	private static final Logger LOG = LoggerFactory.getLogger(IndividualProfileEndpoint.class);
	private static final String SERVICE_URL = "/individual-prifile";

	private static final String INDIVIDUAL_FALLBACK_URL = "http://localhost:8080/individual";
	private static final String PROFILE_FALLBACK_URL = "http://localhost:8081/profile";

	@Value("${api.username}")
	private String apiUsername;

	@Value("${api.password}")
	private String apiPassword;

	@Autowired
	private ServiceUtils utils;
	
    @Autowired
    private RestOperations restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancer;

	@PostMapping(SERVICE_URL)
	@PutMapping(SERVICE_URL)
	public IndividualProfile create(@RequestBody @Validated(Api.class) final IndividualProfile individualProfile) {
		LOG.info("Composite service started");

		// Create Individual
		createIndividual(individualProfile.getIndividual());
		LOG.info("Individual  service called");
		// Create profile
		individualProfile.getProfile().setIndividualId(individualProfile.getIndividual().getId());
		createProfile(individualProfile.getProfile());
		LOG.info("Profile  service called");

		LOG.debug("Composite service finished");
		return individualProfile;
	}

	private void createIndividual(Individual individual) {
		LOG.info("individual request processing started");
		LOG.info("individual is " + individual.toString());
		URI uri = utils.getServiceUrl("dna-individual");
		LOG.info("Got indivisdual servicer uri  "+uri.getPath());
		ResponseEntity<Individual> response = restTemplate.exchange(uri+"/individual", HttpMethod.POST,
				new HttpEntity<>(individual, utils.createHeaders(apiUsername, apiPassword)), Individual.class);
		final Individual responseObject = response.getBody();
		individual.setId(responseObject.getId());
		LOG.info("individual request processing finished");
	}


	private void createProfile(Profile profile) {
		LOG.info("profile request processing started");
		URI uri = utils.getServiceUrl("dna-profile");
		LOG.info("Got profile server uri  "+uri.getPath());
		ResponseEntity<Profile> response = restTemplate.exchange(uri+"/profile", HttpMethod.POST,
				new HttpEntity<>(profile, utils.createHeaders(apiUsername, apiPassword)), Profile.class);
		final Profile responseObject = response.getBody();
		profile.setId(responseObject.getId());
		LOG.info("profile request processing finished");
	}



}
