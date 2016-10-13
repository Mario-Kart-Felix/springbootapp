package com.mastek.dna.dao;

import org.springframework.data.repository.Repository;

import com.mastek.dna.model.Address;

public interface AddressDao extends Repository<Address, Integer>
{
	Address findOne(int id);

	Address save(Address address);

	void delete(Address address);
}
