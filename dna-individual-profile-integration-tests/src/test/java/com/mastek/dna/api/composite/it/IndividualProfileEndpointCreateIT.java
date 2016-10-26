package com.mastek.dna.api.composite.it;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDate;

import org.dbunit.DatabaseUnitException;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.mastek.dna.model.Individual;
import com.mastek.dna.model.IndividualProfile;
import com.mastek.dna.model.Name;
import com.mastek.dna.model.Name.Title;
import com.mastek.dna.model.Profile;


public class IndividualProfileEndpointCreateIT extends IndividualProfileEndpointSuper
{

	@Test
	public void testCreate() throws MalformedURLException, SQLException, DatabaseUnitException
	{
		double randNumber = Math.random();
		final Individual individual = new Individual()
				.setName(new Name().setTitle(Title.MR).setFirstname("John"+randNumber).setSurname("Smith"+randNumber))
				.setDob(LocalDate.of(1980, 7, 30));
		
		final Profile profile = new Profile();
		profile.setFingerPrintData("fingerprint"+randNumber);
		profile.setRetinaScanData("retinaScanData"+randNumber);

		final IndividualProfile individualProfile = new IndividualProfile();
		individualProfile.setIndividual(individual);
		individualProfile.setProfile(profile);
		final IndividualProfile created = send(individualProfile, IndividualProfile.class);


		Assert.assertThat("Individual Id should not be null", created.getIndividual().getId(), IsNull.notNullValue());

		Assert.assertThat("Profile Id should not be null", created.getProfile().getId(), IsNull.notNullValue());

	}


	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.POST, responseClass);
	}
}
