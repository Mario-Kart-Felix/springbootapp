package com.mastek.dna.api.it.individual;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class IndividualEndpointDeleteIT extends IndividualEndpointExistingSuper
{
	@Test
	public void testDelete()
	{
		send(HttpStatus.OK);

		individualChecker.assertNoRow(existing.getId());
	}

	@Test
	public void testDeleteNotFound()
	{
		existing.setId(1000);

		send(HttpStatus.NOT_FOUND);
	}

	private <I, O> O send(final HttpStatus httpStatus)
	{
		return send(null, HttpMethod.DELETE, null, httpStatus);
	}
}
