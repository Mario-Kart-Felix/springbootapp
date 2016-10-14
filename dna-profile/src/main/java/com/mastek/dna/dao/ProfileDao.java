package com.mastek.dna.dao;

import com.mastek.dna.model.Profile;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileDao {

    public Profile create(Profile profile) {
        System.out.println("ProfileDao:  Creating Individual Profile" + profile);
        profile.setId(1);
        return profile;
    }

    public Profile update(Profile profile) {
        System.out.println("ProfileDao:  updating Profile" + profile);
        return profile;
    }

    public void delete(int id) {
        System.out.println("ProfileDao: deleting Profile:" + id);
    }
}
