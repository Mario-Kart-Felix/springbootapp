package com.mastek.dna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dna.dao.IndividualDao;
import com.mastek.dna.model.Individual;

@Service
public class IndividualService
{
	@Autowired
	private IndividualDao individualDao;

	public Individual create(final Individual individual)
	{
		return individualDao.create(individual);
	}
}
