package com.mastek.dna.db;

import com.mastek.dna.model.Profile;
import org.dbunit.DatabaseUnitException;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Arrays;

import static org.apache.commons.lang3.tuple.Pair.of;

@Component
public class ProfileChecker extends BaseChecker
{
	@Override
	public String getXmlTemplate()
	{
		return "/data/model/profile.xml";
	}

	@Override
	public String getTableName()
	{
		return "individual_profile";
	}

	public void assertDatabase(final Profile profile) throws MalformedURLException, SQLException, DatabaseUnitException
	{
		assertTable(profile.getId(),
				Arrays.asList(
						of("$[id]", profile.getId()),
						of("$[individual_id]", profile.getIndividualId()),
						of("$[finger_print_data]", profile.getFingerPrintData()),
						of("$[retina_scan_data]", profile.getRetinaScanData())));
	}
}
