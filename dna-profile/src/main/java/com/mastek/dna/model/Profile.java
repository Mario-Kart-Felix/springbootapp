package com.mastek.dna.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.mastek.dna.model.validator.Api;

public class Profile
{
	private Integer id;
	private List<String> fingerPrint;
	private List<String> retinaScan;

	@Null(groups = Api.class)
	public Integer getId()
	{
		return id;
	}

	public void setId(final Integer id)
	{
		this.id = id;
	}

	@Valid
	@NotNull
	public List<String> getFingerPrint()
	{
		return fingerPrint;
	}

	public void setFingerPrint(final List<String> fingerPrint)
	{
		this.fingerPrint = fingerPrint;
	}

	@Valid
	@NotNull
	public List<String> getRetinaScan()
	{
		return retinaScan;
	}

	public void setRetinaScan(final List<String> retinaScan)
	{
		this.retinaScan = retinaScan;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
