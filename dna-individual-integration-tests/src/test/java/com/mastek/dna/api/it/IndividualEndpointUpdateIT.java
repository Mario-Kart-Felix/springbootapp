package com.mastek.dna.api.it;

import java.time.LocalDate;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.mastek.dna.model.Address;
import com.mastek.dna.model.Individual;
import com.mastek.dna.model.Name;
import com.mastek.dna.model.Name.Title;

public class IndividualEndpointUpdateIT extends IndividualEndpointSuper
{
	private Individual existing;

	@Before
	public void loadEntity()
	{
		// TODO : Load entity from XML/DB
		final int id = 1;

		existing = new Individual()
				.setId(id)
				.setName(new Name().setTitle(Title.MR).setFirstname("Craig").setSurname("Greenhalgh"))
				.setDob(LocalDate.of(1980, 7, 30))
				.setAddress(new Address().setTown("Town"));
	}

	@Test
	public void testUpdate()
	{
		// TODO : Alter all attributes that can change
		existing.getName().setTitle(Title.DR);

		final Individual updated = send(existing, Individual.class);

		Assert.assertThat("Unexpected created response", updated, IndividualMatcher.forPerson(existing));

		// TODO : Assert the DB
	}

	@Override
	protected String getUrl()
	{
		return super.getUrl() + "/{id}";
	}

	@Override
	protected Map<String, Object> getUrlVariables()
	{
		final Map<String, Object> map = super.getUrlVariables();
		map.put("id", existing.getId());
		return map;
	}

	private <I, O> O send(final I toSend, final Class<O> responseClass)
	{
		return send(toSend, HttpMethod.PUT, responseClass);
	}
}
