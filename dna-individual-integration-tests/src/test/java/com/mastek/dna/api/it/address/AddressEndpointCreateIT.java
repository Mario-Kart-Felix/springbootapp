package com.mastek.dna.api.it.address;

import java.net.MalformedURLException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.mastek.api.Address;
import com.mastek.dna.api.matcher.AddressMatcher;

// TODO : Create should work with POST and PUT, and return different HTTP Status codes
public class AddressEndpointCreateIT extends AddressEndpointSuper
{
	@Test
	public void testCreate() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		final Address address = new Address().withLine1("line1").withLine2("line2").withCounty("county").withCountry("country").withPostCode("AB12 3CD");

		final Address created = send(address, Address.class, HttpStatus.CREATED);

		Assert.assertThat("Id should not be null", created.getId(), IsNull.notNullValue());

		Assert.assertThat("Unexpected created response", created, AddressMatcher.forNewAddress(address));

		addressChecker.assertDatabase(individualId, created);
	}

	@Test
	public void testCreateIndividualNotFound()
	{
		individualId = 1000;

		final Address address = new Address().withLine1("line1").withLine2("line2").withCounty("county").withCountry("country").withPostCode("AB12 3CD");

		send(address, null, HttpStatus.NOT_FOUND);
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass, final HttpStatus httpStatus)
	{
		return send(toSend, HttpMethod.POST, responseClass, httpStatus);
	}
}
