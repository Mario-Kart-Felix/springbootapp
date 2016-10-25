package com.mastek.dna.api.it.profile;

import com.mastek.dna.model.Profile;
import org.junit.Before;

import java.util.Map;

public class ProfileEndpointExistingSuper extends ProfileEndpointSuper
{
	protected Profile existing;

	@Before
	public void loadEntity()
	{
		existing = profileLoader.loadEntity();
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
