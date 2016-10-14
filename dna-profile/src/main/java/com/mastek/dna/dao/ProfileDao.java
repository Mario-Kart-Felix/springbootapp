package com.mastek.dna.dao;

import org.springframework.stereotype.Repository;

import com.mastek.dna.model.Profile;

@Repository
public class ProfileDao
{
	public Profile create(final Profile profile)
	{
		System.out.println("ProfileDao:  Creating Individual Profile" + profile);
		profile.setId(1);
		return profile;
	}

	public Profile update(final Profile profile)
	{
		System.out.println("ProfileDao:  updating Profile" + profile);
		return profile;
	}

	public void delete(final int id)
	{
		System.out.println("ProfileDao: deleting Profile:" + id);
	}
}
