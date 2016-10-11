package com.mastek.dna.model.validator;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.Temporal;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<Age, Temporal>
{
	private Optional<Integer> minAge;
	private Optional<Integer> maxAge;

	@Override
	public void initialize(final Age constraintAnnotation)
	{
		minAge = fromValue(constraintAnnotation.min());
		maxAge = fromValue(constraintAnnotation.max());
	}

	private Optional<Integer> fromValue(final int value)
	{
		return value != -1 ? Optional.of(value) : Optional.empty();
	}

	@Override
	public boolean isValid(final Temporal value, final ConstraintValidatorContext constraintContext)
	{
		if (value == null)
		{
			return true;
		}

		final LocalDate now = LocalDate.now();

		final LocalDate localDate = LocalDate.from(value);

		boolean valid = true;
		boolean validDob = false;

		if (localDate.isAfter(now))
		{
			valid = false;
		}
		else
		{
			validDob = true;
			valid = validateConstraints(now, localDate, valid);
		}

		addMessageIfRequired(constraintContext, valid, validDob);

		return valid;
	}

	private boolean validateConstraints(final LocalDate now, final LocalDate localDate, boolean valid)
	{
		final Period between = Period.between(localDate, now);
		final int years = between.getYears();

		if (minAge.isPresent() && years < minAge.get())
		{
			valid = false;
		}

		if (maxAge.isPresent() && years > maxAge.get())
		{
			valid = false;
		}

		return valid;
	}

	private void addMessageIfRequired(final ConstraintValidatorContext constraintContext, final boolean valid, final boolean validDob)
	{
		if (!valid)
		{
			String message = "{com.mastek.dna.model.validation.Age.message}";

			if (validDob)
			{
				if (minAge.isPresent() && !maxAge.isPresent())
				{
					message = "{com.mastek.dna.model.validation.Age.min.message}";
				}
				else if (!minAge.isPresent() && maxAge.isPresent())
				{
					message = "{com.mastek.dna.model.validation.Age.max.message}";
				}
				else if (minAge.isPresent() && maxAge.isPresent())
				{
					message = "{com.mastek.dna.model.validation.Age.range.message}";
				}
			}

			final HibernateConstraintValidatorContext hibernateContext = constraintContext.unwrap(HibernateConstraintValidatorContext.class);

			hibernateContext.disableDefaultConstraintViolation();

			minAge.ifPresent(min -> hibernateContext.addExpressionVariable("min", min));
			maxAge.ifPresent(max -> hibernateContext.addExpressionVariable("max", max));

			hibernateContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		}
	}
}
