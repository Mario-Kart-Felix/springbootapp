package com.mastek.dna.api;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mastek.dna.model.Address;
import com.mastek.dna.service.AddressService;

/**
 * Test the orchestration between the endpoint and the service
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AddressEndpointTest
{
	@InjectMocks
	private final AddressEndpoint addressEndpoint = new AddressEndpoint();

	@Mock
	private AddressService addressService;

	@Test
	public void testCreate()
	{
		final int individualId = 1;
		final Address address = new Address();

		Mockito.when(addressService.create(individualId, address)).thenReturn(address);

		final Address createdAddress = addressEndpoint.create(individualId, address);
		Assert.assertThat("Unexpected Address", createdAddress, Is.is(address));

		Mockito.verify(addressService).create(individualId, address);
	}

	@Test
	public void testUpdate()
	{
		final int individualId = 1;
		final int addressId = 1;
		final Address address = new Address();

		Mockito.when(addressService.update(individualId, address)).thenReturn(address);

		final Address updatedAddress = addressEndpoint.update(individualId, addressId, address);
		Assert.assertThat("Unexpected Address", updatedAddress, Is.is(address));

		Mockito.verify(addressService).update(individualId, address);
	}

	@Test
	public void testDelete()
	{
		final int individualId = 1;
		final int addressId = 1;

		addressEndpoint.delete(individualId, addressId);

		Mockito.verify(addressService).delete(individualId, addressId);
	}
}
