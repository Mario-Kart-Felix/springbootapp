package com.mastek.dna.api.it.individual;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.dbunit.DatabaseUnitException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.mastek.dna.api.matcher.IndividualMatcher;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name.Title;

public class IndividualEndpointUpdateIT extends IndividualEndpointExistingSuper
{
	@Test
	public void testUpdate() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		existing.setDob(LocalDate.now().minusDays(1))
				.getName().setTitle(Title.DR).setFirstname("Steve").setMiddlename("Paul").setSurname("Jones");

		final Individual updated = send(existing, Individual.class, HttpStatus.OK);

		Assert.assertThat("Unexpected created response", updated, IndividualMatcher.forIndividual(existing));

		individualChecker.assertDatabase(updated);
	}

	@Test
	public void testUpdateNotFound()
	{
		// TODO without changing the data the duplicate check kicks in not the
		// not found exception
		existing.setId(10000)
				.getName().setFirstname("Pete");

		send(existing, null, HttpStatus.NOT_FOUND);
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass, final HttpStatus httpStatus)
	{
		return send(toSend, HttpMethod.PUT, responseClass, httpStatus);
	}
}
