package com.mastek.dna.dao;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.mastek.dna.model.Sample;

public interface MatcherDao extends Repository<Sample, Integer>
{
	boolean exists(int id);

	Sample findOne(int id);

	List<Sample> findAll();

	Sample save(Sample sample);

	void delete(int id);

	//Integer findFullMatchingProfile(Sample sample);
}
