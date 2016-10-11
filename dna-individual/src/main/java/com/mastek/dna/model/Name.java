package com.mastek.dna.model;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Name
{
	private Title title;
	private String firstname;
	private String middlename;
	private String surname;

	@NotNull
	public Title getTitle()
	{
		return title;
	}

	public Name setTitle(final Title title)
	{
		this.title = title;
		return this;
	}

	@NotNull
	public String getFirstname()
	{
		return firstname;
	}

	public Name setFirstname(final String firstname)
	{
		this.firstname = firstname;
		return this;
	}

	public String getMiddlename()
	{
		return middlename;
	}

	public Name setMiddlename(final String middlename)
	{
		this.middlename = middlename;
		return this;
	}

	@NotNull
	public String getSurname()
	{
		return surname;
	}

	public Name setSurname(final String surname)
	{
		this.surname = surname;
		return this;
	}

	public enum Title
	{
		MR, MRS, MISS, DR;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
