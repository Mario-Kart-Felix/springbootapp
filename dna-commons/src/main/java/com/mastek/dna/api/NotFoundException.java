package com.mastek.dna.api;

public class NotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	private final Integer id;

	public NotFoundException(final Integer id)
	{
		super("No entity found");
		this.id = id;
	}

	public Integer getId()
	{
		return id;
	}
}
