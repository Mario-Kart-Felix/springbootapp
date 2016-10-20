package com.mastek.dna.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mastek.dna.model.Address;
import com.mastek.dna.model.validator.Api;
import com.mastek.dna.service.AddressService;

@RestController
public class AddressEndpoint
{
	private static final String NEW_URL = "/individual/{individualId}/address";
	private static final String EXISTING_URL = NEW_URL + "/{id}";

	@Autowired
	private AddressService addressService;

	@PostMapping(NEW_URL)
	@PutMapping(NEW_URL)
	@ResponseStatus(HttpStatus.CREATED)
	public Address create(@PathVariable final int individualId, @RequestBody @Validated(Api.class) final Address address)
	{
		return addressService.create(individualId, address);
	}

	@PutMapping(EXISTING_URL)
	public Address update(@PathVariable final int id, @RequestBody @Validated final Address address)
	{
		address.setId(id);
		return addressService.update(address);
	}

	@DeleteMapping(EXISTING_URL)
	public void delete(@PathVariable final int id)
	{
		addressService.delete(id);
	}
}