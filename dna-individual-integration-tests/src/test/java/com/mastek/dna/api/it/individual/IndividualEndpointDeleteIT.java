package com.mastek.dna.api.it.individual;

import org.junit.Test;
import org.springframework.http.HttpMethod;

public class IndividualEndpointDeleteIT extends IndividualEndpointExistingSuper
{
	@Test
	public void testDelete()
	{
		send();

		individualChecker.assertNoRow(existing.getId());
	}

	private <I, O> O send()
	{
		return send(null, HttpMethod.DELETE, null);
	}
}
