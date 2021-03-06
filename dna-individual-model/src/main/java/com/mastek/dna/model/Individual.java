package com.mastek.dna.model;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mastek.dna.model.json.LocalDateDeserializer;
import com.mastek.dna.model.json.LocalDateSerializer;
import com.mastek.dna.model.validator.Age;
import com.mastek.dna.model.validator.Api;
import com.mastek.dna.model.validator.DuplicateIndividual;

@Entity
@DuplicateIndividual
public class Individual
{
	private Integer id;
	private LocalDate dob;
	private Name name;

	private Collection<Address> addresses;

	@Null(groups = Api.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "individual_seq")
	@SequenceGenerator(name = "individual_seq", sequenceName = "individual_seq", allocationSize = 1)
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
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
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
	@Embedded
	public Name getName()
	{
		return name;
	}

	public Individual setName(final Name name)
	{
		this.name = name;
		return this;
	}

	@Null(groups = Api.class)
	@OneToMany
	@JoinColumn(name = "individual_id", updatable = false)
	public Collection<Address> getAddresses()
	{
		return addresses;
	}

	public Individual setAddresses(final Collection<Address> addresses)
	{
		this.addresses = addresses;
		return this;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
