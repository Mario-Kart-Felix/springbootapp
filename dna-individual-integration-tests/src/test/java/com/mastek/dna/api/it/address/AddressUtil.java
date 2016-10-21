package com.mastek.dna.api.it.address;

import org.springframework.jdbc.core.RowMapper;

import com.mastek.api.Address;

public final class AddressUtil
{
	private AddressUtil()
	{
		// Hidden by design
	}

	public static final RowMapper<Address> ROW_MAPPER = (rs, rowNumber) -> new Address()
			.withId(rs.getInt("id"))
			.withLine1(rs.getString("address_line1"))
			.withLine2(rs.getString("address_line2"))
			.withCounty(rs.getString("county"))
			.withCountry(rs.getString("country"))
			.withPostCode(rs.getString("post_code"));
}
