package com.mastek.dna.model.adapter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>
{
	@Override
	public LocalDate unmarshal(final String dateString) throws Exception
	{
		return LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE);
	}

	@Override
	public String marshal(final LocalDate localDate) throws Exception
	{
		return DateTimeFormatter.ISO_DATE.format(localDate);
	}
}