package com.mastek.dna.service;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mastek.dna.api.NotFoundException;
import com.mastek.dna.dao.IndividualDao;
import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;

@Component
@Aspect
public class IndividualAspect
{
	@Autowired
	private IndividualDao individualDao;

	private final static Logger LOGGER = LoggerFactory.getLogger(IndividualAspect.class);

	@Before("execution(* com.mastek.dna.service.IndividualService.update(..)) and args(individual)")
	public void beforeUpdate(final Individual individual)
	{
		checkExists(individual.getId());
	}

	@Before("execution(* com.mastek.dna.service.IndividualService.delete(..)) and args(id)")
	public void beforeDelete(final int id)
	{
		checkExists(id);
	}

	@Before("execution(* com.mastek.dna.service.AddressService.create(..)) and args(individualId, address)")
	public void beforeCreate(final int individualId, final Address address)
	{
		checkExists(individualId);
	}

	private void checkExists(final int id)
	{
		final boolean exists = individualDao.exists(id);
		LOGGER.info("Individual Found with ID [{}] : [{}]", id, exists);
		if (!exists)
		{
			throw new NotFoundException(id);
		}
	}
}
