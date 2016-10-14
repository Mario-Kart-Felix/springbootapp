package com.mastek.dna.api.it;

import com.mastek.dna.api.ValidationError;
import com.mastek.dna.api.ValidationErrorList;
import com.mastek.dna.model.Profile;
import org.hamcrest.collection.IsCollectionWithSize;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class ProfileEndpointCreateIT extends ProfileEndpointSuper
{
	private static final String IS_REQUIRED = "is required";

	@Test
	public void testCreate()
	{
		final Profile profile = new Profile();
		profile.setFingerPrint(Arrays.asList("asdfghj123456","09876tyjnbmkl"));
		profile.setRetinaScan(Arrays.asList("someonesretina","mysecondretina"));


		final Profile created = send(profile, Profile.class);

		Assert.assertThat("Id should not be null", created.getId(), IsNull.notNullValue());

		Assert.assertThat("Unexpected created response", created, ProfileMatcher.forProfile(profile, true));
	}

	@Test
	public void testValidationFailureOfRootElements()
	{
		final Profile profile = new Profile();

		final ValidationErrorList validationErrorList = send(profile, ValidationErrorList.class);

		final List<ValidationError> errors = validationErrorList.getErrors();
		Assert.assertThat("Unexpected error list size", errors, IsCollectionWithSize.hasSize(2));

		assertValidationError(errors, "fingerPrint", IS_REQUIRED);
		assertValidationError(errors, "retinaScan", IS_REQUIRED);
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.POST, responseClass);
	}
}
