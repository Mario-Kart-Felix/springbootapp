package com.mastek.dna.model;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.mastek.dna.model.validator.Age;
import com.mastek.dna.model.validator.Create;

public class Individual
{
	private Integer id;
	private LocalDate dob;
	private Name name;
	private Address address;

	@Null(groups = Create.class)
	public Integer getId()
	{
		return id;
	}

	public Individual setId(final Integer id)
	{
		this.id = id;
		return this;
	}

	@NotNull
	@Age(max = 120)
	public LocalDate getDob()
	{
		return dob;
	}

	public Individual setDob(final LocalDate dob)
	{
		this.dob = dob;
		return this;
	}

	@Valid
	@NotNull
	public Name getName()
	{
		return name;
	}

	public Individual setName(final Name name)
	{
		this.name = name;
		return this;
	}

	@Valid
	@NotNull
	public Address getAddress()
	{
		return address;
	}

	public Individual setAddress(final Address address)
	{
		this.address = address;
		return this;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
