package com.mastek.dna.api.it;

import static com.mastek.dna.api.it.ValidationErrorMatcher.forValidationError;

import java.time.LocalDate;
import java.util.List;

import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsCollectionContaining;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.mastek.dna.api.ValidationError;
import com.mastek.dna.api.ValidationErrorList;
import com.mastek.dna.config.Application;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import com.mastek.dna.model.Name.Title;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = Application.class)
public class IndividualEndpointIT
{
	@Autowired
	private TestRestTemplate restTemplate;

	private final static Logger LOGGER = LoggerFactory.getLogger(IndividualEndpointIT.class);

	private static final String IS_REQUIRED = "is required";

	@Test
	public void testCreate()
	{
		final Individual individual = new Individual()
				.setName(new Name().setTitle(Title.MR).setFirstname("Craig").setSurname("Greenhalgh"))
				.setDob(LocalDate.of(1980, 7, 30))
				.setAddress(new Address().setTown("Town"));

		final Individual created = post(individual, Individual.class);

		Assert.assertThat("Id should not be null", created.getId(), IsNull.notNullValue());

		Assert.assertThat("Unexpected created response", created, IndividualMatcher.forPerson(individual, true));
	}

	@Test
	public void testValidationFailureOfRootElements()
	{
		final Individual person = new Individual();

		final ValidationErrorList validationErrorList = post(person, ValidationErrorList.class);

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

		final ValidationErrorList validationErrorList = post(person, ValidationErrorList.class);

		final List<ValidationError> errors = validationErrorList.getErrors();
		Assert.assertThat("Unexpected error list size", errors, IsCollectionWithSize.hasSize(5));

		assertValidationError(errors, "name.title", IS_REQUIRED);
		assertValidationError(errors, "name.firstname", IS_REQUIRED);
		assertValidationError(errors, "name.surname", IS_REQUIRED);
		assertValidationError(errors, "address", IS_REQUIRED);
		assertValidationError(errors, "dob", IS_REQUIRED);
	}

	private <I, O> O post(final I toPost, final Class<O> responseClass)
	{
		final ResponseEntity<O> response = restTemplate.postForEntity("/individual", toPost, responseClass);

		LOGGER.info("HttpStatus : {}", response.getStatusCode());
		LOGGER.info("Body : {}", response.getBody());

		return response.getBody();
	}

	private void assertValidationError(final List<ValidationError> errors, final String field, final String message)
	{
		Assert.assertThat("Unexpected Validation Error", errors, IsCollectionContaining.hasItem(forValidationError(field, message)));
	}
}
