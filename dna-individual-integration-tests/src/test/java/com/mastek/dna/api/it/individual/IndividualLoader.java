package com.mastek.dna.api.it.individual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mastek.api.Individual;

@Component
public class IndividualLoader
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Individual loadEntity()
	{
		return jdbcTemplate.queryForObject("SELECT * FROM individual i "
				+ "LEFT JOIN address a ON a.individual_id = i.id "
				+ "WHERE a.individual_id IS NULL "
				+ "LIMIT 1",
				IndividualUtil.ROW_MAPPER);
	}
}
