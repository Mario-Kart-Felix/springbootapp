package com.mastek.dna.dao;


import com.mastek.dna.model.Profile;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ProfileDao  extends Repository<Profile, Integer> {
	boolean exists(int id);

	Profile findOne(int id);

	List<Profile> findAll();

	Profile save(Profile profile);

	void delete(int id);
}
