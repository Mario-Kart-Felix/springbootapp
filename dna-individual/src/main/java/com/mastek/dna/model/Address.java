package com.mastek.dna.model;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Address
{
	private String town;

	@NotNull
	public String getTown()
	{
		return town;
	}

	public Address setTown(final String town)
	{
		this.town = town;
		return this;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
