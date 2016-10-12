package com.mastek.dna.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mastek.dna.model.Individual;
import com.mastek.dna.model.validator.Create;
import com.mastek.dna.service.IndividualService;

@RestController("/individual")
public class IndividualEndpoint
{
	@Autowired
	private IndividualService individualService;

	@PostMapping
	@PutMapping
	@ResponseBody
	public Individual create(@RequestBody @Validated(Create.class) final Individual individual)
	{
		return individualService.create(individual);
	}

	@PutMapping("/individual/{id}")
	@ResponseBody
	public Individual update(@PathVariable final int id, @RequestBody @Validated final Individual individual)
	{
		// TODO : Discuss if the ID should be set in the body, then validated
		// that its the same as the URL path parameter
		individual.setId(id);
		return individualService.update(individual);
	}

	@DeleteMapping("/individual/{id}")
	public void delete(@PathVariable final int id)
	{
		individualService.delete(id);
	}
}