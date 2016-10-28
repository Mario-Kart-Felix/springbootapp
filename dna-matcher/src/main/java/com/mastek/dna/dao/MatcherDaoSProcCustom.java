package com.mastek.dna.dao;

import com.mastek.dna.model.Sample;

/* 
 * A custom DAO containing the Stored Procedure method call. This will enable Spring Data to locate and instantiate the class
 */
public interface MatcherDaoSProcCustom

{
	public Integer findFullSampleMatch(Sample sample);
}
