package com.mastek.dna.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dna.dao.IndividualDao;
import com.mastek.dna.model.Individual;

@Service
public class IndividualService
{
	@Autowired
	private IndividualDao individualDao;

	@Transactional
	public Individual create(final Individual individual)
	{
		return individualDao.create(individual);
	}

	@Transactional
	public Individual update(final Individual individual)
	{
		return individualDao.update(individual);
	}

	@Transactional
	public void delete(final int id)
	{
		individualDao.delete(individualDao.get(id));
	}
}
