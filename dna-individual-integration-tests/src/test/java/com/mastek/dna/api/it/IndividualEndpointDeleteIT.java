package com.mastek.dna.api.it;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.mastek.dna.model.Individual;

public class IndividualEndpointDeleteIT extends IndividualEndpointSuper
{
	private Individual existing;

	@Before
	public void loadEntity()
	{
		final int id = 1;

		existing = new Individual()
				.setId(id);
	}

	@Test
	public void testDelete()
	{
		send();

		// TODO : Assert the DB
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
