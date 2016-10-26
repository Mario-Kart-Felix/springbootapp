package com.mastek.dna.api.it.profile;

import com.mastek.dna.matcher.ProfileMatcher;
import com.mastek.dna.model.Profile;
import org.dbunit.DatabaseUnitException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.net.MalformedURLException;
import java.sql.SQLException;

public class ProfileEndpointUpdateIT extends ProfileEndpointExistingSuper
{

	@Test
	public void testUpdate() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		existing.setFingerPrintData("my-right-Thumb-Impression");
		existing.setRetinaScanData("my-right-eye-scan-highest-resolution");
		final Profile updated = send(existing, Profile.class, HttpStatus.OK);

		Assert.assertThat("Unexpected created response", updated, ProfileMatcher.forProfile(existing));
		profileChecker.assertDatabase(updated);
	}

	@Test
	public void testUpdateNotFound()
	{
		// TODO without changing the data the duplicate check kicks in not the
		// not found exception
		existing.setId(10000);
		send(existing, null, HttpStatus.NOT_FOUND);
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass, final HttpStatus httpStatus)
	{
		return send(toSend, HttpMethod.PUT, responseClass, httpStatus);
	}
}
