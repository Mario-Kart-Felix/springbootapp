package com.mastek.dna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dna.dao.ProfileDao;
import com.mastek.dna.model.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProfileService
{
	@Autowired
	private ProfileDao profileDao;

	@Transactional
	public Profile create(final Profile profile)
	{
		return profileDao.save(profile);
	}

	@Transactional
	public Profile update(final Profile profile)
	{
		return profileDao.save(profile);
	}

	@Transactional
	public void delete(final int id)
	{
		profileDao.delete(id);
	}

	@Transactional
	public Profile find(final int id)
	{
		return profileDao.findOne(id);
	}

	@Transactional
	public List<Profile> findAll()
	{
		return profileDao.findAll();
	}
}
