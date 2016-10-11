package com.mastek.dna.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationError
{
	private final String field;
	private final String message;

	public ValidationError(@JsonProperty("field") final String field, @JsonProperty("message") final String message)
	{
		this.field = field;
		this.message = message;
	}

	public String getField()
	{
		return field;
	}

	public String getMessage()
	{
		return message;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
