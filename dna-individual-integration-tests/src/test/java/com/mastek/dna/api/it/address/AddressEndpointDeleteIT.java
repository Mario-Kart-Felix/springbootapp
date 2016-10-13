package com.mastek.dna.api.it.address;

import org.junit.Test;
import org.springframework.http.HttpMethod;

public class AddressEndpointDeleteIT extends AddressEndpointExistingSuper
{
	@Test
	public void testDelete()
	{
		send();

		addressChecker.assertNoRow(existing.getId());
	}

	private <I, O> O send()
	{
		return send(null, HttpMethod.DELETE, null);
	}
}
