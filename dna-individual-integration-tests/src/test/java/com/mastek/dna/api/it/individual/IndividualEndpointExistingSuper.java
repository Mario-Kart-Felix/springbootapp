package com.mastek.dna.api.it.individual;

import java.util.Map;

import org.junit.Before;

import com.mastek.dna.model.Individual;

public class IndividualEndpointExistingSuper extends IndividualEndpointSuper
{
	protected Individual existing;

	@Before
	public void loadEntity()
	{
		existing = jdbcTemplate.queryForObject("SELECT * FROM individual i "
				+ "LEFT JOIN address a ON a.individual_id = i.individual_id "
				+ "WHERE a.individual_id IS NULL "
				+ "LIMIT 1",
				IndividualUtil.ROW_MAPPER);
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
