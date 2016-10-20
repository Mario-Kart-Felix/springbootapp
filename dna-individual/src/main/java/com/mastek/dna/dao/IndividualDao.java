package com.mastek.dna.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.Repository;

import com.mastek.dna.model.Individual;

public interface IndividualDao extends Repository<Individual, Integer>
{
	Individual findOne(Integer id);

	List<Individual> findAll();

	Individual save(Individual individual);

	void delete(Individual individual);

	Individual findByDobAndNameFirstnameAndNameSurname(LocalDate dob, String firstname, String surname);
}
