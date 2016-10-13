package com.mastek.dna.api.matcher;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.mastek.dna.model.Address;

public class AddressMatcher extends TypeSafeMatcher<Address>
{
	private final Address expected;
	private final Collection<String> excluded = new ArrayList<>();

	private AddressMatcher(final Address expected, final boolean excludeId)
	{
		this.expected = expected;
		if (excludeId)
		{
			excluded.add("id");
		}
	}

	public static AddressMatcher forNewAddress(final Address expected)
	{
		return new AddressMatcher(expected, true);
	}

	public static AddressMatcher forAddress(final Address expected)
	{
		return new AddressMatcher(expected, false);
	}

	@Override
	public boolean matchesSafely(final Address address)
	{
		return EqualsBuilder.reflectionEquals(address, expected, excluded);
	}

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("expected : " + expected).appendText("excluded : ").appendValue(excluded);
	}
}
