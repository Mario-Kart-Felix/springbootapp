package com.mastek.dna.dao;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.mastek.dna.model.Sample;

public class MatcherDaoImpl implements MatcherDaoSProcCustom

{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Integer findFullSampleMatch(Sample sample)
	{
		// Define the stored procedure to call
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("fullmatcher");

		// Register the parameters
		query.registerStoredProcedureParameter("sample_fprint", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("sample_retina", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("indid", Integer.class, ParameterMode.OUT);
		query.setParameter("sample_fprint", sample.getFingerprint());
		query.setParameter("sample_retina", sample.getRetinascan());

		query.execute();
		
		Integer individualId = (Integer) query.getOutputParameterValue("indid");
		return individualId;

	}
}
