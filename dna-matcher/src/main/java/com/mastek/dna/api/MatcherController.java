package com.mastek.dna.api;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * @NamedStoredProcedureQuery(

		name = "fullsamplematcher", 
		procedureName = "fullsamplematcher", 
		parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "sample_fprint"), 
			@StoredProcedureParameter(mode = ParameterMode.IN, type = String.class, name = "sample_retina"),
			@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "indId")
}
)
 */

@RestController
public class MatcherController
{

	private static final String ROOT_URL = "/match/full";
	@PersistenceContext
	private EntityManager em;

	@GetMapping(ROOT_URL)
	public String home()
	{

		// Define the stored procedure to call
		StoredProcedureQuery query = em.createStoredProcedureQuery("fullsamplematcher");

		// Register the parameters
		query.registerStoredProcedureParameter("sample_fprint", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("sample_retina", String.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("indid", Integer.class, ParameterMode.OUT);
		query.setParameter("sample_fprint", "ABC12345678");
		query.setParameter("sample_retina", "CD987654");

		query.execute();

		Integer indId = (Integer) query.getOutputParameterValue("indid");
		String result;
		
		if (indId == null)
		{
			result = ("Full individual match not found");
		}
		else
		{
			result = ("Full individual match found >>>>>>> Sample fingerprint: " + query.getParameterValue("sample_fprint")
					+ ", retina sample: " + query.getParameterValue("sample_retina") + ".......  Individual has id of: " + indId);
		}
		return result;

	}

}