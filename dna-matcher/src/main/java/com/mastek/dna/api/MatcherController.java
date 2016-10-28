package com.mastek.dna.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mastek.dna.model.Sample;
import com.mastek.dna.service.MatcherService;

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

	@Autowired
	private MatcherService matcherService;

	@GetMapping(ROOT_URL)
	public String home()
	{

		String result = ("Full individual match not found");
		Integer individualId = null;

		Sample sample = matcherService.find(1000);

		if (sample!=null)
		{
			individualId = matcherService.findFullMatch(sample);
		}

		if (individualId != null)
		{
			result = ("Full individual match found >>>>>>> Sample fingerprint: " + sample.getFingerprint()
					+ ", retina sample: " + sample.getRetinascan() + ".......  Individual has id of: " + individualId);
		}
		return result;

	}

}