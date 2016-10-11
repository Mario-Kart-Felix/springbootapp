package com.mastek.dna.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Individual create(@RequestBody @Validated(Create.class) final Individual individual)
	{
		return individualService.create(individual);
	}
}