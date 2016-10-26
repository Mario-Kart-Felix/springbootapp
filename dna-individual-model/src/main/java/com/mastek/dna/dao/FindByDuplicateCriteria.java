package com.mastek.dna.dao;

import java.time.LocalDate;

import com.mastek.dna.model.Individual;

public interface FindByDuplicateCriteria
{
	Individual findByCriteria(LocalDate dob, String firstname, String surname);
}
