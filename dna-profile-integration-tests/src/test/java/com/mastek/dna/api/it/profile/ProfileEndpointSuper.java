package com.mastek.dna.api.it.profile;

import com.mastek.dna.api.it.profile.EndpointSuper;
import com.mastek.dna.api.it.profile.ProfileLoader;
import com.mastek.dna.db.ProfileChecker;
import org.springframework.beans.factory.annotation.Autowired;


public class ProfileEndpointSuper extends EndpointSuper
{

	@Autowired
	protected ProfileLoader profileLoader;

	@Autowired
	protected ProfileChecker profileChecker;

	@Override
	protected String getUrl()
	{
		return "/profile";
	}
}
