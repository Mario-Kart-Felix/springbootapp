package com.mastek.dna.api.it.individual;

import java.util.Map;

import org.junit.Before;

import com.mastek.api.Individual;

public class IndividualEndpointExistingSuper extends IndividualEndpointSuper
{
	protected Individual existing;

	@Before
	public void loadEntity()
	{
		existing = individualLoader.loadEntity();
	}

	@Override
	protected String getUrl()
	{
		return super.getUrl() + "/{id}";
	}

	@Override
	protected Map<String, Object> getUrlVariables()
	{
		final Map<String, Object> map = super.getUrlVariables();
		map.put("id", existing.getId());
		return map;
	}
}
