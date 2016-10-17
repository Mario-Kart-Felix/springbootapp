package com.mastek.dna.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mastek.dna.dao.IndividualDao;
import com.mastek.dna.model.Individual;

@Component
public class DuplicateIndividualValidator implements ConstraintValidator<DuplicateIndividual, Individual>
{
	@Autowired
	private IndividualDao individualDao;

	@Override
	public void initialize(final DuplicateIndividual constraintAnnotation)
	{
		// Nothing to do
	}

	@Override
	public boolean isValid(final Individual value, final ConstraintValidatorContext constraintContext)
	{
		if (null == value || null == value.getName())
		{
			return true;
		}

		final Individual individual = individualDao.findByDobAndNameFirstnameAndNameSurname(value.getDob(), value.getName().getFirstname(), value.getName().getSurname());

		return null == individual || individual.getId().equals(value.getId());
	}
}
