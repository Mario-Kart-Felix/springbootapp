package com.mastek.dna.api.db;

import static org.apache.commons.lang3.tuple.Pair.of;

import java.net.MalformedURLException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

import org.dbunit.DatabaseUnitException;
import org.springframework.stereotype.Component;

import com.mastek.dna.db.BaseChecker;
import com.mastek.dna.model.Individual;

@Component
public class IndividualChecker extends BaseChecker
{
	@Override
	public String getXmlTemplate()
	{
		return "/data/model/individual.xml";
	}

	@Override
	public String getTableName()
	{
		return "individual";
	}

	public void assertDatabase(final Individual individual) throws MalformedURLException, SQLException, DatabaseUnitException
	{
		assertTable(individual.getId(),
				Arrays.asList(
						of("$[id]", individual.getId()),
						of("$[title]", individual.getName().getTitle()),
						of("$[firstname]", individual.getName().getFirstname()),
						of("$[middlename]", individual.getName().getMiddlename()),
						of("$[surname]", individual.getName().getSurname()),
						of("$[dob]", Date.valueOf(individual.getDob()))));
	}
}
