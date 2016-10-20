package com.mastek.dna.api.it.individual;

import com.mastek.dna.api.ValidationError;
import com.mastek.dna.api.ValidationErrorList;
import com.mastek.dna.api.matcher.IndividualMatcher;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import com.mastek.dna.model.Name.Title;
import org.apache.commons.collections.map.HashedMap;
import org.apache.xmlbeans.ObjectFactory;
import org.dbunit.DatabaseUnitException;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// TODO : Create should work with POST and PUT, and return different HTTP Status codes
public class IndividualEndpointGetIT extends IndividualEndpointSuper
{
	private static final String IS_REQUIRED = "is required";

	private String endPoint;

	@Test
	public void testGet() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		final Individual individual = new Individual()
				.setName(new Name().setTitle(Title.MR).setFirstname("John").setSurname("Smith"))
				.setDob(LocalDate.of(1980, 7, 30));

		endPoint = "/individual";
		Individual createdIndividual = send(individual, Individual.class);

		endPoint = "/individual/"+createdIndividual.getId().intValue();

		final Individual created = send(individual, HttpMethod.GET, Individual.class);

		Assert.assertThat("Id should not be null", created.getId(), IsNull.notNullValue());

		Assert.assertThat("Addresses should be null", created.getAddresses(), IsNull.notNullValue());

		Assert.assertThat("Unexpected created response", created, IndividualMatcher.forIndividual(individual));
	}

	@Test
	public void testGetAll() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		final Individual individual = new Individual()
				.setName(new Name().setTitle(Title.MR).setFirstname("John").setSurname("Smith"))
				.setDob(LocalDate.of(1980, 7, 30));

		endPoint = "/individual";
		Individual createdIndividual = send(individual, Individual.class);

		final List<Individual> created = send(individual, HttpMethod.GET, List.class);

		Assert.assertNotNull(created);

		Assert.assertTrue("Size of the list is greater than zero", created.size() > 0);
	}

	@Test
	public void testValidationFailureOfNonExistingId()
	{
		endPoint = "/individual/";
		final Individual individual = send(null, HttpMethod.GET, null);

		Assert.assertNull(individual);
	}

	@Override
	public String getUrl()
	{
		return endPoint;
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.POST, responseClass);
	}
}
