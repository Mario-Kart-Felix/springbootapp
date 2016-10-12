package com.mastek.dna.api.it;

import java.time.LocalDate;
import java.util.List;

import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.mastek.dna.api.ValidationError;
import com.mastek.dna.api.ValidationErrorList;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import com.mastek.dna.model.Name.Title;

// TODO : Create should work with POST and PUT, and return different HTTP Status codes
public class IndividualEndpointCreateIT extends IndividualEndpointSuper
{
	private static final String IS_REQUIRED = "is required";

	@Test
	public void testCreate()
	{
		final Individual individual = new Individual()
				.setName(new Name().setTitle(Title.MR).setFirstname("Craig").setSurname("Greenhalgh"))
				.setDob(LocalDate.of(1980, 7, 30))
				.setAddress(new Address().setTown("Town"));

		final Individual created = send(individual, Individual.class);

		Assert.assertThat("Id should not be null", created.getId(), IsNull.notNullValue());

		Assert.assertThat("Unexpected created response", created, IndividualMatcher.forPerson(individual, true));

		// TODO : Assert the DB
	}

	@Test
	public void testValidationFailureOfRootElements()
	{
		final Individual person = new Individual();

		final ValidationErrorList validationErrorList = send(person, ValidationErrorList.class);

		final List<ValidationError> errors = validationErrorList.getErrors();
		Assert.assertThat("Unexpected error list size", errors, IsCollectionWithSize.hasSize(3));

		assertValidationError(errors, "name", IS_REQUIRED);
		assertValidationError(errors, "address", IS_REQUIRED);
		assertValidationError(errors, "dob", IS_REQUIRED);
	}

	@Test
	public void testValidationFailureOfRootElementChild()
	{
		final Individual person = new Individual()
				.setName(new Name());

		final ValidationErrorList validationErrorList = send(person, ValidationErrorList.class);

		final List<ValidationError> errors = validationErrorList.getErrors();
		Assert.assertThat("Unexpected error list size", errors, IsCollectionWithSize.hasSize(5));

		assertValidationError(errors, "name.title", IS_REQUIRED);
		assertValidationError(errors, "name.firstname", IS_REQUIRED);
		assertValidationError(errors, "name.surname", IS_REQUIRED);
		assertValidationError(errors, "address", IS_REQUIRED);
		assertValidationError(errors, "dob", IS_REQUIRED);
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.POST, responseClass);
	}
}
