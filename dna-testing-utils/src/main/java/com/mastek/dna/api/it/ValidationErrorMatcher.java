package com.mastek.dna.api.it;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import com.mastek.dna.api.ValidationError;

public class ValidationErrorMatcher extends TypeSafeMatcher<ValidationError>
{
	private final String field;
	private final String message;

	private ValidationErrorMatcher(final String field, final String message)
	{
		this.field = field;
		this.message = message;
	}

	public static ValidationErrorMatcher forValidationError(final String field, final String message)
	{
		return new ValidationErrorMatcher(field, message);
	}

	@Override
	public boolean matchesSafely(final ValidationError validationError)
	{
		return field.equals(validationError.getField()) && message.equals(validationError.getMessage());
	}

	@Override
	public void describeTo(final Description description)
	{
		description.appendText("field should equal ").appendValue(field)
				.appendText(", message should equal ").appendValue(message);
	}

	@Override
	public void describeMismatchSafely(final ValidationError validationError, final Description description)
	{
		description.appendText("field was ").appendValue(validationError.getField())
				.appendText(", message was ").appendValue(validationError.getMessage());
	}

}
