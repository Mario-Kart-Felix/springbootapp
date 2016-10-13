package com.mastek.dna.api.it;

import com.mastek.dna.model.Profile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import java.util.Arrays;
import java.util.Map;

public class ProfileEndpointUpdateIT extends ProfileEndpointSuper
{
	private Profile existing;

	@Before
	public void loadEntity()
	{
		existing = new Profile();
		existing.setId(1);
		existing.setFingerPrint(Arrays.asList("asdfghj123456","09876tyjnbmkl"));
		existing.setRetinaScan(Arrays.asList("my-first-retinal-scan","my-second-retinal-scan"));
	}

	@Test
	public void testUpdate()
	{
		existing.getFingerPrint().set(0,"my-right-Thumb-Impression");
		existing.getFingerPrint().set(0,"my-left-index-finger-Impression");
		existing.getRetinaScan().set(0,"my-right-eye-scan-highest-resolution");
		final Profile updated = send(existing, Profile.class);

		Assert.assertThat("Unexpected created response", updated, ProfileMatcher.forProfile(existing));
	}

	@Override
	protected String getUrl()
	{
		return super.getUrl() + "/{id}";
	}

	@Override
	protected Map<String, Object> getUrlVariables()
	{
		final Map<String, Object> map = super.getUrlVariables();
		map.put("id", existing.getId());
		return map;
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.PUT, responseClass);
	}
}
