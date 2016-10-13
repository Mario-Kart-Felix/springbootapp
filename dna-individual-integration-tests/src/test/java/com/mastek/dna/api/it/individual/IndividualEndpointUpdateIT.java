package com.mastek.dna.api.it.individual;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.mastek.dna.api.matcher.IndividualMatcher;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name.Title;

public class IndividualEndpointUpdateIT extends IndividualEndpointExistingSuper
{
	@Test
	public void testUpdate()
	{
		existing.setDob(LocalDate.now().minusDays(1))
				.getName().setTitle(Title.DR).setFirstname("Steve").setMiddlename("Paul").setSurname("Jones");

		final Individual updated = send(existing, Individual.class);

		Assert.assertThat("Unexpected created response", updated, IndividualMatcher.forIndividual(existing));

		// TODO : Assert the DB
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.PUT, responseClass);
	}
}
