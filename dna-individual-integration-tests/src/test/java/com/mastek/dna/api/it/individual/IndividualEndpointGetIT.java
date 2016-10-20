package com.mastek.dna.api.it.individual;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.mastek.dna.api.matcher.IndividualMatcher;
import com.mastek.dna.model.Individual;

public class IndividualEndpointGetIT extends IndividualEndpointExistingSuper
{
	@Test
	public void testGet()
	{
		final Individual fetchedIndividual = send(HttpStatus.OK);

		Assert.assertThat("Unexpected response", fetchedIndividual, IndividualMatcher.forIndividual(existing));

		// TODO : Assert the address
	}

	@Test
	public void testGetNotFound()
	{
		existing.setId(1000);

		send(HttpStatus.NOT_FOUND);
	}

	private Individual send(final HttpStatus httpStatus)
	{
		return send(null, HttpMethod.GET, Individual.class, httpStatus);
	}
}
