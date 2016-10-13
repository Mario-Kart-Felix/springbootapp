package com.mastek.dna.api.it.address;

import java.net.MalformedURLException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.mastek.dna.api.matcher.AddressMatcher;
import com.mastek.dna.model.Address;

public class AddressEndpointUpdateIT extends AddressEndpointExistingSuper
{
	@Test
	public void testUpdate() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		existing.setLine1("new line 1")
				.setLine2("new line 2")
				.setCounty("new county")
				.setCountry("new country")
				.setPostCode("XY01 QA1");

		final Address updated = send(existing, Address.class);

		Assert.assertThat("Id should not change", updated.getId(), Is.is(existing.getId()));

		Assert.assertThat("Unexpected created response", updated, AddressMatcher.forAddress(existing));

		addressChecker.assertDatabase(individualId, updated);
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.PUT, responseClass);
	}
}
