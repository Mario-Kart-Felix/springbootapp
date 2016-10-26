package com.mastek.dna.api.it.address;

import java.util.Map;

import org.junit.Before;

import com.mastek.api.Address;

public class AddressEndpointExistingSuper extends AddressEndpointSuper
{
	protected Address existing;

	@Before
	public void loadEntity()
	{
		existing = jdbcTemplate.queryForObject("SELECT * FROM address WHERE individual_id = ?", AddressUtil.ROW_MAPPER, individualId);
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
