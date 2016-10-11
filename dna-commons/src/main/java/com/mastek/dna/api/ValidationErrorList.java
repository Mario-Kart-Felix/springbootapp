package com.mastek.dna.api;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ToStringBuilder;

@XmlRootElement(name = "errors")
public class ValidationErrorList
{
	private final List<ValidationError> errors = new ArrayList<>();

	public List<ValidationError> getErrors()
	{
		return errors;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
