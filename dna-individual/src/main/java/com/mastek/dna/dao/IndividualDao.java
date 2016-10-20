package com.mastek.dna.dao;

import java.time.LocalDate;

import org.springframework.data.repository.Repository;

import com.mastek.dna.model.Individual;

public interface IndividualDao extends Repository<Individual, Integer>
{
	boolean exists(int id);

	Individual findOne(int id);

	Individual save(Individual individual);

	void delete(int id);

	Individual findByDobAndNameFirstnameAndNameSurname(LocalDate dob, String firstname, String surname);
}
