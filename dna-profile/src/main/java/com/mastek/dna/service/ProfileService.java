package com.mastek.dna.service;

import com.mastek.dna.dao.ProfileDao;
import com.mastek.dna.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileDao profileDao;

    public Profile create(Profile profile) {
        return profileDao.create(profile);
    }

    public Profile update(Profile profile) {
        return profileDao.update(profile);
    }

    public void delete(int id) {
        profileDao.delete(id);
    }
}
