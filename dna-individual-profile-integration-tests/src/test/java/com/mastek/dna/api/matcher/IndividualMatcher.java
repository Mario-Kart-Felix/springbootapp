package com.mastek.dna.api.matcher;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.mastek.dna.model.Individual;

public class IndividualMatcher extends TypeSafeMatcher<Individual>
{
	private final Individual expected;

	private IndividualMatcher(final Individual expected)
	{
		this.expected = expected;
	}

	public static IndividualMatcher forIndividual(final Individual expected)
	{
		return new IndividualMatcher(expected);
	}

	@Override
	public boolean matchesSafely(final Individual individual)
	{
		return EqualsBuilder.reflectionEquals(individual, expected, "id", "name", "addresses")
				&& NameMatcher.forName(expected.getName()).matchesSafely(individual.getName());
	}

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("expected : " + expected);
	}
}
