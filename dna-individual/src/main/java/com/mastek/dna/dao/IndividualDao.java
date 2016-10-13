package com.mastek.dna.dao;

import org.springframework.data.repository.Repository;

import com.mastek.dna.model.Individual;

public interface IndividualDao extends Repository<Individual, Integer>
{
	Individual findOne(int id);

	Individual save(Individual individual);

	void delete(Individual individual);
}
