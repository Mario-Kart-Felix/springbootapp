package com.mastek.dna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dna.dao.ProfileDao;
import com.mastek.dna.model.Profile;

@Service
public class ProfileService
{
	@Autowired
	private ProfileDao profileDao;

	public Profile create(final Profile profile)
	{
		return profileDao.create(profile);
	}

	public Profile update(final Profile profile)
	{
		return profileDao.update(profile);
	}

	public void delete(final int id)
	{
		profileDao.delete(id);
	}
}
