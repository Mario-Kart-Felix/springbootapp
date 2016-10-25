package com.mastek.dna.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mastek.dna.model.Individual;
import com.mastek.dna.model.validator.Api;
import com.mastek.dna.service.IndividualService;

@RestController
public class IndividualEndpoint
{
	private static final String ROOT_URL = "/individual";
	private static final String URL_WITH_ID = ROOT_URL + "/{id}";

	@Autowired
	private IndividualService individualService;

	@PostMapping(ROOT_URL)
	@PutMapping(ROOT_URL)
	@ResponseStatus(HttpStatus.CREATED)
	public Individual create(@RequestBody @Validated(Api.class) final Individual individual)
	{
		return individualService.create(individual);
	}

	@PutMapping(URL_WITH_ID)
	public Individual update(@PathVariable final int id, @RequestBody @Validated final Individual individual)
	{
		individual.setId(id);
		return individualService.update(individual);
	}

	@DeleteMapping(URL_WITH_ID)
	public void delete(@PathVariable final int id)
	{
		individualService.delete(id);
	}

	@GetMapping(URL_WITH_ID)
	public Individual find(@PathVariable final int id)
	{
		return individualService.find(id);
	}

	@GetMapping(ROOT_URL)
	public List<Individual> find()
	{
		return individualService.findAll();
	}
}