package com.mastek.dna.api.it.individual;

import org.springframework.jdbc.core.RowMapper;

import com.mastek.api.Individual;
import com.mastek.api.Name;
import com.mastek.api.Title;

public final class IndividualUtil
{
	private IndividualUtil()
	{
		// Hidden by design
	}

	public static final RowMapper<Individual> ROW_MAPPER = (rs, rowNumber) -> new Individual()
			.withId(rs.getInt("id"))
			.withName(new Name()
					.withTitle(Title.valueOf(rs.getString("title")))
					.withFirstname(rs.getString("first_name"))
					.withMiddlename(rs.getString("middle_name"))
					.withSurname(rs.getString("last_name")))
			.withDob(rs.getDate("dob").toLocalDate().toString());
}
