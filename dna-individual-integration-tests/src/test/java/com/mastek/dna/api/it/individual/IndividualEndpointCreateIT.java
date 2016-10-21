package com.mastek.dna.api.it.individual;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.dbunit.DatabaseUnitException;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastek.api.Address;
import com.mastek.api.Individual;
import com.mastek.api.Name;
import com.mastek.api.Title;
import com.mastek.dna.api.ValidationError;
import com.mastek.dna.api.ValidationErrorList;
import com.mastek.dna.api.matcher.IndividualMatcher;

// TODO : Create should work with POST and PUT, and return different HTTP Status codes
public class IndividualEndpointCreateIT extends IndividualEndpointSuper
{
	private static final String IS_REQUIRED = "is required";

	@Test
	public void testCreate() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		final Individual individual = new Individual()
				.withName(new Name().withTitle(Title.MR).withFirstname("John").withSurname("Smith"))
				.withDob(LocalDate.of(1980, 7, 30).toString());

		final Individual created = send(individual, Individual.class, HttpStatus.CREATED);

		Assert.assertThat("Id should not be null", created.getId(), IsNull.notNullValue());

		// Assert.assertThat("Addresses should be null", created.getAddresses(),
		// IsNull.nullValue());

		Assert.assertThat("Unexpected created response", created, IndividualMatcher.forIndividual(individual));

		individualChecker.assertDatabase(created);
	}

	@Test
	public void testValidationFailureOfRootElements() throws JsonProcessingException
	{
		final Individual individual = new Individual();
		individual.getAddresses().add(new Address());

		final ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_EMPTY);
		// mapper.enable(MapperFeature.);
		// mapper.configure(MapperFeature., true);
		// mapper.setAnnotationIntrospector(new AnnotationIntrospectorPair(new
		// JacksonAnnotationIntrospector(),
		// new JaxbAnnotationIntrospector(TypeFactory.defaultInstance())));

		final String json = mapper.writeValueAsString(individual);
		System.out.println(json);

		final ValidationErrorList validationErrorList = send(individual, ValidationErrorList.class, HttpStatus.BAD_REQUEST);

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
				.withName(new Name());

		final ValidationErrorList validationErrorList = send(individual, ValidationErrorList.class, HttpStatus.BAD_REQUEST);

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
		final Individual exising = individualLoader.loadEntity().withId(null);

		final ValidationErrorList validationErrorList = send(exising, ValidationErrorList.class, HttpStatus.BAD_REQUEST);

		final List<ValidationError> errors = validationErrorList.getErrors();

		assertValidationError(errors, "individual", "Duplicate individual");
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass, final HttpStatus httpStatus)
	{
		return send(toSend, HttpMethod.POST, responseClass, httpStatus);
	}
}
