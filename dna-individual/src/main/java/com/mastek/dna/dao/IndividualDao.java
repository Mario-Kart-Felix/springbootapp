package com.mastek.dna.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mastek.dna.model.Individual;

@Repository
public class IndividualDao
{
	@PersistenceContext
	private EntityManager entityManager;

	public Individual get(final int id)
	{
		return entityManager.find(Individual.class, id);
	}

	public Individual create(final Individual individual)
	{
		entityManager.persist(individual);
		return individual;
	}

	public Individual update(final Individual individual)
	{
		return entityManager.merge(individual);
	}

	public void delete(final Individual individual)
	{
		entityManager.remove(individual);
	}
}
