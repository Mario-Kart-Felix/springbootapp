package com.mastek.dna.api.it.address;

import java.net.MalformedURLException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

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

		final Address updated = send(existing, Address.class, HttpStatus.OK);

		Assert.assertThat("Id should not change", updated.getId(), Is.is(existing.getId()));

		Assert.assertThat("Unexpected created response", updated, AddressMatcher.forAddress(existing));

		addressChecker.assertDatabase(individualId, updated);
	}

	@Test
	public void testUpdateNotFound()
	{
		existing.setId(1000);

		send(existing, null, HttpStatus.NOT_FOUND);
	}

	@Test
	public void testUpdateIndividualNotFound()
	{
		individualId = 1000;

		send(existing, null, HttpStatus.NOT_FOUND);
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass, final HttpStatus httpStatus)
	{
		return send(toSend, HttpMethod.PUT, responseClass, httpStatus);
	}
}
