package com.mastek.dna.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mastek.dna.model.Profile;
import com.mastek.dna.model.validator.Api;
import com.mastek.dna.service.ProfileService;

@RestController("/profile")
public class ProfileEndpoint
{
	@Autowired
	private ProfileService profileService;

	@RequestMapping(method = { RequestMethod.POST, RequestMethod.PUT })
	public Profile create(@RequestBody @Validated(Api.class) final Profile profile)
	{
		return profileService.create(profile);
	}

	@RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
	public Profile update(@PathVariable final int id, @RequestBody @Validated final Profile profile)
	{
		profile.setId(id);
		return profileService.update(profile);
	}

	@RequestMapping(value = "/profile/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable final int id)
	{
		profileService.delete(id);
	}
}
