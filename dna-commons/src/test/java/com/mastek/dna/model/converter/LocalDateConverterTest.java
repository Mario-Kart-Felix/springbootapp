package com.mastek.dna.model.converter;

import java.sql.Date;
import java.time.LocalDate;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Test;

public class LocalDateConverterTest
{
	@Test
	public void testConvertToDatabaseColumn()
	{
		final LocalDate localDate = LocalDate.of(2016, 10, 25);

		final LocalDateConverter localDateConverter = new LocalDateConverter();
		final Date sqlDate = localDateConverter.convertToDatabaseColumn(localDate);

		Assert.assertThat("Unexpected SQL date", sqlDate, Is.is(Date.valueOf(localDate)));
	}

	@Test
	public void testConvertToDatabaseColumnWhenNull()
	{
		final LocalDateConverter localDateConverter = new LocalDateConverter();
		final Date sqlDate = localDateConverter.convertToDatabaseColumn(null);

		Assert.assertThat("Unexpected SQL date", sqlDate, IsNull.nullValue());
	}
}
