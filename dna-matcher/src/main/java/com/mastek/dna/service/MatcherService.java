package com.mastek.dna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dna.dao.MatcherDao;
import com.mastek.dna.model.Sample;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MatcherService
{
	@Autowired
	private MatcherDao matcherDao;

	@Transactional
	public Sample create(final Sample sample)
	{
		return matcherDao.save(sample);
	}

	@Transactional
	public Sample update(final Sample sample)
	{
		return matcherDao.save(sample);
	}

	@Transactional
	public void delete(final int id)
	{
		matcherDao.delete(id);
	}

	@Transactional
	public Sample find(final int id)
	{
		return matcherDao.findOne(id);
	}

	@Transactional
	public List<Sample> findAll()
	{
		return matcherDao.findAll();
	}
	
	/*@Transactional
	//[TODO]
	public Integer findFullMatchingProfile(final Sample sample)
	{
		return 2;
	}*/
}
