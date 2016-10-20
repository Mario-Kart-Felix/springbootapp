package com.mastek.dna.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mastek.dna.api.NotFoundException;
import com.mastek.dna.dao.AddressDao;
import com.mastek.dna.model.Address;

@Component
@Aspect
public class AddressAspect
{
	@Autowired
	private AddressDao addressDao;

	private final static Logger LOGGER = LoggerFactory.getLogger(AddressAspect.class);

	@Before("execution(* com.mastek.dna.service.AddressService.delete(..)) and args(individualId, id)")
	public void beforeDelete(final int individualId, final int id)
	{
		checkExists(individualId, id);
	}

	@Before("execution(* com.mastek.dna.service.AddressService.update(..)) and args(individualId, address)")
	public void beforeUpdate(final int individualId, final Address address)
	{
		checkExists(individualId, address.getId());
	}

	private void checkExists(final int individualId, final int id)
	{
		final boolean exists = addressDao.countByIndividualIdAndId(individualId, id) != 0;

		LOGGER.info("Address found with ID [{}] for individual ID [{}] : [{}]", id, individualId, exists);
		if (!exists)
		{
			throw new NotFoundException(id);
		}
	}
}
