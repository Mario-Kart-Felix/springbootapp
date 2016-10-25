package com.mastek.dna.api.it.profile;

import com.mastek.dna.model.Profile;
import org.springframework.jdbc.core.RowMapper;

public final class ProfileUtil
{
    private ProfileUtil()
    {
        // Hidden by design
    }

    public static final RowMapper<Profile> ROW_MAPPER = (rs, rowNumber) -> {
                                                    Profile profile = new Profile();
        profile.setId(rs.getInt("id"));
        profile.setIndividualId(rs.getInt("individual_id"));
        profile.setFingerPrintData(rs.getString("finger_print_data"));
        profile.setRetinaScanData(rs.getString("retina_scan_data"));
        return profile;
    };

}
