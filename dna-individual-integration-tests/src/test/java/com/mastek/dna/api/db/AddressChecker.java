package com.mastek.dna.api.db;

import static org.apache.commons.lang3.tuple.Pair.of;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Arrays;

import org.dbunit.DatabaseUnitException;
import org.springframework.stereotype.Component;

import com.mastek.dna.db.BaseChecker;
import com.mastek.dna.model.Address;

@Component
public class AddressChecker extends BaseChecker
{
	@Override
	public String getXmlTemplate()
	{
		return "/data/model/address.xml";
	}

	@Override
	public String getTableName()
	{
		return "address";
	}

	@Override
	public String getTableIdentifier()
	{
		return "address_id";
	}

	public void assertDatabase(final int individualId, final Address address) throws MalformedURLException, SQLException, DatabaseUnitException
	{
		assertTable(address.getId(),
				Arrays.asList(
						of("$[address_id]", address.getId()),
						of("$[individual_id]", individualId),
						of("$[address_line1]", address.getLine1()),
						of("$[address_line2]", address.getLine2()),
						of("$[county]", address.getCounty()),
						of("$[country]", address.getCountry()),
						of("$[post_code]", address.getPostCode())));
	}
}
