package com.mastek.dna.model.converter;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date>
{
	@Override
	public Date convertToDatabaseColumn(final LocalDate attribute)
	{
		return attribute == null ? null : Date.valueOf(attribute);
	}

	@Override
	public LocalDate convertToEntityAttribute(final Date dbData)
	{
		return dbData == null ? null : dbData.toLocalDate();
	}
}