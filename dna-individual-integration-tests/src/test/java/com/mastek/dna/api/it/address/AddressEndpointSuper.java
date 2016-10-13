package com.mastek.dna.api.it.address;

import java.util.Map;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import com.mastek.dna.api.db.AddressChecker;
import com.mastek.dna.api.it.EndpointSuper;

public class AddressEndpointSuper extends EndpointSuper
{
	@Autowired
	protected AddressChecker addressChecker;

	protected int individualId;

	@Before
	public void loadIndividual()
	{
		individualId = jdbcTemplate.queryForObject("SELECT individual_id FROM individual LIMIT 1", Integer.class);
	}

	@Override
	protected String getUrl()
	{
		return "/individual/{individualId}/address";
	}

	@Override
	protected Map<String, Object> getUrlVariables()
	{
		final Map<String, Object> map = super.getUrlVariables();
		map.put("individualId", individualId);
		return map;
	}
}
