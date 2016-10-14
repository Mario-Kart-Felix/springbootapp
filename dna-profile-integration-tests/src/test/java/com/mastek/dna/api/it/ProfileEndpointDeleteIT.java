package com.mastek.dna.api.it;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.mastek.dna.model.Profile;

public class ProfileEndpointDeleteIT extends ProfileEndpointSuper
{
	private Profile existing;

	@Before
	public void loadEntity()
	{
		existing = new Profile();
		existing.setId(1);
	}

	@Test
	public void testDelete()
	{
		send();
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

	private <I, O> O send()
	{
		return send(null, HttpMethod.DELETE, null);
	}
}
