package com.mastek.dna.model.json;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer extends StdSerializer<LocalDate>
{
	private static final long serialVersionUID = 1L;

	public LocalDateSerializer()
	{
		super(LocalDate.class);
	}

	@Override
	public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider sp) throws IOException, JsonProcessingException
	{
		gen.writeString(value.toString());
	}
}