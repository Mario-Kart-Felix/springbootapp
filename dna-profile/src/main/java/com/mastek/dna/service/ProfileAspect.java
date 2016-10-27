package com.mastek.dna.service;

import com.mastek.api.Individual;
import com.mastek.dna.api.NotFoundException;
import com.mastek.dna.dao.ProfileDao;
import com.mastek.dna.model.Profile;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableFeignClients
public class ProfileAspect {
    @Autowired
    private IndividualClient individualClient;

    @Autowired
    private ProfileDao profileDao;

    private final static Logger LOGGER = LoggerFactory.getLogger(ProfileAspect.class);

    @Before("execution(* com.mastek.dna.service.ProfileService.find(..)) and args(id)")
    public void beforeFind(final int id) {
        checkExists(id);
    }

    @Before("execution(* com.mastek.dna.service.ProfileService.update(..)) and args(profile)")
    public void beforeUpdate(final Profile profile) {
        checkExists(profile.getId());
    }

    @Before("execution(* com.mastek.dna.service.ProfileService.delete(..)) and args(id)")
    public void beforeDelete(final int id) {
        checkExists(id);
    }

    @Before("execution(* com.mastek.dna.service.ProfileService.create(..)) and args(profile)")
    public void beforeCreate(final Profile profile) {
        checkIndividualExists(profile.getIndividualId());
    }

    private void checkIndividualExists(int individualId) {
        Individual individual = null;
        try {
            individual = individualClient.find(individualId);
        } catch (Exception e) {
            throw new NotFoundException(individualId);
        }
        //FixMe: change how we are checking existence of a individual
        final boolean exists = true;
        LOGGER.info("Individual Found with ID [{}] : [{}] : [{}]", individualId, exists, individual);
        if (!exists) {
            throw new NotFoundException(individualId);
        }
    }

    private void checkExists(final int id) {
        final boolean exists = profileDao.exists(id);
        LOGGER.info("Profile Found with ID [{}] : [{}]", id, exists);
        if (!exists) {
            throw new NotFoundException(id);
        }
    }
}

