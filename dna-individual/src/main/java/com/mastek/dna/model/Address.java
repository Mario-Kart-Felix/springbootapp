package com.mastek.dna.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mastek.dna.model.validator.Api;

@Entity
public class Address
{
	private Integer id;
	private Individual individual;

	private String line1;
	private String line2;
	private String county;
	private String country;
	private String postCode;

	@Null(groups = Api.class)
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
	@SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
	public Integer getId()
	{
		return id;
	}

	public Address setId(final Integer id)
	{
		this.id = id;
		return this;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "individual_id", updatable = false)
	@JsonIgnore
	public Individual getIndividual()
	{
		return individual;
	}

	public Address setIndividual(final Individual individual)
	{
		this.individual = individual;
		return this;
	}

	@Column(name = "address_line1")
	public String getLine1()
	{
		return line1;
	}

	public Address setLine1(final String line1)
	{
		this.line1 = line1;
		return this;
	}

	@Column(name = "address_line2")
	public String getLine2()
	{
		return line2;
	}

	public Address setLine2(final String line2)
	{
		this.line2 = line2;
		return this;
	}

	public String getCounty()
	{
		return county;
	}

	public Address setCounty(final String county)
	{
		this.county = county;
		return this;
	}

	public String getCountry()
	{
		return country;
	}

	public Address setCountry(final String country)
	{
		this.country = country;
		return this;
	}

	@Column(name = "post_code")
	public String getPostCode()
	{
		return postCode;
	}

	public Address setPostCode(final String postCode)
	{
		this.postCode = postCode;
		return this;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
