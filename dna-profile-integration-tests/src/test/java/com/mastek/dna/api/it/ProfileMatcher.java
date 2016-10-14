package com.mastek.dna.api.it;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.unitils.reflectionassert.ReflectionAssert;

import com.mastek.dna.model.Profile;

public class ProfileMatcher extends TypeSafeMatcher<Profile>
{
	private final Profile expected;
	private final boolean usesToMatchId;

	private ProfileMatcher(final Profile expected, final boolean usesToMatchId)
	{
		this.expected = expected;
		this.usesToMatchId = usesToMatchId;
	}

	public static ProfileMatcher forProfile(final Profile expected)
	{
		return new ProfileMatcher(expected, false);
	}

	public static ProfileMatcher forProfile(final Profile expected, final boolean usesToMatchId)
	{
		return new ProfileMatcher(expected, usesToMatchId);
	}

	@Override
	public boolean matchesSafely(final Profile profile)
	{
		if (usesToMatchId)
		{
			expected.setId(profile.getId());
		}

		ReflectionAssert.assertReflectionEquals(expected, profile);
		return true;
	}

	@Override
	public void describeTo(final Description description)
	{
		// Nothing to do here
	}
}
