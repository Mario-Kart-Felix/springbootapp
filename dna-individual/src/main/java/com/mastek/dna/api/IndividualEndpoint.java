package com.mastek.dna.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mastek.dna.model.Individual;
import com.mastek.dna.model.validator.Api;
import com.mastek.dna.service.IndividualService;

@RestController
public class IndividualEndpoint
{
	private static final String NEW_URL = "/individual";
	private static final String EXISTING_URL = "/individual/{id}";

	@Autowired
	private IndividualService individualService;

	@PostMapping(NEW_URL)
	@PutMapping(NEW_URL)
	public Individual create(@RequestBody @Validated(Api.class) final Individual individual)
	{
		return individualService.create(individual);
	}

	@PutMapping(EXISTING_URL)
	public Individual update(@PathVariable final int id, @RequestBody @Validated final Individual individual)
	{
		individual.setId(id);
		return individualService.update(individual);
	}

	@DeleteMapping(EXISTING_URL)
	public void delete(@PathVariable final int id)
	{
		individualService.delete(id);
	}
}