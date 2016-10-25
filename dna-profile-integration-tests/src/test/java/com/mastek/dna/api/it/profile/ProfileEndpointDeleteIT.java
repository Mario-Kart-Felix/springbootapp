package com.mastek.dna.api.it.profile;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class ProfileEndpointDeleteIT extends ProfileEndpointExistingSuper
{

	@Test
	public void testDelete()
	{
		send(HttpStatus.OK);
		profileChecker.assertNoRow(existing.getId());
	}

	@Test
	public void testDeleteNotFound()
	{
		existing.setId(345);
        send(existing, HttpMethod.DELETE, null, HttpStatus.NOT_FOUND);

	}

	private <I, O> O send(final HttpStatus httpStatus)
	{
		return send(null, HttpMethod.DELETE, null, httpStatus);
	}
}
