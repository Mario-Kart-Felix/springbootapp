package com.mastek.dna.api.it.profile;

import com.mastek.dna.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProfileLoader
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Profile loadEntity()
	{
		return jdbcTemplate.queryForObject("SELECT * FROM individual_profile", ProfileUtil.ROW_MAPPER);
	}
}
