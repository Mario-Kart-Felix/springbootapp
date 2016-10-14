package com.mastek.dna.api.it.individual;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.mastek.dna.api.ValidationError;
import com.mastek.dna.api.ValidationErrorList;
import com.mastek.dna.api.matcher.IndividualMatcher;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import com.mastek.dna.model.Name.Title;

// TODO : Create should work with POST and PUT, and return different HTTP Status codes
public class IndividualEndpointCreateIT extends IndividualEndpointSuper
{
	private static final String IS_REQUIRED = "is required";

	@Test
	public void testCreate() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		final Individual individual = new Individual()
				.setName(new Name().setTitle(Title.MR).setFirstname("John").setSurname("Smith"))
				.setDob(LocalDate.of(1980, 7, 30));

		final Individual created = send(individual, Individual.class);

		Assert.assertThat("Id should not be null", created.getId(), IsNull.notNullValue());

		Assert.assertThat("Addresses should be null", created.getAddresses(), IsNull.nullValue());

		Assert.assertThat("Unexpected created response", created, IndividualMatcher.forIndividual(individual));

		individualChecker.assertDatabase(created);
	}

	@Test
	public void testValidationFailureOfRootElements()
	{
		final Individual individual = new Individual();

		individual.setAddresses(new HashSet<>());
		individual.getAddresses().add(new Address());

		final ValidationErrorList validationErrorList = send(individual, ValidationErrorList.class);

		final List<ValidationError> errors = validationErrorList.getErrors();
		Assert.assertThat("Unexpected error list size", errors, IsCollectionWithSize.hasSize(3));

		assertValidationError(errors, "name", IS_REQUIRED);
		assertValidationError(errors, "addresses", "must be null");
		assertValidationError(errors, "dob", IS_REQUIRED);
	}

	@Test
	public void testValidationFailureOfRootElementChild()
	{
		final Individual individual = new Individual()
				.setName(new Name());

		final ValidationErrorList validationErrorList = send(individual, ValidationErrorList.class);

		final List<ValidationError> errors = validationErrorList.getErrors();
		Assert.assertThat("Unexpected error list size", errors, IsCollectionWithSize.hasSize(4));

		assertValidationError(errors, "name.title", IS_REQUIRED);
		assertValidationError(errors, "name.firstname", IS_REQUIRED);
		assertValidationError(errors, "name.surname", IS_REQUIRED);
		assertValidationError(errors, "dob", IS_REQUIRED);
	}

	@Test
	public void testValidationFailureOfDuplicate()
	{
		final Individual exising = individualLoader.loadEntity().setId(null);

		final ValidationErrorList validationErrorList = send(exising, ValidationErrorList.class);

		final List<ValidationError> errors = validationErrorList.getErrors();

		assertValidationError(errors, "individual", "Duplicate individual");
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.POST, responseClass);
	}
}
