package com.mastek.dna.api.matcher;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.mastek.api.Name;

public class NameMatcher extends TypeSafeMatcher<Name>
{
	private final Name expected;

	private NameMatcher(final Name expected)
	{
		this.expected = expected;
	}

	public static NameMatcher forName(final Name expected)
	{
		return new NameMatcher(expected);
	}

	@Override
	public boolean matchesSafely(final Name name)
	{
		return EqualsBuilder.reflectionEquals(name, expected);
	}

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("expected : " + expected);
	}
}
