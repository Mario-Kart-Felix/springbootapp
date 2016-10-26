package com.mastek.dna.api.composite.it;

import org.springframework.beans.factory.annotation.Autowired;

import com.mastek.dna.api.db.IndividualChecker;
import com.mastek.dna.api.it.EndpointSuper;

public class IndividualProfileEndpointSuper extends EndpointSuper
{
	@Autowired
	protected IndividualChecker individualChecker;
	
	@Override
	protected String getUrl()
	{
		return "/individual-prifile";
	}
}
