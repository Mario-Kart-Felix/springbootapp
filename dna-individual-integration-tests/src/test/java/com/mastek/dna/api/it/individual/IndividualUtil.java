package com.mastek.dna.api.it.individual;

import org.springframework.jdbc.core.RowMapper;

import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import com.mastek.dna.model.Name.Title;

public final class IndividualUtil
{
	private IndividualUtil()
	{
		// Hidden by design
	}

	public static final RowMapper<Individual> ROW_MAPPER = (rs, rowNumber) -> new Individual()
			.setId(rs.getInt("individual_id"))
			.setName(new Name()
					.setTitle(Title.valueOf(rs.getString("title")))
					.setFirstname(rs.getString("first_name"))
					.setMiddlename(rs.getString("middle_name"))
					.setSurname(rs.getString("last_name")))
			.setDob(rs.getDate("dob").toLocalDate());
}
