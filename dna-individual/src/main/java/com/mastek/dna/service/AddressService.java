package com.mastek.dna.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mastek.dna.dao.AddressDao;
import com.mastek.dna.dao.IndividualDao;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;

@Service
public class AddressService
{
	@Autowired
	private AddressDao addressDao;

	@Autowired
	private IndividualDao individualDao;

	@Transactional
	public Address create(final int individualId, final Address address)
	{
		final Individual individual = individualDao.findOne(individualId);

		return addressDao.save(address.setIndividual(individual));
	}

	@Transactional
	public Address update(final int individualId, final Address address)
	{
		return addressDao.save(address);
	}

	@Transactional
	public void delete(final int individualId, final int id)
	{
		addressDao.delete(id);
	}
}
