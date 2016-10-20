package com.mastek.dna.api.it.address;

import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class AddressEndpointDeleteIT extends AddressEndpointExistingSuper
{
	@Test
	public void testDelete()
	{
		send(HttpStatus.OK);

		addressChecker.assertNoRow(existing.getId());
	}

	private <I, O> O send(final HttpStatus httpStatus)
	{
		return send(null, HttpMethod.DELETE, null, httpStatus);
	}
}
