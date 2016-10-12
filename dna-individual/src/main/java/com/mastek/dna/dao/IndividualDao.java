package com.mastek.dna.dao;

import org.springframework.stereotype.Repository;

import com.mastek.dna.model.Individual;

@Repository
public class IndividualDao
{
	public Individual create(final Individual individual)
	{
		individual.setId(100);
		return individual;
	}
}
