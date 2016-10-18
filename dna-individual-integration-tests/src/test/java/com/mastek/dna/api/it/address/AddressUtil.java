package com.mastek.dna.api.it.address;

import org.springframework.jdbc.core.RowMapper;

import com.mastek.dna.model.Address;

public final class AddressUtil
{
	private AddressUtil()
	{
		// Hidden by design
	}

	public static final RowMapper<Address> ROW_MAPPER = (rs, rowNumber) -> new Address()
			.setId(rs.getInt("id"))
			.setLine1(rs.getString("address_line1"))
			.setLine2(rs.getString("address_line2"))
			.setCounty(rs.getString("county"))
			.setCountry(rs.getString("country"))
			.setPostCode(rs.getString("post_code"));

}
