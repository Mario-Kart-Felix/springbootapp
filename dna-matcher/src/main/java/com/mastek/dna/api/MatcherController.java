package com.mastek.dna.api;


import javax.persistence.EntityManager;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
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

@NamedStoredProcedureQuery(
		name = "testmatcher", 
		procedureName = "testmatcher", 
		parameters = { 
			@StoredProcedureParameter(mode = ParameterMode.OUT, type = Integer.class, name = "indId")
}
)

@RestController
public class MatcherController
{
	
	private static final String ROOT_URL = "/match/full";
	@PersistenceContext
	private EntityManager em;
	
	@GetMapping(ROOT_URL)
	public String home() {
		
		// define the stored procedure
		//StoredProcedureQuery query = em.createStoredProcedureQuery("fullsamplematcher");
		//query.setParameter("sample_fprint", "ABC12345678");
		//query.setParameter("sample_retina", "CD987654");
		StoredProcedureQuery query = em.createStoredProcedureQuery("testmatcher");
		query.execute();

		Integer indId = (Integer) query.getOutputParameterValue("indId");
        return "Hello World! - Individual has id of: "+ indId;
    }
	
	
	
	

}