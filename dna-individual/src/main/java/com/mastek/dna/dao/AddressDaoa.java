package com.mastek.dna.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class AddressDaoa
{
	@PersistenceContext
	private EntityManager entityManager;

	// public Address get(final int id)
	// {
	// return entityManager.find(Address.class, id);
	// }

	// public Address create(final Address address)
	// {
	// entityManager.persist(address);
	// return address;
	// }
	//
	// public Address update(final Address address)
	// {
	// return entityManager.merge(address);
	// }
	//
	// public void delete(final Address address)
	// {
	// entityManager.remove(address);
	// }
}
