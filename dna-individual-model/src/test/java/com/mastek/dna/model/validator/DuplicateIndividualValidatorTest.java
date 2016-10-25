package com.mastek.dna.model.validator;

import java.time.LocalDate;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mastek.dna.dao.FindByDuplicateCriteria;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;

@RunWith(MockitoJUnitRunner.class)
public class DuplicateIndividualValidatorTest
{
	@Mock
	private FindByDuplicateCriteria findByDuplicateCriteria;

	@InjectMocks
	private DuplicateIndividualValidator duplicateIndividualValidator;

	@Test
	public void testIsADuplicate()
	{
		final String firstname = "Craig";
		final String surname = "Greenhalgh";
		final LocalDate dob = LocalDate.of(1980, 7, 30);

		final Individual duplicateIndividual = new Individual().setId(1).setName(new Name().setFirstname(firstname).setSurname(surname)).setDob(dob);

		Mockito.when(findByDuplicateCriteria.findByCriteria(dob, firstname, surname)).thenReturn(duplicateIndividual);

		Assert.assertThat("Unexpected duplicate check", duplicateIndividualValidator.isValid(new Individual().setName(new Name().setFirstname(firstname).setSurname(surname)).setDob(dob), null), Is.is(false));
	}
}
