package com.mastek.dna.api;

import com.mastek.dna.model.Profile;
import com.mastek.dna.model.validator.Create;
import com.mastek.dna.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController("/profile")
public class ProfileEndpoint {

    @Autowired
    ProfileService profileService;

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Profile create(@RequestBody @Validated(Create.class) final Profile profile) {
        return profileService.create(profile);
    }

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
    public Profile update(@PathVariable final int id, @RequestBody @Validated Profile profile) {
        profile.setId(id);
        return profileService.update(profile);
    }
    @RequestMapping(value = "/profile/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable final int id) {
        profileService.delete(id);
    }
}
