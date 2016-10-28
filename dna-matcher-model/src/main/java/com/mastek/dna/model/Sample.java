package com.mastek.dna.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Null;

import org.apache.commons.lang3.builder.ToStringBuilder;
import com.mastek.dna.model.validator.Api;

@Entity
@Table(name = "sample_profile")
public class Sample
{
	private Integer id;
	private String fingerprint;
	private String retinascan;

	@Null(groups = Api.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sample_profile_seq")
	@SequenceGenerator(name = "sample_profile_seq", sequenceName = "sample_profile_seq", allocationSize = 1)
	public Integer getId()
	{
		return id;
	}

	public Sample setId(final Integer id)
	{
		this.id = id;
		return this;
	}

	@Column(name = "finger_print_data")
	public String getFingerprint()
	{
		return fingerprint;
	}

	public void setFingerprint(String fingerprint)
	{
		this.fingerprint = fingerprint;
	}

	@Column(name = "retina_scan_data ")
	public String getRetinascan()
	{
		return retinascan;
	}

	public void setRetinascan(String retinascan)
	{
		this.retinascan = retinascan;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}
}
