package com.mastek.dna.api.it;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.unitils.reflectionassert.ReflectionAssert;

import com.mastek.dna.model.Individual;

public class IndividualMatcher extends TypeSafeMatcher<Individual>
{
	private final Individual expected;
	private final boolean usesToMatchId;

	private IndividualMatcher(final Individual expected, final boolean usesToMatchId)
	{
		this.expected = expected;
		this.usesToMatchId = usesToMatchId;
	}

	public static IndividualMatcher forPerson(final Individual expected)
	{
		return new IndividualMatcher(expected, false);
	}

	public static IndividualMatcher forPerson(final Individual expected, final boolean usesToMatchId)
	{
		return new IndividualMatcher(expected, usesToMatchId);
	}

	@Override
	public boolean matchesSafely(final Individual individual)
	{
		if (usesToMatchId)
		{
			expected.setId(individual.getId());
		}

		ReflectionAssert.assertReflectionEquals(expected, individual);
		return true;
	}

	@Override
	public void describeTo(final Description description)
	{
		// Nothing to do here
	}
}
