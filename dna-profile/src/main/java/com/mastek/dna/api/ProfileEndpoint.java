package com.mastek.dna.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.mastek.dna.model.Profile;
import com.mastek.dna.model.validator.Api;
import com.mastek.dna.service.ProfileService;

import java.util.List;

@RestController
public class ProfileEndpoint
{
	private static final String ROOT_URL = "/profile";
	private static final String URL_WITH_ID = ROOT_URL + "/{id}";

	@Autowired
	private ProfileService profileService;

	@PostMapping(ROOT_URL)
	@PutMapping(ROOT_URL)
	@ResponseStatus(HttpStatus.CREATED)
	public Profile create(@RequestBody @Validated(Api.class) final Profile profile)
	{
		return profileService.create(profile);
	}

	@PutMapping(URL_WITH_ID)
	public Profile update(@PathVariable final int id, @RequestBody @Validated final Profile profile)
	{
		profile.setId(id);
		return profileService.update(profile);
	}

	@DeleteMapping(URL_WITH_ID)
	public void delete(@PathVariable final int id)
	{
		profileService.delete(id);
	}

	@GetMapping(URL_WITH_ID)
	public Profile find(@PathVariable final int id)
	{
		return profileService.find(id);
	}

	@GetMapping(ROOT_URL)
	public List<Profile> find()
	{
		return profileService.findAll();
	}

}
