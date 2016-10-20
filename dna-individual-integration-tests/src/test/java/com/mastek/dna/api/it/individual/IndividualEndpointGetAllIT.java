package com.mastek.dna.api.it.individual;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.mastek.dna.model.Individual;

public class IndividualEndpointGetAllIT extends IndividualEndpointSuper
{
	@Test
	public void testGetAll() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		@SuppressWarnings("unchecked")
		final List<Individual> fetchedList = send(List.class);

		Assert.assertThat("Unexpected list size", fetchedList, IsNot.not(IsEmptyCollection.empty()));

		// TODO : Assert the list contents
	}

	private <I, O> O send(final Class<O> responseClass)
	{
		return send(null, HttpMethod.GET, responseClass, HttpStatus.OK);
	}
}
