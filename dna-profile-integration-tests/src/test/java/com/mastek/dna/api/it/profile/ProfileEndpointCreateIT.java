package com.mastek.dna.api.it.profile;

import com.mastek.dna.api.ValidationError;
import com.mastek.dna.api.ValidationErrorList;
import com.mastek.dna.matcher.ProfileMatcher;
import com.mastek.dna.model.Profile;
import org.dbunit.DatabaseUnitException;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.List;

public class ProfileEndpointCreateIT extends ProfileEndpointSuper
{
	private static final String IS_REQUIRED = "is required";

	@Test
	public void testCreate() throws DatabaseUnitException, SQLException, MalformedURLException {
		final Profile profile = new Profile();
		profile.setIndividualId(1);
		profile.setFingerPrintData("asdfghj12345609876tyjnbmkl");
		profile.setRetinaScanData("someonesretinamysecondretina");


		final Profile created = send(profile, Profile.class, HttpStatus.CREATED);

		Assert.assertThat("Id should not be null", created.getId(), IsNull.notNullValue());

		Assert.assertThat("Unexpected created response", created, ProfileMatcher.forProfile(profile, true));

		profileChecker.assertDatabase(created);
	}

	@Test
	public void testValidationFailureOfRootElements()
	{
		final Profile profile = new Profile();

		final ValidationErrorList validationErrorList = send(profile, ValidationErrorList.class, HttpStatus.BAD_REQUEST);

		final List<ValidationError> errors = validationErrorList.getErrors();
		Assert.assertThat("Unexpected error list size", errors, IsCollectionWithSize.hasSize(3));

		assertValidationError(errors, "individualId", IS_REQUIRED);
		assertValidationError(errors, "fingerPrintData", IS_REQUIRED);
		assertValidationError(errors, "retinaScanData", IS_REQUIRED);
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass, final HttpStatus httpStatus)
	{
		return send(toSend, HttpMethod.POST, responseClass, httpStatus);
	}
}
