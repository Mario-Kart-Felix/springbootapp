package com.mastek.dna.api.it.individual;

import org.springframework.beans.factory.annotation.Autowired;

import com.mastek.dna.api.db.IndividualChecker;
import com.mastek.dna.api.it.EndpointSuper;

public class IndividualEndpointSuper extends EndpointSuper
{
	@Autowired
	protected IndividualLoader individualLoader;

	@Autowired
	protected IndividualChecker individualChecker;

	@Override
	protected String getUrl()
	{
		return "/individual";
	}
}
